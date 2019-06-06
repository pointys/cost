package com.nm.cost.model;

import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Arrays;

/**
 * t_users
 * @author 
 */
@Slf4j
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    private Integer userId;

    private Integer roleId;

    private String userName;

    private String userSex;

    private Integer userAge;

    private String userPhone;

    private String userAccount;

    private String userPwd;

    private Float userSalary;

    private String userMark;

    // 非该表字段
    private String roleName;
    private String ck;//删除复选框的动态ids值,ids又是下面的数组属性
    private Integer[] ids;//批量删除时获取它们的name属性



    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public String getCk() {
        ck="<input type='checkbox' name='ids' value='"+this.getUserId()+"'>";
        return ck;
    }

    public void setCk(String ck) {
        this.ck = ck;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserSex() {
        return userSex;
    }

    public void setUserSex(String userSex) {
        this.userSex = userSex;
    }

    public Integer getUserAge() {
        return userAge;
    }

    public void setUserAge(Integer userAge) {
        this.userAge = userAge;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserAccount() {
        return userAccount;
    }

    public void setUserAccount(String userAccount) {
        this.userAccount = userAccount;
    }

    public String getUserPwd() {
        return userPwd;
    }

    public void setUserPwd(String userPwd) {
        this.userPwd = userPwd;
    }

    public Float getUserSalary() {
        return userSalary;
    }

    public void setUserSalary(Float userSalary) {
        this.userSalary = userSalary;
    }

    public String getUserMark() {
        return userMark;
    }

    public void setUserMark(String userMark) {
        this.userMark = userMark;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", roleId=" + roleId +
                ", userName='" + userName + '\'' +
                ", userSex='" + userSex + '\'' +
                ", userAge=" + userAge +
                ", userPhone='" + userPhone + '\'' +
                ", userAccount='" + userAccount + '\'' +
                ", userPwd='" + userPwd + '\'' +
                ", userSalary=" + userSalary +
                ", userMark='" + userMark + '\'' +
                ", roleName='" + roleName + '\'' +
                ", ids=" + Arrays.toString(ids) +
                '}';
    }
}