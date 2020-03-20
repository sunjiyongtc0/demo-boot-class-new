package com.sunjy.secret.boot.Controller.bbs;

import com.alibaba.fastjson.JSONObject;
import com.sunjy.secret.boot.domain.TBbsComment;
import com.sunjy.secret.boot.domain.TCCourse;
import com.sunjy.secret.boot.domain.TCTeach;
import com.sunjy.secret.boot.domain.TPUser;
import com.sunjy.secret.boot.service.bbs.BbsService;
import com.sunjy.secret.boot.service.curriculum.CourseService;
import com.sunjy.secret.boot.service.curriculum.TeachService;
import com.sunjy.secret.boot.util.MethodService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/bbs")
public class BbsController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private BbsService bbsService;

/**
 * 进入论坛主页
 * */
    @GetMapping("/main")
    public  ModelAndView  main(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/bbs/bbsMain");
        System.out.println(bbsService.findTitle().size());
        mav.addObject("lm",bbsService.findTitle());
        return mav;
    }
    /**
     * 读取一条记录的详情页
     * */
    @GetMapping("/detail/{id}")
    public  ModelAndView  Detail(@PathVariable("id") long  id){
        ModelAndView mav=new ModelAndView();
        mav.addObject("lm",bbsService.findComment(id));
        mav.addObject("returnCount",bbsService.findComment(id).size());
        mav.addObject("tbt",bbsService.findTitleById(id));
        mav.setViewName("/bbs/bbsDetail");
        return mav;
    }
/**
 * 跳转到跟帖页
 * */
    @GetMapping("/bbsAdd")
    public  String bbsAdd(){
        return "bbs/bbsAdd";
    }
/**
 *保存一条帖子
 * */
    @PostMapping("/addSave")
    @ResponseBody
    public JSONObject addSave(@Param("remark") String remark,@Param("titleId") Long titleId){
        JSONObject jo=new JSONObject();
        TBbsComment tbc=new TBbsComment();
        tbc.setCreatTime(new Date()); tbc.setTitleId(titleId);
        tbc.setFlatId(0);tbc.setRemark(remark);
        TPUser user= (TPUser) request.getSession().getAttribute("user");
        tbc.setUserId(user.getId());
        int i=bbsService.saveTBbsComment(tbc);
        if(i==1){
            jo.put("message","ok");
        }else{
            jo.put("message","false");
        }
        return  jo;
    }
}
