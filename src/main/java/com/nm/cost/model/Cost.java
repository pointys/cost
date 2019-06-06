package com.nm.cost.model;

import lombok.Data;

import java.io.Serializable;
import java.util.Arrays;

/**
 * t_cost
 * @author 
 */
public class Cost implements Serializable {
    private Integer costId;

    private String costName;

    private String costDesc;

    private String costMark;

    private static final long serialVersionUID = 1L;
    // 数据库外用于删除操作
    private String ck;

    //选中costId进行操作的数组
    private Integer[] ids;


    public Integer[] getIds() {
        return ids;
    }

    public void setIds(Integer[] ids) {
        this.ids = ids;
    }

    public String getCk() {
        ck="<input type='checkbox' name='ids' value='"+this.getCostId()+"'>";
        return ck;
    }

    public void setCk(String ck) {
        this.ck = ck;
    }

    public Integer getCostId() {
        return costId;
    }

    public void setCostId(Integer costId) {
        this.costId = costId;
    }

    public String getCostName() {
        return costName;
    }

    public void setCostName(String costName) {
        this.costName = costName;
    }

    public String getCostDesc() {
        return costDesc;
    }

    public void setCostDesc(String costDesc) {
        this.costDesc = costDesc;
    }

    public String getCostMark() {
        return costMark;
    }

    public void setCostMark(String costMark) {
        this.costMark = costMark;
    }


    @Override
    public String toString() {
        return "Cost{" +
                "costId=" + costId +
                ", costName='" + costName + '\'' +
                ", costDesc='" + costDesc + '\'' +
                ", costMark='" + costMark + '\'' +
                ", ck='" + ck + '\'' +
                ", ids=" + Arrays.toString(ids) +
                '}';
    }
}