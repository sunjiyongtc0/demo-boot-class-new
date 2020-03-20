package com.sunjy.secret.boot.util;

import com.sunjy.secret.boot.domain.TPUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 为html提供方法
 *
 * */
public class MethodService {
    /**
    *判断是否包含当前用户id
    * */
    public boolean contains(String id, List<TPUser> lu){
        List<String> ids = new ArrayList<String>();
        for(TPUser u : lu){
            ids.add(u.getId()+"");
        }
        return (ids.contains(id));
    }

}
