package com.sunjy.secret.boot.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "DemoClass.t_c_course")
public class TCCourse {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "couerse_name")
    private String couerseName;

    /**
     * 金额
     */
    @Column(name = "charges_amount")
    private Float chargesAmount;

    /**
     * 课时
     */
    @Column(name = "charges_unit")
    private String chargesUnit;

    /**
     * 是否启用0启用1禁用
     */
    @Column(name = "flat_id")
    private Integer flatId;

    @Column(name = "user_id")
    private Long userId;

    /**
     * 优惠
     */
    @Column(name = "dis_count")
    private Float disCount;

    /**
     * 分配教师组
     */
    @Column(name = "teach_id")
    private String teachId;

    /**
     * 分配用户id
     */
    @Column(name = "distribute_user_id")
    private Long distributeUserId;

    /**
     * 最后更新时间
     */
    @Column(name = "update_time")
    private Date updateTime;

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
     * @return couerse_name
     */
    public String getCouerseName() {
        return couerseName;
    }

    /**
     * @param couerseName
     */
    public void setCouerseName(String couerseName) {
        this.couerseName = couerseName;
    }

    /**
     * 获取金额
     *
     * @return charges_amount - 金额
     */
    public Float getChargesAmount() {
        return chargesAmount;
    }

    /**
     * 设置金额
     *
     * @param chargesAmount 金额
     */
    public void setChargesAmount(Float chargesAmount) {
        this.chargesAmount = chargesAmount;
    }

    /**
     * 获取课时
     *
     * @return charges_unit - 课时
     */
    public String getChargesUnit() {
        return chargesUnit;
    }

    /**
     * 设置课时
     *
     * @param chargesUnit 课时
     */
    public void setChargesUnit(String chargesUnit) {
        this.chargesUnit = chargesUnit;
    }

    /**
     * 获取是否启用0启用1禁用
     *
     * @return flat_id - 是否启用0启用1禁用
     */
    public Integer getFlatId() {
        return flatId;
    }

    /**
     * 设置是否启用0启用1禁用
     *
     * @param flatId 是否启用0启用1禁用
     */
    public void setFlatId(Integer flatId) {
        this.flatId = flatId;
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
     * 获取优惠
     *
     * @return dis_count - 优惠
     */
    public Float getDisCount() {
        return disCount;
    }

    /**
     * 设置优惠
     *
     * @param disCount 优惠
     */
    public void setDisCount(Float disCount) {
        this.disCount = disCount;
    }

    /**
     * 获取分配教师组
     *
     * @return teach_id - 分配教师组
     */
    public String getTeachId() {
        return teachId;
    }

    /**
     * 设置分配教师组
     *
     * @param teachId 分配教师组
     */
    public void setTeachId(String teachId) {
        this.teachId = teachId;
    }

    /**
     * 获取分配用户id
     *
     * @return distribute_user_id - 分配用户id
     */
    public Long getDistributeUserId() {
        return distributeUserId;
    }

    /**
     * 设置分配用户id
     *
     * @param distributeUserId 分配用户id
     */
    public void setDistributeUserId(Long distributeUserId) {
        this.distributeUserId = distributeUserId;
    }

    /**
     * 获取最后更新时间
     *
     * @return update_time - 最后更新时间
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置最后更新时间
     *
     * @param updateTime 最后更新时间
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}