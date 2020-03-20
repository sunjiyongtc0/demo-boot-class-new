package com.sunjy.secret.boot.service.pdc;

import com.sunjy.secret.boot.domain.TCPdcDetail;
import com.sunjy.secret.boot.domain.TPRole;
import com.sunjy.secret.boot.mapper.TCPdcDetailMapper;
import com.sunjy.secret.boot.mapper.TPRoleMapper;
import com.sunjy.secret.boot.mapper.TPUserMapper;
import com.sunjy.secret.boot.mapper.TPdcUserRoleMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Map;

@Service
public class PdcService {

    @Autowired
    private TCPdcDetailMapper tcpdcMapper;

    @Autowired
    TPdcUserRoleMapper tPdcUserRoleMapper;

    @Autowired
    TPUserMapper tpUserMapper;

    private static final Logger logger = LoggerFactory.getLogger(PdcService.class);

    public  void  saveTeacher( TCPdcDetail tcPdcDetail){
        tcpdcMapper.insert(tcPdcDetail);
    }

    public  void  updateTeacher( TCPdcDetail tcPdcDetail){
        tcpdcMapper.updateByPrimaryKeySelective(tcPdcDetail);
    }

    public  List<TCPdcDetail> findAllTeacher(){
        List<TCPdcDetail> lt=tcpdcMapper.selectAll();
        return lt;
    }

    public  TCPdcDetail findById(long id){
        TCPdcDetail tp=  tcpdcMapper.selectByPrimaryKey(id);
        return  tp;
    }

    public  List<Map<String,Object>> findRoleUser(long id){
        List<Map<String,Object>>  lm=  tPdcUserRoleMapper.findRoleUsers(id);
        return lm;
    }

    public List<Map<String,Object>> GetInforMate(){
        List<Map<String,Object>>  lm=tpUserMapper.GetInforMate();
    return lm;
    }
}
