package com.sunjy.secret.boot.service.pdc;

import com.sunjy.secret.boot.domain.TPUser;
import com.sunjy.secret.boot.domain.TPdcUserRole;
import com.sunjy.secret.boot.mapper.TPUserMapper;
import com.sunjy.secret.boot.mapper.TPdcUserRoleMapper;
import com.sunjy.secret.boot.util.PasswordEncryptor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private TPUserMapper tpUserMapper;

    @Autowired
    private TPdcUserRoleMapper tpdcUserRoleMapper;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
/**
 * 创建用户
 * */
    public long creatUser(TPUser tpUser){
        String pass=tpUser.getUserPass();
        String salt = UUID.randomUUID().toString();
        PasswordEncryptor encoderMd5 = new PasswordEncryptor(salt, "MD5");
        String encodedPassword = encoderMd5.encode(pass);
        tpUser.setSalt(salt);
        tpUser.setCreatTime(new Date());
        tpUser.setRemarks(pass);
        tpUser.setUserPass(encodedPassword);
        int i=tpUserMapper.insertSelective(tpUser);
        return i;
    };
    /**
     * 判断用户是否存在
     * */
    public TPUser JudgeUser(TPUser tpUser){
        TPUser flat=null;
        TPUser tpUserTep=tpUserMapper.GetSalt(tpUser.getUserName());
        if(tpUserTep==null){
            return flat;
        }else {
            String salt = tpUserTep.getSalt();
            PasswordEncryptor encoderMd5 = new PasswordEncryptor(salt, "MD5");
            String encodedPassword = encoderMd5.encode(tpUser.getUserPass());
            tpUserTep.setUserPass(encodedPassword);
            int i=tpUserMapper.selectCount(tpUserTep);
            if (i==1) {
                flat = tpUserTep;
            }
            return flat;
        }
    }

    public List<TPUser> findUser(){
        List<TPUser> lu= tpUserMapper.selectAll();
        return lu;
    }

    public  TPUser getUser(Long id){
        TPUser u=tpUserMapper.selectByPrimaryKey(id);
        return  u;
    }

    public  int saveUserRole(TPdcUserRole ur){

        return  tpdcUserRoleMapper.insert(ur);
    }
}
