package com.sunjy.secret.boot.mapper;

import com.sunjy.secret.boot.domain.TBbsComment;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
import java.util.Map;

public interface TBbsCommentMapper extends MyMapper<TBbsComment> {
    @Select("SELECT bc.creat_time creat_time,bc.remark remark ,pu.alias_name  alias_name FROM  t_bbs_comment bc INNER JOIN t_p_user pu ON bc.user_id =pu.id WHERE bc.flat_id=0 AND bc.title_id=#{id}")
    public List<Map<String,String>> detailAll(@Param("id") Long tid);

    @Select("SELECT pu.alias_name alias_name,bc.remark , TIMESTAMPDIFF(DAY,bc.creat_time,NOW())+1 DAY FROM t_bbs_comment bc INNER JOIN t_p_user pu ON bc.user_id=pu.id INNER JOIN t_bbs_title  bt ON bt.id=bc.title_id WHERE bt.user_id=#{id}")
    public List<Map<String,String>> callBack(@Param("id") Long tid);
}