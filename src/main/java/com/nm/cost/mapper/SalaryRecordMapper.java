package com.nm.cost.mapper;

import com.nm.cost.model.SalaryChart;
import com.nm.cost.model.SalaryRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import java.util.List;

@Mapper
public interface SalaryRecordMapper {

    /**
     * 插入salaryRecord
     * @param salary
     * @return
     */
    @Insert("insert into t_salary_record (userId,salaryMonth,salaryDate,salaryBasic,salaryComm) values(#{salary.userId},#{salary.salaryMonth},now(),#{salary.salaryBasic},#{salary.salaryComm})")
    int insertSalary(@Param("salary") SalaryRecord salary);

    /**
     * 查询所有
     * @param salary
     * @return
     */
    @Select("select tsr.*,ts.userName from t_salary_record tsr inner join t_users ts on tsr.userId=ts.userId where ts.userMark=0")
    List<SalaryRecord> querySalarys();

    /**
     * 根据userId或者日期查询
     * @param salary
     * @return
     */
    List<SalaryRecord> querySalaryByUserIdOrSalaryMonth(SalaryRecord salary);

    /**
     * 根据userName或者日期查询
     * @param salary
     * @return
     */
    List<SalaryRecord> querySalaryByUserNameOrSalaryMonth(SalaryRecord salary);

    @Select("SELECT salaryMonth,SUM(salaryBasic) as salaryBasicTotal,SUM(salaryComm) as salaryCommTotal from t_salary_record GROUP BY salaryMonth")
    List<SalaryChart> querySalaryChart();
}