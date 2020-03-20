package com.sunjy.secret.boot.mapper;

import com.sunjy.secret.boot.domain.TBbsTitle;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import tk.mybatis.mapper.MyMapper;

import java.util.List;
import java.util.Map;

public interface TBbsTitleMapper extends MyMapper<TBbsTitle> {
    @Select("SELECT   bt.id id,pu.alias_name alias_name,bt.creat_time creat_time,bt.title_name title_name FROM t_bbs_title bt INNER JOIN t_p_user pu ON bt.user_id=pu.id WHERE  bt.flat_id=0  ")
    public List<Map<String,String>> titleBbs();
    @Select("SELECT   bt.id id,pu.alias_name alias_name,bt.creat_time creat_time,bt.title_name title_name FROM t_bbs_title bt INNER JOIN t_p_user pu ON bt.user_id=pu.id WHERE  bt.flat_id=0 AND bt.user_id=#{id}")
    public List<Map<String,String>> creatTitleBbs(@Param("id")long id);
    @Select("SELECT DISTINCT bt.id id, bt.title_name ,bt.remark ,pu.alias_name ,bt.creat_time creat_time FROM t_bbs_title  bt INNER JOIN t_bbs_comment bc ON bt.id=bc.title_id INNER JOIN t_p_user pu ON bt.user_id=pu.id  WHERE bt.flat_id=0 AND bc.user_id=#{id}")
    public List<Map<String,String>> joinTitleBbs(@Param("id")long id);
}