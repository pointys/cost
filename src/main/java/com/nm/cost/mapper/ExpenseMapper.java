package com.nm.cost.mapper;

import com.nm.cost.model.Expense;
import com.nm.cost.model.ExpenseChart;
import org.apache.ibatis.annotations.*;

import javax.xml.soap.Detail;
import java.util.List;

@Mapper
public interface ExpenseMapper {

    //主键查询
    @Select(" select te.*,tu.userName from t_expense te inner join t_users tu on te.userId=tu.userId where te.expenseId=#{expense.expenseId}")
    Expense queryExpenseById(@Param("expense") Expense expense);

    @Select(" select te.*,tu.userName from t_expense te inner join t_users tu on te.userId=tu.userId where te.userId=#{expense.userId}")
    List<Expense> queryExpenseByUserId(@Param("expense") Expense expense);

    //动态sql查询
    List<Expense> queryExpense(Expense expense);


    @Insert("insert into t_expense(userId,expenseName,expenseDesc,expenseTotal,expenseDate,expenseState) values (#{expense.userId},#{expense.expenseName},#{expense.expenseDesc},#{expense.expenseTotal},now(),#{expense.expenseState})")
    int insert(@Param("expense") Expense expense);

    //获取最后插入的主键值
    int getLastId();

    @Update("update t_expense set expenseState=#{expenseState} where expenseId=#{expenseId}")
    int updateExpenseStateByExpenseId(@Param("expenseId") Integer expenseId, @Param("expenseState") String expenseState);

    //查询不同costId的费用总和
    @Select("SELECT ed.costId,sum(e.expenseTotal) as expenseTotal FROM t_expense e LEFT JOIN t_expense_detail ed on e.expenseId=ed.expenseId WHERE e.expenseState=3 GROUP BY ed.costId")
    List<ExpenseChart> expenseChart();
}