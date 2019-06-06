package com.nm.cost.service.finance.impl;

import com.nm.cost.mapper.SalaryRecordMapper;
import com.nm.cost.model.SalaryChart;
import com.nm.cost.model.SalaryRecord;
import com.nm.cost.service.finance.ISalaryService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Author: 韩老魔
 * @Date: 2019/2/24 0024 10:46
 */
@Slf4j
@Transactional
@Service
public class SalaryService implements ISalaryService {
    @Autowired
    private SalaryRecordMapper salaryRecordMapper;

    @Override
    public boolean addSalary(SalaryRecord salary) {
        if(salaryRecordMapper.insertSalary(salary)>0){
            return true;
        }
        return false;
    }

    @Override
    public List<SalaryRecord> querySalary(SalaryRecord salary) {
        return salaryRecordMapper.querySalaryByUserIdOrSalaryMonth(salary);
    }

    @Override
    public List<SalaryRecord> querySalaryByUserNameOrSalaryMonth(SalaryRecord salary) {
        return salaryRecordMapper.querySalaryByUserNameOrSalaryMonth(salary);
    }

    @Override
    public List<SalaryChart> querysalaryChart() {
        return salaryRecordMapper.querySalaryChart();
    }
}
