package com.nm.cost.service.system;

import com.nm.cost.model.Cost;

import java.util.List;

/**
 * @Author: 韩老魔
 * @Date: 2019/2/24 0024 12:13
 */
public interface ICostService {

    boolean addCost(Cost cost);

    /**
     * 根据costId数组做假删除
     * @param ids
     * @return
     */
    boolean deleteCost(Integer[] ids);

    /**
     * 根据costId修改costName及costDesc
     * @param cost
     * @return
     */
    boolean updateCost(Cost cost);

    /**
     * 根据costId和costName查询cost列表
     * @param cost
     * @return
     */
    List<Cost> queryCostByCostIdOrByCostName(Cost cost);

    /**
     * 查询所有费用列表
     * @param cost
     * @return
     */
    List<Cost> queryCosts();
}
