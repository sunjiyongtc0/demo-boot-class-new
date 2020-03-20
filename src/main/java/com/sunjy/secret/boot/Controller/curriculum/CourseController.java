package com.sunjy.secret.boot.Controller.curriculum;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.Page;
import com.sunjy.secret.boot.domain.ResultVO;
import com.sunjy.secret.boot.domain.TCCourse;
import com.sunjy.secret.boot.domain.TPUser;
import com.sunjy.secret.boot.service.curriculum.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private CourseService courseService;

    @GetMapping("/main")
    public ModelAndView CourseMain(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/curriculum/couerseMain");
        List<TCCourse> lt=courseService.findCourse();
        mav.addObject("list",lt);
        return mav;
    }

    @GetMapping("/addCourse")
    public ModelAndView  addCourse(){
        ModelAndView mav=new ModelAndView();
        HttpSession session = request.getSession();
        TPUser user=(TPUser)session.getAttribute("user");
        mav.addObject("user",user);
        mav.setViewName("/curriculum/addCourse");
    return mav;
    }

    @PostMapping("/registerCourse")
    public ModelAndView registerCourse(@ModelAttribute TCCourse tcCourse){
        System.out.println(tcCourse.getCouerseName());
        courseService.creatCourse(tcCourse);
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/curriculum/ClassMain");
        List<TCCourse> lt=courseService.findCourse();
        mav.addObject("list",lt);
        return mav;
    }
//--------------------------课程数据
    @GetMapping("/ClassMain")
    public String ClassMain(){
        return "/curriculum/ClassMain";
    }

    @GetMapping("/getjsonData")
    @ResponseBody
    public JSONObject GetJsonData(){
        ResultVO vo=new ResultVO();
        vo.setMsg("课程详情数据");
        vo.setCode(1);
        JSONArray ja= new JSONArray();
        List<TCCourse> lt=courseService.findCourse();
        ja= JSONArray.parseArray(JSON.toJSONString(lt));
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
