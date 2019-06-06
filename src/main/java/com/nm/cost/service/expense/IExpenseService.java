package com.nm.cost.service.expense;

import com.nm.cost.model.AuditRecord;
import com.nm.cost.model.Expense;
import com.nm.cost.model.ExpenseChart;
import com.nm.cost.model.ExpenseDetail;

import javax.xml.soap.Detail;
import java.util.List;

/**
 * @Author: 韩老魔
 * @Date: 2019/3/13 0013 14:21
 */
public interface IExpenseService {
    boolean addExpense(Expense expense);

    List<Expense> queryExpense(Expense expense);

    Expense queryExpenseById(Expense expense);

    List<Expense> queryExpenseByUserId(Expense expense);

    List<ExpenseDetail> queryExpenseDetails(int expenseId);

    List<AuditRecord> queryAuditRecords(int expenseId);

    boolean auditExpense(AuditRecord audit);

    List<ExpenseChart> expenseChart();
}
