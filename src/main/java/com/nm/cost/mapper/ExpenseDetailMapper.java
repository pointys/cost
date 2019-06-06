package com.nm.cost.mapper;

import com.nm.cost.model.ExpenseDetail;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ExpenseDetailMapper {


    int insert(@Param("expenseDetailList") List<ExpenseDetail> expenseDetailList);

    @Select("select ted.*,ts.costName from t_expense_detail ted inner join t_cost ts on ted.costId=ts.costId where ted.expenseId=#{expenseId}")
    List<ExpenseDetail> queryExpenseDetails(@Param("expenseId") int expenseId);

}