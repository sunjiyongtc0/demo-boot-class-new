package com.sunjy.secret.boot.Controller.bbs;

import com.alibaba.fastjson.JSONObject;
import com.sunjy.secret.boot.domain.TBbsComment;
import com.sunjy.secret.boot.domain.TBbsTitle;
import com.sunjy.secret.boot.domain.TPUser;
import com.sunjy.secret.boot.service.bbs.BbsService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

@Controller
@RequestMapping("/userbbs")
public class UserBbsController {

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
        mav.setViewName("/bbs/Userbbs");
        TPUser user=(TPUser) request.getSession().getAttribute("user");
        long userId=user.getId();
        mav.addObject("lc",bbsService.findCreat(userId));
        mav.addObject("lj",bbsService.findJoin(userId));
        mav.addObject("user",user);

        return mav;
    }

    @GetMapping("/message")
    public  ModelAndView message(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/bbs/UserMessage");
        TPUser user=(TPUser) request.getSession().getAttribute("user");
        long userId=user.getId();
        System.out.println(bbsService.findMessage(userId));
        mav.addObject("user",user);
        mav.addObject("lc",bbsService.findMessage(userId));
        return mav;
    }

    @GetMapping("/creatBbs")
    public  String CreatBbs(){

        return "/bbs/creatBbs";
    }
    @PostMapping("/addSave")
    @ResponseBody
    public  JSONObject addSave(@ModelAttribute TBbsTitle tbt){
        JSONObject jo=new JSONObject();
        tbt.setCreatTime(new Date());
        TPUser user=(TPUser) request.getSession().getAttribute("user");
        long userId=user.getId();
        tbt.setUserId(userId);tbt.setFlatId(0);
        int i=bbsService.saveTBbsTitle(tbt);
        if(i==1){
            jo.put("message", "ok");
        }else {
            jo.put("message", "false");
        }
        return jo;
    }
}
