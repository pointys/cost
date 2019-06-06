package com.nm.cost.model;

import java.io.Serializable;

/**
 * t_expense_detail
 * @author 
 */
public class ExpenseDetail implements Serializable {
    private Integer detailId;

    private Integer expenseId;

    private Integer costId;

    private String detailDesc;

    private Float detailMoney;

    //联表查询字段
    private String costName;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "ExpenseDetail{" +
                "detailId=" + detailId +
                ", expenseId=" + expenseId +
                ", costId=" + costId +
                ", detailDesc='" + detailDesc + '\'' +
                ", detailMoney=" + detailMoney +
                ", costName='" + costName + '\'' +
                '}';
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public Integer getDetailId() {
        return detailId;
    }

    public void setDetailId(Integer detailId) {
        this.detailId = detailId;
    }

    public Integer getExpenseId() {
        return expenseId;
    }

    public void setExpenseId(Integer expenseId) {
        this.expenseId = expenseId;
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public String getDetailDesc() {
        return detailDesc;
    }

    public void setDetailDesc(String detailDesc) {
        this.detailDesc = detailDesc;
    }

    public Float getDetailMoney() {
        return detailMoney;
    }

    public void setDetailMoney(Float detailMoney) {
        this.detailMoney = detailMoney;
    }
}