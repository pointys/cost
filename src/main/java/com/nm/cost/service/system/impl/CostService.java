package com.nm.cost.service.system.impl;

import com.nm.cost.mapper.CostMapper;
import com.nm.cost.model.Cost;
import com.nm.cost.service.system.ICostService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.SQLException;
import java.util.List;

/**
 * @Author: 韩老魔
 * @Date: 2019/2/24 0024 12:13
 */
@Slf4j
@Service
@Transactional
public class CostService implements ICostService {
    @Autowired
    private CostMapper costMapper;

    @Override
    public boolean addCost(Cost cost) {
        if(costMapper.addCost(cost)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteCost(Integer[] ids) {
        if(costMapper.deleteCost(ids)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean updateCost(Cost cost) {
        try {
            if(costMapper.updateCost(cost)>0){
                return true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<Cost> queryCostByCostIdOrByCostName(Cost cost) {
        return costMapper.queryCostByCostIdOrByCostName(cost);
    }

    @Override
    public List<Cost> queryCosts() {
        return costMapper.queryCosts();
    }

}
