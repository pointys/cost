package com.nm.cost.controller.system;

import com.nm.cost.model.SalaryRecord;
import com.nm.cost.model.User;
import com.nm.cost.service.finance.impl.SalaryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @Author: 韩老魔
 * @Date: 2019/2/24 0024 10:44
 */
@Controller
@RequestMapping("/system")
public class SalaryController{
    @Autowired
    private SalaryService salaryService;

    @GetMapping("/mySalary")
    public String mySalary(HttpServletRequest req, Model model) {
        User user = (User) req.getSession().getAttribute("userInfo");
        SalaryRecord salaryRecord = new SalaryRecord();
        salaryRecord.setUserId(user.getUserId());
        List<SalaryRecord> salaryList = salaryService.querySalary(salaryRecord);

        model.addAttribute("salaryList", salaryList);
        return "/system/user/salarypayment_mylist";
    }

    @PostMapping("/mySalary")
    public String queryMySalary(SalaryRecord salary, Model model) {
        List<SalaryRecord> salaryList = salaryService.querySalary(salary);
        model.addAttribute("salaryList", salaryList);
        return "/system/user/salarypayment_mylist";
    }

}
