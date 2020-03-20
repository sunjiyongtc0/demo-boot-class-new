package com.sunjy.secret.boot.Controller.pdc;



import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sunjy.secret.boot.domain.ResultVO;
import com.sunjy.secret.boot.domain.TCPdcDetail;
import com.sunjy.secret.boot.domain.TPRole;
import com.sunjy.secret.boot.domain.TPUser;
import com.sunjy.secret.boot.service.pdc.PdcService;
import com.sunjy.secret.boot.service.pdc.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/pdc")
public class PdcController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private PdcService pdcService;

    @Autowired
    private UserService userService;

    /**
     * 添加教师数据详情
     * */
    @PostMapping("/addteacher")
    public String addTeacher(@ModelAttribute TCPdcDetail pd){

        pdcService.saveTeacher(pd);
        return "redirect:/teach/main";
    }
/**
 * 修改教师数据详情
 * */
    @PostMapping("/updateteacher")
    public String updateTeacher(@ModelAttribute TCPdcDetail pd){

        pdcService.updateTeacher(pd);
        return "redirect:/teach/main";
    }

  /**
   * 匹配用户信息
   * */
    @GetMapping("/mateMain")
    public ModelAndView mateMain(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/pdc/inforMate");
        mav.addObject("lu",userService.findUser());
        mav.addObject("lt",pdcService.findAllTeacher());
    return mav;
    }


    @GetMapping("/tableInfor")
    @ResponseBody
    public JSONObject tableInfor(){
        ResultVO vo =new ResultVO();
        vo.setMsg("用户匹配列表");
        vo.setCode(1);
        JSONArray ja= new JSONArray();
        List<Map<String ,Object>> lm=pdcService.GetInforMate();
        ja= JSONArray.parseArray(JSON.toJSONString(lm));
        if(ja.size()>0) {
            vo.setCount(ja.size());vo.setCode(0);vo.setData(ja);
        }
        return vo.toJSONObject();
    }

    @PostMapping("/inforAdd")
    public String inforAdd(){
        System.out.println("===========");
        return "redirect:/pdc/mateMain";
    }

    @GetMapping("/inforDetail/{type}")
    public ModelAndView inforDetail(@PathVariable("type") int type){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/pdc/inforDetail");

        if(type==0){
            request.setAttribute("type","add");
        }else{
            request.setAttribute("type","update");
            TPUser user=(TPUser)request.getSession().getAttribute("user");
            System.out.println(user.getId());
            TCPdcDetail tp=pdcService.findById(user.getId());
            mav.addObject("tp",tp);
        }

        return mav;
    }

}
