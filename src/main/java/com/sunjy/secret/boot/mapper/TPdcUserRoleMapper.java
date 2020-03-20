package com.sunjy.secret.boot.mapper;

import com.sunjy.secret.boot.domain.TPdcUserRole;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
import java.util.Map;

public interface TPdcUserRoleMapper extends MyMapper<TPdcUserRole> {
    @Select("SELECT pur.user_id,u.alias_name FROM  t_pdc_user_role pur INNER JOIN  t_p_user u ON pur.user_id=u.id  WHERE pur.role_id=#{id}")
    public List<Map<String,Object>> findRoleUsers(@Param("id") Long id);
}