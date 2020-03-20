package com.sunjy.secret.boot.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "DemoClass.t_bbs_comment")
public class TBbsComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "title_id")
    private Long titleId;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "creat_time")
    private Date creatTime;

    private String remark;

    @Column(name = "flat_id")
    private Integer flatId;

    /**
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return title_id
     */
    public Long getTitleId() {
        return titleId;
    }

    /**
     * @param titleId
     */
    public void setTitleId(Long titleId) {
        this.titleId = titleId;
    }

    /**
     * @return user_id
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * @param userId
     */
    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * @return creat_time
     */
    public Date getCreatTime() {
        return creatTime;
    }

    /**
     * @param creatTime
     */
    public void setCreatTime(Date creatTime) {
        this.creatTime = creatTime;
    }

    /**
     * @return remark
     */
    public String getRemark() {
        return remark;
    }

    /**
     * @param remark
     */
    public void setRemark(String remark) {
        this.remark = remark;
    }

    /**
     * @return flat_id
     */
    public Integer getFlatId() {
        return flatId;
    }

    /**
     * @param flatId
     */
    public void setFlatId(Integer flatId) {
        this.flatId = flatId;
    }
}