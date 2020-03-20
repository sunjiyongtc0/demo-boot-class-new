package com.sunjy.secret.boot.domain;

import java.util.Date;
import javax.persistence.*;

@Table(name = "DemoClass.t_p_role")
public class TPRole {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "role_name")
    private String roleName;

    @Column(name = "power_id")
    private String powerId;

    @Column(name = "creat_user")
    private String creatUser;

    @Column(name = "creat_time")
    private Date creatTime;

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
     * @return role_name
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * @param roleName
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    /**
     * @return power_id
     */
    public String getPowerId() {
        return powerId;
    }

    /**
     * @param powerId
     */
    public void setPowerId(String powerId) {
        this.powerId = powerId;
    }

    /**
     * @return creat_user
     */
    public String getCreatUser() {
        return creatUser;
    }

    /**
     * @param creatUser
     */
    public void setCreatUser(String creatUser) {
        this.creatUser = creatUser;
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
}