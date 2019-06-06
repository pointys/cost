package com.nm.cost.controller.system;

import com.nm.cost.exception.MyException;
import com.nm.cost.model.*;
import com.nm.cost.service.finance.impl.SalaryService;
import com.nm.cost.service.system.impl.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Author: 韩老魔
 * @Date: 2019/2/23 0023 16:31
 */
@Controller
@RequestMapping("/system")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private SalaryService salaryService;

    //程序入口
    @GetMapping("/login")
    public String login(HttpSession session) {
        // 登录前清空session
        session.invalidate();
        System.out.println("清空session");
        return "login";
    }

    //登录验证
    @PostMapping("/login")
    public String login(User user, Model model, HttpSession session) {
        //1.根据账号密码调用service查询用户信息
        User userInfo = userService.verifyUser(user);

        //2.判断是否存在该用户
        if (userInfo == null) {
            model.addAttribute("tip", "账号或密码错误");
            return "login";
        } else {//验证通过
            //用户关闭浏览器session失效
            session.setAttribute("userInfo", userInfo);//用户信息存入session 页面跳转首页
            //查询用户roleId所对应的权限 Menu所有字段信息
            List<Menu> menuList = userService.queryMenus(userInfo.getRoleId());
            session.setAttribute("menuList", menuList);
            for (Menu m :
                    menuList) {
                System.out.println("---" + m.toString());
            }

            //这里采用重定向改变浏览器url来防止浏览器回车又重新登录 原因：system/login的get请求有清空session方法
//            //返回视图
//            return "index";
            return "redirect:/system/index";
        }
    }

    @RequestMapping("/index")
    public String index(HttpSession session) {
////        手动抛出异常
//        try {
//            Integer.parseInt("abc");
//        } catch (Exception e) {
//            throw new MyException("400","abc不能转为整型");
//        }
//        全局捕获异常
//        int i=1/0;
        return "index";
    }

    @GetMapping("/main")
    public String main(HttpServletRequest req) {
        //查询报表信息
        List<SalaryChart> salaryChart = salaryService.querysalaryChart();
        HttpSession session = req.getSession();
        session.setAttribute("salaryChart", salaryChart);
        return "main";
    }

    @RequestMapping("/userQuery")
    public String userQuery(Model model, HttpServletRequest req, User user) {
        List<User> userList = userService.findUserByUserAccountOrUserName(user);
        model.addAttribute("userList", userList);
        model.addAttribute("user", user);
        return "/system/user/userinfo_list";
    }

    @GetMapping("/updateUser")
    public String updateUser(User user, Model model) {

        user = userService.queryUser(user);

        model.addAttribute("user", user);
        return "/system/user/userinfo_update";
    }

    @PostMapping("/updateUser")
    public String doUpdateUser(User user, Model model) {

        boolean b = userService.updateUser(user);
        if (b) {
            model.addAttribute("tip", "修改成功");
        } else {
            model.addAttribute("tip", "修改失败");
        }
        //修改结果信息返回视图 ajax会好点
        return "/system/user/userinfo_update";
    }

    @PostMapping("/deleteUsers")
    public String deleteUsers(User user) {
        userService.delteUsers(user.getIds());
//       重定向 注意路径
        return "redirect:/system/userQuery";
    }

    @GetMapping("addUser")
    public String addUser() {
        //返回视图
        return "/system/user/userinfo_add";
    }

    /**
     * 添加用户信息
     *
     * @param user
     * @param model
     * @return
     */
    @PostMapping("/userAdd")
    public String doAddUser(User user, Model model) {
        boolean b = userService.addUser(user);
        if (b) {
            model.addAttribute("tip", "添加成功");
        } else {
            model.addAttribute("tip", "添加失败");
        }
        //消除user回显
        user = new User();
        model.addAttribute("user", user);
        return "/system/user/userinfo_add";
    }

    /**
     * ajax检测用户注册账号是否存在
     *
     * @param userAccount
     * @param model
     * @return
     */
    @PostMapping("checkAccount")
    @ResponseBody
    public ResponseMessage checkAccount(String userAccount, Model model) {
        int count = userService.checkAccount(userAccount);
        ResponseMessage responseMessage = new ResponseMessage();
        if (count == 0) {//无账号
            responseMessage.setState(true);
            responseMessage.setTip("该账号可用");
        } else {//有账号
            responseMessage.setState(false);
            responseMessage.setTip("该账号已存在");
        }
        return responseMessage;
    }

    @GetMapping("/myuserInfo")
    public String myInfo() {
        return "/system/user/userinfo_show";
    }

    /**
     * 修改个人信息
     *
     * @param user
     * @param model
     * @param req
     * @return
     */
    @PostMapping("/myuserInfo")
    public String updateMyInfo(User user, Model model, HttpServletRequest req) {
        boolean b = userService.updateUser(user);
        if (b) {
            model.addAttribute("tip", "修改成功");
            //清空session重新登录
            req.getSession().invalidate();
            return "/system/login?tip=修改成功";
        } else {
            model.addAttribute("user", user);
            model.addAttribute("tip", "修改失败");
            return "/system/user/userinfo_show";
        }
    }

}
