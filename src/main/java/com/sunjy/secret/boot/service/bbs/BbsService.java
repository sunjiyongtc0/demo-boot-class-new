package com.sunjy.secret.boot.service.bbs;

import com.sunjy.secret.boot.domain.TBbsComment;
import com.sunjy.secret.boot.domain.TBbsTitle;
import com.sunjy.secret.boot.domain.TCCourse;
import com.sunjy.secret.boot.mapper.TBbsCommentMapper;
import com.sunjy.secret.boot.mapper.TBbsTitleMapper;
import com.sunjy.secret.boot.mapper.TCCourseMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class BbsService {

    @Autowired
    private TBbsTitleMapper tBbsTitleMapper;

    @Autowired
    private TBbsCommentMapper tBbsCommentMapper;

    public List<Map<String ,String>> findTitle(){
        List<Map<String ,String>> lm=tBbsTitleMapper.titleBbs();
        return lm;
    }

    public List<Map<String ,String>> findComment(long tid ){
        List<Map<String ,String>> lm=tBbsCommentMapper.detailAll(tid);
        return lm;
    }

    public TBbsTitle findTitleById(long tid){
        TBbsTitle tbt=tBbsTitleMapper.selectByPrimaryKey(tid);
        return tbt;
    }
    public int saveTBbsComment(TBbsComment tbc){
        int i=tBbsCommentMapper.insert(tbc);
        return i;
    }

    public List<Map<String ,String>> findCreat(long tid ){
        List<Map<String ,String>> lm=tBbsTitleMapper.creatTitleBbs(tid);
        return lm;
    }

    public List<Map<String ,String>> findJoin(long tid ){
        List<Map<String ,String>> lm=tBbsTitleMapper.joinTitleBbs(tid);
        return lm;
    }

    public List<Map<String ,String>> findMessage(long tid ){
        List<Map<String ,String>> lm=tBbsCommentMapper.callBack(tid);
        return lm;
    }

    public int saveTBbsTitle(TBbsTitle tbt){
        int i=tBbsTitleMapper.insert(tbt);
        return i;
    }
}
