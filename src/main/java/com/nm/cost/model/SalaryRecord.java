package com.nm.cost.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * t_salary_record
 *
 * @author
 */
public class SalaryRecord implements Serializable {
    private Integer salaryId;

    private Integer userId;

    @DateTimeFormat(pattern="yyyy-MM")
    private Date salaryMonth;

    private Date salaryDate;

    private Float salaryBasic;

    private Float salaryComm;

    //联表字段
    private String userName;

    //方便前端显示
    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "SalaryRecord{" +
                "salaryId=" + salaryId +
                ", userId=" + userId +
                ", salaryMonth=" + salaryMonth +
                ", salaryDate=" + salaryDate +
                ", salaryBasic=" + salaryBasic +
                ", salaryComm=" + salaryComm +
                ", userName='" + userName + '\'' +
                '}';
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Integer getSalaryId() {
        return salaryId;
    }

    public void setSalaryId(Integer salaryId) {
        this.salaryId = salaryId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Date getSalaryMonth() {
        return salaryMonth;
    }

    public void setSalaryMonth(Date salaryMonth) {
        this.salaryMonth = salaryMonth;
    }

    public Date getSalaryDate() {
        return salaryDate;
    }

    public void setSalaryDate(Date salaryDate) {
        this.salaryDate = salaryDate;
    }

    public Float getSalaryBasic() {
        return salaryBasic;
    }

    public void setSalaryBasic(Float salaryBasic) {
        this.salaryBasic = salaryBasic;
    }

    public Float getSalaryComm() {
        return salaryComm;
    }

    public void setSalaryComm(Float salaryComm) {
        this.salaryComm = salaryComm;
    }
}