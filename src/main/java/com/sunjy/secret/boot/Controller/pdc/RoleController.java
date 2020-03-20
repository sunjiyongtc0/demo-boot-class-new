package com.sunjy.secret.boot.Controller.pdc;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.sunjy.secret.boot.domain.ResultVO;
import com.sunjy.secret.boot.domain.TPRole;
import com.sunjy.secret.boot.domain.TPUser;
import com.sunjy.secret.boot.domain.TPdcUserRole;
import com.sunjy.secret.boot.service.pdc.PdcService;
import com.sunjy.secret.boot.service.pdc.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private HttpServletResponse response;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PdcService pdcService;

    @GetMapping("/main")
    public ModelAndView RoleMain(){
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/pdc/roleMain");
        List<TPRole> lr=roleService.findUser();
        mav.addObject("list",lr);
        return mav;
    }
    @GetMapping("/registerRole")
    public String registerRole(){
        return "/pdc/registerRole";
    }
    @PostMapping("/registerRoleinto")
    public ModelAndView registerRoleInto(){
        String roleName=request.getParameter("role_name");
        String powerId=request.getParameter("power_id");
        TPRole tpRole=new TPRole();
        tpRole.setRoleName(roleName);
        tpRole.setPowerId(powerId);
        TPUser user=(TPUser)request.getSession().getAttribute("user");
        tpRole.setCreatUser(user.getId().toString());
        int i=roleService.creatRole(tpRole);
        ModelAndView mav=new ModelAndView();
        mav.setViewName("/pdc/roleMain");
        List<TPRole> lr=roleService.findUser();
        mav.addObject("list",lr);
        return mav;
    }

    @GetMapping("/getjsonData")
    @ResponseBody
    public JSONObject getJsonData(){
        ResultVO vo= new ResultVO();
        vo.setMsg("角色列表");
        vo.setCode(1);
        JSONArray ja= new JSONArray();
        List<TPRole> lu=roleService.findUser();
        ja= JSONArray.parseArray(JSON.toJSONString(lu));
        if(ja.size()>0) {
            vo.setCount(ja.size());vo.setCode(0);vo.setData(ja);
        }
        return vo.toJSONObject();

    }

    @GetMapping("/userRole")
    public String  userRole(){
        List<TPRole> lu=roleService.findUser();
        request.setAttribute("lr",lu);
        return "/pdc/userRole";
    }

    @GetMapping("/roleUsers/{id}")
    public String  roleUsers(@PathVariable("id") long id){
        List<Map<String,Object>> lu=pdcService.findRoleUser(id);
        request.setAttribute("lr",lu);
        return "/pdc/roleUser";
    }

}
