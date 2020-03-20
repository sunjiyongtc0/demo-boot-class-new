package com.sunjy.secret.boot.mapper;

import com.sunjy.secret.boot.domain.TPUser;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
import java.util.Map;

public interface TPUserMapper extends MyMapper<TPUser> {
    @Select("select * from t_p_user where user_name=#{userName}")
    public  TPUser GetSalt(@Param("userName") String userName);

    @Select("SELECT pd.id,pd.pdc_name ,p.user_name ,p.alias_name,pd.contact ,pd.gender,pd.education FROM  t_c_pdc_detail pd INNER JOIN  t_p_user p ON p.id=pd.remark")
    public List<Map<String ,Object>> GetInforMate();
}