package com.sunjy.secret.boot.Controller.schedule;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping("/Calendar")
public class CalendarController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @GetMapping("/main")
    public  String main(){
        JSONObject o=new JSONObject();
        o.put("2020-03-03","这天鸣人捣乱了");
        o.put("2020-03-13","这天猪鹿蝶成型");
        o.put("2020-03-21","鼬进木叶村了");
        o.put("2020-04-02","我爱罗暴走");
        o.put("2020-04-11","9：00中忍毕业典礼");
        o.put("2020-05-23","音忍村袭击木叶");
        o.put("2020-03-13","304");
        o.put("2020-03-23","567");
        request.setAttribute("map",o);
        return "/Schedule/calendar";
    }

    @GetMapping("/getMap")
    @ResponseBody
    public JSONObject markJson(){
        JSONObject o=new JSONObject();
        o.put("2020-03-03","这天鸣人捣乱了");
        o.put("2020-03-13","这天猪鹿蝶成型");
        o.put("2020-03-21","鼬进木叶村了");
        o.put("2020-04-02","我爱罗暴走");
        o.put("2020-04-11","9：00中忍毕业典礼");
        o.put("2020-05-23","音忍村袭击木叶");
        o.put("2020-03-13","304");
        o.put("2020-03-23","567");

        return o;
    }

}
