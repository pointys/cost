package com.nm.cost.mapper;

import com.nm.cost.model.Cost;
import com.nm.cost.model.Expense;
import org.apache.ibatis.annotations.*;

import java.sql.SQLException;
import java.util.List;

@Mapper
public interface CostMapper {

    /**
     * 简写
     * @param cost
     * @return
     */
    @Insert("insert into t_cost(costName,costDesc,costMark) values(#{costName,jdbcType=VARCHAR},#{costDesc,jdbcType=VARCHAR},'0')")
    int addCost(Cost cost);

    /**
     * 对于多个参数来说，每个参数之前都要加上@Param注解，
     * 要不然会找不到对应的参数进而报错
     */
    int deleteCost(@Param("ids") Integer[] ids);

    /**
     * 根据costId修改
     */
    int updateCost(Cost cost) throws SQLException;

    /**
     * 根据CostIdOr或CostName
     * 条件查询查询
     * @param cost
     * @return
     */
    List<Cost> queryCostByCostIdOrByCostName(Cost cost);

    @Select("select * from t_cost where costMark='0'")
    List<Cost> queryCosts();

}