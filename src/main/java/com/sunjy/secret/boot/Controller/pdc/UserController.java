package com.sunjy.secret.boot.Controller.pdc;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sunjy.secret.boot.domain.ResultVO;
import com.sunjy.secret.boot.domain.TPUser;
import com.sunjy.secret.boot.domain.TPdcUserRole;
import com.sunjy.secret.boot.service.pdc.UserService;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class UserController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private UserService userService;


    @GetMapping("/login")
    public  String login(){
        return "pdc/login";
    }
    @GetMapping("/logininto")
    public  String  logininto() throws IOException, ServletException {
        String userName=request.getParameter("user_name");
        String userPass=request.getParameter("user_pass");
        TPUser tpUser=new TPUser();
        tpUser.setUserName(userName);
        tpUser.setUserPass(userPass);
       // TPUser user=this.userService.JudgeUser( tpUser);
        TPUser user=this.userService.getUser((long) this.userService.JudgeUser( tpUser).getId());
        if(user!=null){
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            System.out.println(user.getUserName());
            return "home";
        }else{
            return "pdc/login";
        }
    }

    @GetMapping("/register")
    public  String Register(){
        TPUser tu=(TPUser)request.getSession().getAttribute("user");
        if(tu==null||tu.getId()<1){
            request.setAttribute("type",0);
        }else {
            request.setAttribute("type",1);
        }
        return "pdc/register";
    }

    @PostMapping("/registerinto")
    public  String RegisterInto() throws IOException, ServletException {
        String userName=request.getParameter("user_name");
        String userPass=request.getParameter("user_pass");
        String aliasName=request.getParameter("alias_name");
        TPUser tpUser=new TPUser();
        tpUser.setUserName(userName);
        tpUser.setUserPass(userPass);
        tpUser.setAliasName(aliasName);
        TPUser tu=(TPUser)request.getSession().getAttribute("user");
        if (tu==null||tu.getId()<1) {
            if (this.userService.creatUser(tpUser) == 1) {
                return "pdc/login";
            } else {
                return "/register";
            }
        }else{
            if (this.userService.creatUser(tpUser) == 1) {
                return "pdc/userMain";
            }else{
                return "pdc/register";
            }

        }
    }

    @GetMapping("/userMain")
    public ModelAndView UserMain(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("pdc/userMain");
//        List<TPUser> lu=userService.findUser();
//        mav.addObject("list",lu);
        return mav;
    }

    @GetMapping("/loginout")
    public  String loginout(){
        request.getSession().setAttribute("user",null);
        return "pdc/login";
    }

    @GetMapping("user/getjsonData")
    @ResponseBody
    public JSONObject  getJsonData(){
        ResultVO vo= new ResultVO();
        vo.setMsg("用户列表");
        vo.setCode(1);
        JSONArray ja= new JSONArray();
        List<TPUser> lu=userService.findUser();
        ja= JSONArray.parseArray(JSON.toJSONString(lu));
        if(ja.size()>0) {
            vo.setCount(ja.size());vo.setCode(0);vo.setData(ja);
        }
        return vo.toJSONObject();
    }

    @PostMapping("/user/addSave")
    @ResponseBody
    public JSONObject addSave(){
        String uid=request.getParameter("userId");
        String roleIds= request.getParameter("roleIds");
        ResultVO vo= new ResultVO();
        String[] rid=roleIds.split(",");
        for (int i=0;i<rid.length;i++) {
            String s=rid[i];
            TPdcUserRole ur=new TPdcUserRole();
            ur.setUserId(Long.valueOf(uid));ur.setRoleId(Long.valueOf(s));
            int j=userService.saveUserRole(ur);
        }
       vo.setData(uid+"======"+roleIds);
        vo.setMsg("ok");
       return vo.toJSONObject();
    }

}
