package com.sunjy.secret.boot.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "DemoClass.t_bbs_title")
public class TBbsTitle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "title_name")
    private String titleName;

    @Column(name = "creat_time")
    private Date creatTime;

    @Column(name = "flat_id")
    private Integer flatId;

    private String remark;

    @Column(name = "count_num")
    private Integer countNum;

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
     * @return title_name
     */
    public String getTitleName() {
        return titleName;
    }

    /**
     * @param titleName
     */
    public void setTitleName(String titleName) {
        this.titleName = titleName;
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
     * @return count_num
     */
    public Integer getCountNum() {
        return countNum;
    }

    /**
     * @param countNum
     */
    public void setCountNum(Integer countNum) {
        this.countNum = countNum;
    }
}