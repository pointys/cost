package com.nm.cost.service.finance;

import com.nm.cost.model.SalaryChart;
import com.nm.cost.model.SalaryRecord;

import java.util.List;

/**
 * @Author: 韩老魔
 * @Date: 2019/2/24 0024 10:46
 */
public interface ISalaryService {
    /**
     * 添加薪资
     * @param salary
     * @return
     */
    boolean addSalary(SalaryRecord salary);

    /**
     * 根据发放日期和userId查询薪资
     * @param salary
     * @return
     */
    List<SalaryRecord> querySalary(SalaryRecord salary);

    List<SalaryRecord> querySalaryByUserNameOrSalaryMonth(SalaryRecord salary);

    /**
     * 薪资报表
     * @return
     */
    List<SalaryChart> querysalaryChart();
}
