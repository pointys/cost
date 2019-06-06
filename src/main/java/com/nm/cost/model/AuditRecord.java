package com.nm.cost.model;

import java.io.Serializable;
import java.util.Date;

/**
 * t_audit_record
 * @author 
 */
public class AuditRecord implements Serializable {
    private Integer auditId;

    private Integer userId;

    private Integer expenseId;

    private String auditState;

    private String auditSugg;

    private Date auditDate;

    //联表字段 审核人姓名
    private String userName;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "AuditRecord{" +
                "auditId=" + auditId +
                ", userId=" + userId +
                ", expenseId=" + expenseId +
                ", auditState='" + auditState + '\'' +
                ", auditSugg='" + auditSugg + '\'' +
                ", auditDate=" + auditDate +
                ", userName='" + userName + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getAuditId() {
        return auditId;
    }

    public void setAuditId(Integer auditId) {
        this.auditId = auditId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public String getAuditState() {
        return auditState;
    }

    public void setAuditState(String auditState) {
        this.auditState = auditState;
    }

    public String getAuditSugg() {
        return auditSugg;
    }

    public void setAuditSugg(String auditSugg) {
        this.auditSugg = auditSugg;
    }

    public Date getAuditDate() {
        return auditDate;
    }

    public void setAuditDate(Date auditDate) {
        this.auditDate = auditDate;
    }
}