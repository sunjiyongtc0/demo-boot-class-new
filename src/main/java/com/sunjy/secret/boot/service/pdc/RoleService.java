package com.sunjy.secret.boot.service.pdc;

import com.sunjy.secret.boot.domain.TPRole;
import com.sunjy.secret.boot.mapper.TPRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class RoleService {

    @Autowired
    private TPRoleMapper tpRoleMapper;

    private static final Logger logger = LoggerFactory.getLogger(RoleService.class);

    public List<TPRole> findUser(){
        List<TPRole> lr= tpRoleMapper.selectAll();
        return lr;
    }
    public int creatRole(TPRole tpRole){
        tpRole.setCreatTime(new Date());
        int i=tpRoleMapper.insertSelective(tpRole);
        return i;
    };
}
