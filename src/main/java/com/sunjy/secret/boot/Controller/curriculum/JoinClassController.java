package com.sunjy.secret.boot.Controller.curriculum;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sunjy.secret.boot.domain.*;
import com.sunjy.secret.boot.service.curriculum.CourseService;
import com.sunjy.secret.boot.service.curriculum.TeachService;
import com.sunjy.secret.boot.service.pdc.PdcService;
import com.sunjy.secret.boot.util.MethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/joinClass")
public class JoinClassController {

    @Value("${FilePath}")
    String localPath;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private PdcService pdcService;

    @Autowired
    private TeachService teachService;

    @Autowired
    private CourseService courseService;
    /**
     * 报名管理
    * */
    @GetMapping("/joinMain")
    public String Joinain(){
        return "/curriculum/joinMain";
    }


    /**
     * 报名设置
     * */
    @GetMapping("/set/{id}")
    public ModelAndView SetTeach(@PathVariable("id") long id){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/curriculum/joinSet");
        TCCourse tcCourse= teachService.findCourse(id);
        mav.addObject("tcCourse",tcCourse);
        List<TCPdcDetail> lt=pdcService.findAllTeacher();
        List<TCPdcDetail> lts=new ArrayList<TCPdcDetail>();
        for(TCPdcDetail tp:lt){
            tp.setPhotoPath("/userData"+tp.getPhotoPath().substring(tp.getPhotoPath().lastIndexOf("/")));
            lts.add(tp);
        }
        mav.addObject("lt",lts);
        mav.addObject("localPath",localPath);

//        MethodService md = new MethodService();
//        mav.addObject("methodService",md);
//        request.setAttribute("methodService",md);
//        request.setAttribute("proofsList",ls);
        return mav;
    }
        @PostMapping("addJoin")
        public String addJoin(@ModelAttribute TCTeach tt){
        TPUser user=(TPUser)request.getSession().getAttribute("user");
        tt.setStudentId(user.getId()+",");
            teachService.add(tt);
        return "/curriculum/joinMain";
        }
    /**
     * 我的报名
     * */
    @GetMapping("/joinSee")
    public String JoinSee(){
        return "/curriculum/joinSee";
    }

    /**
     *
     * 报名详情表
     * */
    @GetMapping("/getjsonData")
    @ResponseBody
    public JSONObject GetJsonData(){
        ResultVO vo=new ResultVO();
        vo.setMsg("我的详情表");
        vo.setCode(1);
        JSONArray ja= new JSONArray();
        TPUser tu=(TPUser) request.getSession().getAttribute("user");
        List<Map<String,Object>>  lm=courseService.findJoin(tu.getId());
        List<Map<String,Object>> lts=new ArrayList<Map<String,Object>>();
        for(Map<String,Object> tp:lm){
            tp.put("photo_path","/userData"+(tp.get("photo_path")+"").substring((tp.get("photo_path")+"").lastIndexOf("/")));
            lts.add(tp);
        }

        ja= JSONArray.parseArray(JSON.toJSONString(lts));
        if(ja.size()>0) {
            vo.setCount(ja.size());vo.setCode(0);vo.setData(ja);
        }
        return vo.toJSONObject();
    }


//    @PostMapping("/registerRoleinto")
//    public ModelAndView registerRoleInto(){
//        String roleName=request.getParameter("role_name");
//        String powerId=request.getParameter("power_id");
//        System.out.println(roleName+"====="+powerId);
//        TPRole tpRole=new TPRole();
//        tpRole.setRoleName(roleName);
//        tpRole.setPowerId(powerId);
//        TPUser user=(TPUser)request.getSession().getAttribute("user");
//        tpRole.setCreatUser(user.getId().toString());
//        int i=roleService.creatRole(tpRole);
//        ModelAndView mav=new ModelAndView();
//        mav.setViewName("/pdc/roleMain");
//        List<TPRole> lr=roleService.findUser();
//        mav.addObject("list",lr);
//        return mav;
//    }
}
