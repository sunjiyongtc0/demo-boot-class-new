package com.sunjy.secret.boot.mapper;

import com.sunjy.secret.boot.domain.TCCourse;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
import java.util.Map;

public interface TCCourseMapper extends MyMapper<TCCourse> {

    @Update("update t_c_course set teach_id=#{t.teachId},distribute_user_id= #{t.distributeUserId},update_time=#{t.updateTime}  where id=#{t.id}")
    public int updateCouerse(@Param("t") TCCourse t);


    @Select("SELECT cc.couerse_name,cc.charges_amount,cc.charges_unit ,pd.pdc_name,pd.photo_path FROM t_c_teach ct INNER JOIN t_c_course cc ON cc.id=ct.course_id INNER JOIN  t_c_pdc_detail pd ON ct.teach_id=pd.id  where  FIND_IN_SET(#{id},ct.student_id)  ")
    public List<Map<String,Object>> findJoin(long id);
}