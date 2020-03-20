package com.sunjy.secret.boot.Controller;

import com.sunjy.secret.boot.domain.TPUser;
import com.sunjy.secret.boot.mapper.TPUserMapper;
import com.sunjy.secret.boot.service.pdc.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private UserService userService;

    @GetMapping("/t1")
    public String  t1(){
        return "t1";
    }

    @GetMapping("/t2")
    public  String t2(){
        System.out.println("t2");
        TPUser tpUser=new TPUser();
        tpUser.setUserName("admin");
        tpUser.setUserPass("admin");
        tpUser.setRoleId(1l);
        tpUser.setCreatTime(new Date());
        System.out.println( this.userService.creatUser( tpUser));
        return "save";
    }
}
