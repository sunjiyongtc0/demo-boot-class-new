package com.sunjy.secret.boot.service.curriculum;

import com.sunjy.secret.boot.domain.TCCourse;
import com.sunjy.secret.boot.domain.TCPdcDetail;
import com.sunjy.secret.boot.domain.TCTeach;
import com.sunjy.secret.boot.domain.TPUser;
import com.sunjy.secret.boot.mapper.TCCourseMapper;
import com.sunjy.secret.boot.mapper.TCTeachMapper;
import com.sunjy.secret.boot.mapper.TPUserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeachService {
    @Autowired
    private TCCourseMapper tcCourseMapper;
    @Autowired
    private TPUserMapper tpUserMapper;
    @Autowired
    private TCTeachMapper tcTeachMapper;

    public TCCourse findCourse(long id){
        TCCourse tcCourse=tcCourseMapper.selectByPrimaryKey(id);
        return tcCourse;
    }
    public TPUser findUser(long id){
        TPUser tpUser=tpUserMapper.selectByPrimaryKey(id);
        return tpUser;
    }
    public List<TPUser> findAllTeach(){
        List<TPUser> lu=tpUserMapper.selectAll();
        return lu;
    }
    public List<TPUser> findAllStudent(){
        List<TPUser> lu=tpUserMapper.selectAll();
        return lu;
    }

    public  int  add(TCTeach tcTeach){
        int i=0;
        i=tcTeachMapper.insertSelective(tcTeach);
        return  i;
    }


}
