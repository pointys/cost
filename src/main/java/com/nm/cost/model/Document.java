package com.nm.cost.model;

import lombok.Data;

import java.util.Date;

/**
 * @Author: 韩老魔
 * @Date: 2019/4/5 0005 17:47
 */
@Data
public class Document {
    private Integer file_id;
    private String file_name;
    private Long file_size;
    private Date file_date;
    private String file_type;
    private String file_url;
    private String file_mark;
    private Integer user_id;
    private String file_desc;
    //联表字段
    private String userName;
    //与file_date比较条件

    //string类型接收前端数据字段与file_date比较
    private String beginDate;
    private String endDate;

    //复选框字段
    private String ck;//删除复选框的动态ids值,ids又是下面的数组属性
    private Integer[] ids;//批量删除时获取它们的name属性
}
