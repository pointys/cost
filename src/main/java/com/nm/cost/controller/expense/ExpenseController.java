package com.nm.cost.controller.expense;

import com.github.pagehelper.PageHelper;
import com.nm.cost.mapper.AuditRecordMapper;
import com.nm.cost.model.*;
import com.nm.cost.service.expense.impl.ExpenseService;
import com.nm.cost.service.system.impl.CostService;
import com.sun.org.apache.xpath.internal.operations.Mod;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.xml.soap.Detail;
import java.util.List;

/**
 * @Author: 韩老魔
 * @Date: 2019/3/13 0013 10:33
 */
@Controller
@RequestMapping("/expense")
public class ExpenseController {
    @Autowired
    private CostService costService;
    @Autowired
    private ExpenseService expenseService;

    //进入申请报销页面
    @GetMapping("/expenseAdd")
    public String addExpense(Model model) {
        List<Cost> costList = costService.queryCosts();
        model.addAttribute("costList", costList);
        return "/expense/expense/expense_add";
    }

    //保存添加的报销单
    @PostMapping("/expenseAdd")
    public String addExpense(Model model, Expense expense) {
        boolean b = expenseService.addExpense(expense);
        if (b) {
            model.addAttribute("tip", "添加费用成功");
        } else {
            model.addAttribute("tip", "添加费用失败");
        }
        //报销明细弹窗
        List<Cost> costList = costService.queryCosts();
        model.addAttribute("costList", costList);
        //接收的expense信息会返回视图
        return "/expense/expense/expense_add";
    }

    //经理审批列表
    @RequestMapping("/queryManagerAuditExpense")
    public String queryManagerAuditExpense(Expense expense, Model model) {

        expense.setExpenseState("1");// 待经理审核的状态

        List<Expense> expenseList = expenseService.queryExpense(expense);

        model.addAttribute("expenseList", expenseList);//待经理审核列表

        model.addAttribute("expense", expense);//查询条件
        return "/expense/managerAudit/expense_managerlist";
    }

    //经理处理单个审批
    @GetMapping("/manageAduitExpense")
    public String manageAduitExpense(Expense expense, Model model) {
        //1.通过主键查询报销单
        expense = expenseService.queryExpenseById(expense);
        //2.查询报销明细
        List<ExpenseDetail> detailList = expenseService.queryExpenseDetails(expense.getExpenseId());
        //3。查询审核记录
        List<AuditRecord> recordList = expenseService.queryAuditRecords(expense.getExpenseId());

        model.addAttribute("recordList", recordList);
        model.addAttribute("detailList", detailList);
        model.addAttribute("expense", expense);
        return "/expense/managerAudit/expense_audit";
    }

    //经理提交审核 添加审核记录并修改报销单状态
    @PostMapping("/manageAduitExpense")
    public String manageDoAduitExpense(AuditRecord audit, Model model) {
        //1.添加审核记录操作
        boolean b = expenseService.auditExpense(audit);

        //2.返回并回显数据:重新执行上面的()
        Expense expense = new Expense();
        expense.setExpenseId(audit.getExpenseId());//设置报销单编号
        //1.通过主键查询报销单
        expense = expenseService.queryExpenseById(expense);
        List<ExpenseDetail> detailList = expenseService.queryExpenseDetails(expense.getExpenseId());
        List<AuditRecord> recordList = expenseService.queryAuditRecords(expense.getExpenseId());

        model.addAttribute("expense", expense);//查询条件
        model.addAttribute("expense", expense);// 返回报销单
        model.addAttribute("detailList", detailList);// 返回报销明细
        model.addAttribute("recordList", recordList);// 返回审核记录
        if (b) {
            model.addAttribute("tip", "报销单审核成功");
        } else {
            model.addAttribute("tip", "报销单审核失败");
        }
        return "/expense/managerAudit/expense_audit";
    }

    //报销查询 查询所有申请报销列表
    @RequestMapping("/expenseQuery")
    public String expenseQuery(Expense expense, Model model) {
        List<Expense> expenseList = expenseService.queryExpense(expense);
        model.addAttribute("expenseList", expenseList);
        return "/expense/expense/expense_list";
    }

    @GetMapping("/showExpenseDetail")
    public String showExpenseDetail(Expense expense, Model model) {

        //1.根据expenseId查询报销费用
        expense = expenseService.queryExpense(expense).get(0);
        //2.2根据报销单编号查询报销单明细
        List<ExpenseDetail> detailList = expenseService.queryExpenseDetails(expense.getExpenseId());
        //2.3查询该费用编号的审核记录
        List<AuditRecord> recordList = expenseService.queryAuditRecords(expense.getExpenseId());

        model.addAttribute("expense", expense);
        model.addAttribute("detailList", detailList);
        model.addAttribute("recordList", recordList);

        return "/expense/expense/expense_show";
    }

    //我的报销
    @RequestMapping("/myExpense")
    public String myExpense(Model model, HttpServletRequest req, Expense expense) {
        User user = (User) req.getSession().getAttribute("userInfo");

        //根据用户id查询费用
        expense.setUserId(user.getUserId());
        List<Expense> expenseList = expenseService.queryExpenseByUserId(expense);

        model.addAttribute("expense", expense);
        model.addAttribute("expenseList", expenseList);
        return "expense/expense/expense_mylist";
    }

    @GetMapping("/expenseDetails")
    public String expenseDetails(Expense expense, Model model) {
        // 1.根据主键查询费用
        expense = expenseService.queryExpenseById(expense);
        // 2.根据报销单编号查询报销单明细
        List<ExpenseDetail> detailList = expenseService.queryExpenseDetails(expense.getExpenseId());

        // 3.查询该费用编号的审核记录
        List<AuditRecord> recordList = expenseService.queryAuditRecords(expense.getExpenseId());

        model.addAttribute("expense", expense);
        model.addAttribute("detailList", detailList);
        model.addAttribute("recordList", recordList);
        return "expense/expense/expense_show";
    }

    //经理进入费用报表页面
    @GetMapping("/chart")
    public String chart(Model model) {
        List<ExpenseChart> eCList = expenseService.expenseChart();
        model.addAttribute("eCList", eCList);
        return "/expense/expense/expense_chart";
    }
}
