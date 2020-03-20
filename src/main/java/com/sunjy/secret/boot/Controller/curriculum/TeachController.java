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
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/teach")
public class TeachController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private TeachService teachService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private PdcService pdcService;

    @GetMapping("/set/{id}")
    public ModelAndView SetTeach(@PathVariable("id") long id){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/curriculum/couerseMain");
        TCCourse tcCourse= teachService.findCourse(id);
        mav.addObject("tcCourse",tcCourse);
        List<TPUser> ls= teachService.findAllStudent();
//        mav.addObject("ls",ls);
        List<TPUser> lt= teachService.findAllTeach();
        mav.addObject("lt",lt);
        JSONArray ja=new JSONArray();
        MethodService ms =new MethodService();
        for (TPUser u:lt){
            JSONObject jo=new JSONObject();
            jo.put("value",u.getId());jo.put("title",u.getAliasName());
//            if(ms.contains(u.getId()+"",ls)){
//                jo.put("checked",true);
//            }
            ja.add(jo);
        }
        mav.addObject("tlist",ja.toJSONString());
        MethodService md = new MethodService();
        mav.addObject("methodService",md);
//        request.setAttribute("methodService",md);
//        request.setAttribute("proofsList",ls);
        return mav;
    }


/**
 *
 *给课程分配教师
 * */
    @PostMapping("/addCouerse")
    public  String  addCouerse(@ModelAttribute TCCourse Course){
        TPUser u=(TPUser) request.getSession().getAttribute("user");
        Course.setUpdateTime(new Date());
        Course.setDistributeUserId(u.getId());
        courseService.updateCouerse(Course);
        return "redirect:/course/ClassMain";
    }

    @PostMapping("/add")
    public  ModelAndView  add(@ModelAttribute TCTeach tcTeach){
        teachService.add(tcTeach);
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/curriculum/ClassMain");
        List<TCCourse> lt=courseService.findCourse();
        mav.addObject("list",lt);
        return mav;
    }


    /**
     * 课程教师主页
     * */
    @GetMapping("/main")
    public ModelAndView teachMain(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/curriculum/teachMain");
        return  mav;
    }

    /**
     * 课程教师列表
     * */
    @GetMapping("/getjsonData")
    @ResponseBody
    public JSONObject GetJsonData(){
        ResultVO vo=new ResultVO();
        vo.setMsg("课程详情数据");
        vo.setCode(1);
        JSONArray ja= new JSONArray();
        List<TCPdcDetail> lt=  pdcService.findAllTeacher();
        ja= JSONArray.parseArray(JSON.toJSONString(lt));
        if(ja.size()>0) {
            vo.setCount(ja.size());vo.setCode(0);vo.setData(ja);
        }
        return vo.toJSONObject();
    }

    /**
     * 添加教师信息
     * */
    @GetMapping("/addpdc")
    public  String  addPdc(){
        request.setAttribute("type","add");
        return "curriculum/teacherDetail";
    }
    /**
     * 修改教师信息
     * */
    @GetMapping("/updatepdc/{id}")
    public  ModelAndView  updatePdc(@PathVariable("id") long id){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("curriculum/teacherDetail");
        TCPdcDetail tp=pdcService.findById(id);
        mav.addObject("tp",tp);
        mav.addObject("type","update");
        System.out.println(tp.getPdcName());
        return mav;
    }
}
