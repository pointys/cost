package com.nm.cost.controller.finance;

import com.nm.cost.model.*;
import com.nm.cost.service.expense.impl.ExpenseService;
import com.nm.cost.service.finance.impl.SalaryService;
import com.nm.cost.service.system.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * @Author: 韩老魔
 * @Date: 2019/3/15 0015 18:44
 */
@Controller
@RequestMapping("/finance")
public class FinanceController {
    @Autowired
    private ExpenseService expenseService;
    @Autowired
    private UserService userService;
    @Autowired
    private SalaryService salaryService;

    @RequestMapping("/queryFinanceExpense")
    public String queryFinanceExpense(Model model, Expense expense) {
        System.out.println("接收审核expense:" + expense.toString());
        expense.setExpenseState("2");// 经理审核通过状态
        List<Expense> expenseList = expenseService.queryExpense(expense);

        for (Expense e :
                expenseList) {
            System.out.println(e.toString());
        }
        model.addAttribute("expenseList", expenseList);//经理审核通过分费用列表
        model.addAttribute("expense", expense);//查询条件
        return "finance/financAaudit/financeaudit_list";
    }

    @GetMapping("/financeAudit")
    public String financeAudit(Expense expense, Model model) {
        // 1.报销单
        expense = expenseService.queryExpenseById(expense);
        //2.费用明细
        List<ExpenseDetail> detailList = expenseService.queryExpenseDetails(expense.getExpenseId());
        //  3.审核记录
        List<AuditRecord> recordList = expenseService.queryAuditRecords(expense.getExpenseId());

        model.addAttribute("expense", expense);
        model.addAttribute("detailList", detailList);
        model.addAttribute("recordList", recordList);
        return "finance/financAaudit/financeaudit_audit";
    }

    @PostMapping("/financeAudit")
    public String doFinanceAudit(AuditRecord record, Model model) {
        System.out.println("AuditRecord:" + record.toString());
        //1.财务审核业务
        boolean b = expenseService.auditExpense(record);

        //2.回显
        Expense expense = new Expense();
        expense.setExpenseId(record.getExpenseId());//从接收参数中获取报销单编号
        expense = expenseService.queryExpenseById(expense);

        //3.报销单对应的报销明细
        List<ExpenseDetail> detailList = expenseService.queryExpenseDetails(expense.getExpenseId());
        //4.报销单对应的审核记录
        List<AuditRecord> recordList = expenseService.queryAuditRecords(expense.getExpenseId());

        if (b) {
            model.addAttribute("tip", "财务审核成功");
        } else {
            model.addAttribute("tip", "财务审核失败");
        }
        model.addAttribute("expense", expense);
        model.addAttribute("detailList", detailList);
        model.addAttribute("recordList", recordList);
        return "finance/financAaudit/financeaudit_audit";
    }

    //薪资发放
    @GetMapping("/sendSalary")
    public String sendSalary(Model model) {
        // 1.显示所有领取人信息
        List<User> userList = userService.queryUsers(new User());

        model.addAttribute("userList", userList);
        return "finance/salary/salarypayment_add";
    }

    //薪资发放ajax
    @PostMapping("/salaryQuery")
    @ResponseBody
    public User salaryQuery(User user) {
        return userService.queryUser(user);
    }

    @PostMapping("/sendSalary")
    public String doSendSalary(Model model, SalaryRecord salary){

        boolean b = salaryService.addSalary(salary);
        if(b){
            model.addAttribute("tip","发放成功");
        }else{
            model.addAttribute("tip","发放成功");
        }
        //发放薪资后回显所有领取人信息
        List<User> userList = userService.queryUsers(new User());
        model.addAttribute("userList",userList);

        return "finance/salary/salarypayment_add";
    }

    @RequestMapping("/querySalary")
    public String querySalary(SalaryRecord salaryRecord,Model model){

        List<SalaryRecord> salaryList = salaryService.querySalaryByUserNameOrSalaryMonth(salaryRecord);

        model.addAttribute("salaryRecord",salaryRecord);
        model.addAttribute("salaryList",salaryList);
        return "finance/salary/salarypayment_list";
    }
}
