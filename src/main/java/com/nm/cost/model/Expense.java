package com.nm.cost.model;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

/**
 * t_expense
 * @author 
 */
public class Expense implements Serializable {
    private Integer expenseId;

    private Integer userId;

    private String expenseName;

    private String expenseDesc;

    private Float expenseTotal;

    private Date expenseDate;

    private String expenseState;

    //建议一对多
    //多条报销明细信息cost
    private Integer[] costIds;
    private Float[] detailMoneys;
    private String[] detailDescs;

    //用于经理查询报销单条件的属性
    private String userName;//报销人姓名
    private String startDate;//报销开始时间
    private String endDate;//报销结束时间

    //用于判断是显示查看详情还是修改的操作
    private String operate;

    public int getExpenseId() {
        return expenseId;
    }
    public void setExpenseId(int expenseId) {
        this.expenseId = expenseId;
    }
    public int getUserId() {
        return userId;
    }
    public void setUserId(int userId) {
        this.userId = userId;
    }
    public String getExpenseName() {
        return expenseName;
    }
    public void setExpenseName(String expenseName) {
        this.expenseName = expenseName;
    }
    public String getExpenseDesc() {
        return expenseDesc;
    }
    public void setExpenseDesc(String expenseDesc) {
        this.expenseDesc = expenseDesc;
    }
    public float getExpenseTotal() {
        return expenseTotal;
    }
    public void setExpenseTotal(float expenseTotal) {
        this.expenseTotal = expenseTotal;
    }
    public Date getExpenseDate() {
        return expenseDate;
    }
    public void setExpenseDate(Date expenseDate) {
        this.expenseDate = expenseDate;
    }
    public String getExpenseState() {
        return expenseState;
    }
    public void setExpenseState(String expenseState) {
        this.expenseState = expenseState;
    }
    public Integer[] getCostIds() {
        return costIds;
    }
    public void setCostIds(Integer[] costIds) {
        this.costIds = costIds;
    }
    public Float[] getDetailMoneys() {
        return detailMoneys;
    }
    public void setDetailMoneys(Float[] detailMoneys) {
        this.detailMoneys = detailMoneys;
    }
    public String[] getDetailDescs() {
        return detailDescs;
    }
    public void setDetailDescs(String[] detailDescs) {
        this.detailDescs = detailDescs;
    }

    public String getUserName() {
        return userName;
    }
    public void setUserName(String userName) {
        this.userName = userName;
    }
    public String getStartDate() {
        return startDate;
    }
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }
    public String getEndDate() {
        return endDate;
    }
    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOperate() {
        return operate;
    }
    public void setOperate(String operate) {
        this.operate = operate;
    }

    @Override
    public String toString() {
        return "Expense{" +
                "expenseId=" + expenseId +
                ", userId=" + userId +
                ", expenseName='" + expenseName + '\'' +
                ", expenseDesc='" + expenseDesc + '\'' +
                ", expenseTotal=" + expenseTotal +
                ", expenseDate=" + expenseDate +
                ", expenseState='" + expenseState + '\'' +
                ", costIds=" + Arrays.toString(costIds) +
                ", detailMoneys=" + Arrays.toString(detailMoneys) +
                ", detailDescs=" + Arrays.toString(detailDescs) +
                ", userName='" + userName + '\'' +
                ", startDate='" + startDate + '\'' +
                ", endDate='" + endDate + '\'' +
                ", operate='" + operate + '\'' +
                '}';
    }
}