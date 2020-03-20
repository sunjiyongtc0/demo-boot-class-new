package com.sunjy.secret.boot.service.curriculum;

import com.sunjy.secret.boot.domain.TCCourse;
import com.sunjy.secret.boot.mapper.TCCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class CourseService {

    @Autowired
    private  TCCourseMapper tcCourseMapper;

    public List<TCCourse> findCourse(){
        List<TCCourse> lt=tcCourseMapper.selectAll();
        return lt;
    }

    public  int creatCourse(TCCourse tcCourse){
        int i=0;
        i=tcCourseMapper.insertSelective(tcCourse);
    return i;
    }

    public  int updateCouerse( TCCourse Course){
        int i=0;
        tcCourseMapper.updateCouerse(Course);
        return i;
    }

    public  List<Map<String,Object>> findJoin(long id){
        return  tcCourseMapper.findJoin(id);
    }
}
