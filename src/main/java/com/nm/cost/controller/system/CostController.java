package com.nm.cost.controller.system;

import com.nm.cost.model.Cost;
import com.nm.cost.service.system.ICostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

/**
 * @Author: 韩老魔
 * @Date: 2019/2/24 0024 11:56
 */
@Controller
@RequestMapping("/system")
public class CostController {
    @Autowired
    private ICostService costService;

    @GetMapping("/costQuery")
    public String queryCosts(Cost cost, Model model) {
        List<Cost> costList = costService.queryCosts();
        model.addAttribute("costList", costList);
        return "/system/cost/cost_list";
    }

    @PostMapping("/costQuery")
    public String queryCost(Cost cost, Model model) {
        List<Cost> costList = costService.queryCostByCostIdOrByCostName(cost);
        model.addAttribute("costList", costList);
        return "/system/cost/cost_list";
    }

    @GetMapping("/costUpdate")
    public String updateCost(Cost cost, Model model) {
        cost = costService.queryCostByCostIdOrByCostName(cost).get(0);

        model.addAttribute("cost", cost);
        return "/system/cost/cost_update";
    }

    @PostMapping("/costUpdate")
    public String doUpdateCost(Cost cost, Model model) {
        boolean b = costService.updateCost(cost);
        if (b) {
            model.addAttribute("tip", "修改成功");
        } else {
            model.addAttribute("tip", "修改失败");
        }
        return "/system/cost/cost_update";
    }

    @GetMapping("/costAdd")
    public String addCost() {
        return "/system/cost/cost_add";
    }

    @PostMapping("/costAdd")
    public String doAddCost(Cost cost, Model model) {
        boolean b = costService.addCost(cost);
        if (b) {
            model.addAttribute("tip", "添加成功");
        } else {
            model.addAttribute("tip", "添加失败");
        }
        return "/system/cost/cost_add";
    }

    @PostMapping("/costDelete")
    public String deleteCost(Cost cost, Model model) {
        costService.deleteCost(cost.getIds());
        //重定向返回费用列表
        return "redirect:/system/costQuery";
    }
}
