package com.sunjy.secret.boot.domain;

import javax.persistence.*;

@Table(name = "DemoClass.t_c_pdc_detail")
public class TCPdcDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 教师名称
     */
    @Column(name = "pdc_name")
    private String pdcName;

    /**
     * 性别
     */
    private Integer gender;

    /**
     * 联系方式
     */
    private String contact;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 签名
     */
    private String creed;

    /**
     * 学历
     */
    private String education;

    /**
     * 头像
     */
    @Column(name = "photo_path")
    private String photoPath;

    /**
     * 档案
     */
    @Column(name = "archives_path")
    private String archivesPath;

    /**
     * 标注
     */
    @Column(name = "flat_id")
    private Integer flatId;

    private String remark;

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
     * 获取教师名称
     *
     * @return pdc_name - 教师名称
     */
    public String getPdcName() {
        return pdcName;
    }

    /**
     * 设置教师名称
     *
     * @param pdcName 教师名称
     */
    public void setPdcName(String pdcName) {
        this.pdcName = pdcName;
    }

    /**
     * 获取性别
     *
     * @return gender - 性别
     */
    public Integer getGender() {
        return gender;
    }

    /**
     * 设置性别
     *
     * @param gender 性别
     */
    public void setGender(Integer gender) {
        this.gender = gender;
    }

    /**
     * 获取联系方式
     *
     * @return contact - 联系方式
     */
    public String getContact() {
        return contact;
    }

    /**
     * 设置联系方式
     *
     * @param contact 联系方式
     */
    public void setContact(String contact) {
        this.contact = contact;
    }

    /**
     * 获取邮箱
     *
     * @return email - 邮箱
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置邮箱
     *
     * @param email 邮箱
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取签名
     *
     * @return creed - 签名
     */
    public String getCreed() {
        return creed;
    }

    /**
     * 设置签名
     *
     * @param creed 签名
     */
    public void setCreed(String creed) {
        this.creed = creed;
    }

    /**
     * 获取学历
     *
     * @return education - 学历
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置学历
     *
     * @param education 学历
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * 获取头像
     *
     * @return photo_path - 头像
     */
    public String getPhotoPath() {
        return photoPath;
    }

    /**
     * 设置头像
     *
     * @param photoPath 头像
     */
    public void setPhotoPath(String photoPath) {
        this.photoPath = photoPath;
    }

    /**
     * 获取档案
     *
     * @return archives_path - 档案
     */
    public String getArchivesPath() {
        return archivesPath;
    }

    /**
     * 设置档案
     *
     * @param archivesPath 档案
     */
    public void setArchivesPath(String archivesPath) {
        this.archivesPath = archivesPath;
    }

    /**
     * 获取标注
     *
     * @return flat_id - 标注
     */
    public Integer getFlatId() {
        return flatId;
    }

    /**
     * 设置标注
     *
     * @param flatId 标注
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
}