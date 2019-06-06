package com.nm.cost;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.nm.cost.converter.OfficePDFConverter;
import com.nm.cost.mapper.*;
import com.nm.cost.model.*;
import com.nm.cost.service.document.IDocumentService;
import com.nm.cost.service.expense.IExpenseService;
import com.nm.cost.service.finance.ISalaryService;
import com.nm.cost.service.system.ICostService;
import com.nm.cost.service.system.IUserService;
import org.apache.commons.lang3.time.DateUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.ParseException;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CostApplicationTests {
    @Autowired
    private IUserService userService;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ICostService costService;
    @Autowired
    private ISalaryService salaryService;
    @Autowired
    private ExpenseMapper expenseMapper;
    @Autowired
    private ExpenseDetailMapper expenseDetailMapper;
    @Autowired
    private IExpenseService expenseService;
    @Autowired
    private AuditRecordMapper auditRecordMapper;
    @Autowired
    private SalaryRecordMapper salaryRecordMapper;
    @Autowired
    private IDocumentService documentService;
    @Autowired
    DocumentMapper documentMapper;
    @Autowired
    private OfficePDFConverter converter;

    /**
     * 测试接口
     */
//    @Test
//    public void contextLoads() {
        /**
         * IUserService接口
         * queryUser(user)、queryUsers()、addUser(user2)、
         * queryMenus(1)、updateUser(user)、checkAccount、delteUsers(ids)
         */
//        User user = new User();
//        user.setRoleName("超级管理员");
//        user.setUserAccount("123");
//        user.setRoleId(1);
//        user.setUserName("123");
//        user.setUserPwd("123");
//        user = userService.queryUser(user);
//        System.out.println(user.toString());
        /*List<User> userList=userService.queryUsers();
        User user2=new User();
        user2.setUserAccount("m");
        user2.setUserPwd("m");
        user2.setRoleId(1);
//        boolean b = userService.addUser(user2);
//        System.out.println(b);
        for ( User user1:
             userList) {
            System.out.println(user1.toString());
        }
//        List<Menu> menuList = userService.queryMenus(1);
//        for (Menu m :
//                menuList) {
//            System.out.println(m.toString());
//        }*/
//       User u=new User(1,1,"q","a",1,"a","a","a");
//         boolean b1=userService.updateUser(u);
//        System.out.println(b1);
//        boolean b = userService.checkAccount("123");
//        System.out.println(b);
//        userService.delteUsers(new Integer[]{1,2});
//        List<User> userList = userService.findUserByUserAccountOrUserName(user);
//        System.out.println(userList);
//        for (User u:
//             userList) {
//            System.out.println(u.getRoleName());
//        }
//        int i = userService.checkAccount("123");
//        System.out.println(i);
//        User user = new User();user.setUserAccount("123");
//
//        List<User> userList = userMapper.queryUsers(user);
//        for(User u:userList){
//            System.out.println(u.toString());
//        }
/**
 * ICostService接口
 * queryCostByCostIdOrByCostName(cost)、deleteCost(ids)、
 * updateCost(cost)、addCost(cost)
 */
       /* Cost cost=new Cost();
        cost.setCostId(1);
        cost.setCostName("飞机费用");
        cost.setCostDesc("来回飞机费用");
        List<Cost> costList = costService.queryCostByCostIdOrByCostName(cost);
        for (Cost cost1:
             costList) {
            System.out.println(cost1.toString());
        }

        Integer[] ids=new Integer[]{1,2};
        boolean boo = costService.deleteCost(ids);
        System.out.println(boo);
        boolean b = costService.updateCost(cost);
        System.out.println(b);

        boolean b1 = costService.addCost(cost);
        System.out.println(b1);*/
//        Cost cost = null;
//        List<Cost> costList = costService.queryCosts(cost);
//        for (Cost c :
//                costList) {
//            System.out.println(c.toString());
//        }
        /*
         * ISalaryService接口
         *
         * */
 /*       SalaryRecord salary = new SalaryRecord();
        //将Date转换成指定格式的String
        SimpleDateFormat sdf = new SimpleDateFormat("yyy-MM-dd");
        salary.setUserId(1);
        List<SalaryRecord> salaryList = salaryService.querySalary(salary);
        for (SalaryRecord s :
                salaryList) {

            System.out.println(s.toString());
        }*/
//    }

    @Test
    public void dateTest() {
//        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//        Date date=new Date();
//        String format = sdf.format(date);
//        System.out.println(format);
//        Expense expense=new Expense();
//        expense.setUserId(1);
//        expense.setExpenseName("zhang");
//        expense.setExpenseDesc("aaa");
//        expense.setExpenseTotal(20f);
//        expense.setExpenseState("1");
//        int i = expenseMapper.insert(expense);
//        System.out.println("----"+i);
//        int lastId = expenseMapper.getLastId();
//        System.out.println("----------:" + lastId);
/*        List<ExpenseDetail> expenseDetailList = new ArrayList<>();
        ExpenseDetail ed = new ExpenseDetail();
       for(int m=0;m<3;m++) {
           ed.setExpenseId(9);
           ed.setCostId(1 + m);
           ed.setDetailDesc("鸡巴蟹" + m);
           ed.setDetailMoney(20f);
           expenseDetailList.add(ed);
       }

        int i = expenseDetailMapper.insert(expenseDetailList);*/
//        System.out.println("========="+i);
//        Expense expense=new Expense();
//        expense.setUserId(1);
//        expense.setExpenseState("1");
//        List<Expense> expenseList = expenseMapper.queryExpense(expense);
//        for (Expense e:
//                expenseList) {
//            System.out.println("遍历："+e.toString());
//        }
//        Expense expense = new Expense();
//        expense.setExpenseId(3);
//        expense.setUserId(1);
//        expense.setExpenseState("1");// 待经理审核的状态
//
//        List<Expense> expenseList = expenseService.queryExpense(expense);
//
//        for (Expense e:
//                expenseList) {
//            System.out.println("遍历："+e.toString());
//        }
//        List<ExpenseDetail> detailList = expenseDetailMapper.queryExpenseDetails(2);
//                for (ExpenseDetail detail:
//                        detailList) {
//            System.out.println("遍历："+detail.toString());
//        }
//        List<AuditRecord> auditRecordList = auditRecordMapper.queryAuditRecords(2);
//        for (AuditRecord r:
//             auditRecordList) {
//            System.out.println(r.toString());
//        }
//        SalaryRecord salaryRecord = new SalaryRecord();
//        salaryRecord:SalaryRecord{salaryId=null, userId=null, salaryMonth=null, salaryDate=null, salaryBasic=null, salaryComm=null, userName=''}
//        salaryRecord.setUserName("菜鸡");
//        System.out.println(salaryRecord.toString());
//        salaryRecord.setSalaryMonth(Mon Oct 01 00:00:00 CST 2018);
//        List<SalaryRecord> salaryRecordList = salaryRecordMapper.querySalaryByUserNameOrSalaryMonth(salaryRecord);
//        salaryRecord.setUserId(1);
//        List<SalaryRecord> salaryRecordList = salaryRecordMapper.querySalaryByUserIdOrSalaryMonth(salaryRecord);
//        for (int i = 0; i < salaryRecordList.size(); i++) {
//            System.out.println(salaryRecordList.get(i).toString());
//        }
//        Document document = new Document();
//        document.setFile_mark("1");
//        document.setFile_name("测试");
//        document.setFile_size(12333);
//        document.getFile_date();
//        document.setFile_id(1);
//        document.setFile_type("doc");
//        document.setFile_url("url");
//        document.setUser_id(1);
//document.setFile_name("123");
//        int add = documentService.add(document);
//        System.out.println(add);
//         document = documentService.querys(document).get(0);
//        System.out.println(document.toString());
//        List<Document> documentList = documentService.querys(document);
//        for (Document d : documentList) {
//            System.out.println(d.toString());
//        }`
//        documentService.delete(new Integer[]{1,3,6});
//        try {
////            if(converter.officePDF("f:/test.docx" , "f:/test.pdf")){
////                System.out.println("转换成功");
////            }
////        } catch (Exception e) {
////            e.printStackTrace();
////        }

        //先删除服务器源文件再删除记录
//        Document document=new Document();
//        Integer[] ids=new Integer[]{61,62};
//        for(Integer id:ids){
//            System.out.println("循环删除id:"+id);
//            document.setFile_id(id);
//            List<Document> documentList=documentMapper.querys(document);
//            String file_url=documentList.get(0).getFile_url();
//            System.out.println("file_url:"+file_url);
//            Path path= Paths.get(file_url);
//            try {
//                //逐个从删除硬盘文件
//                Files.delete(path);
//
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }

//          String command = "f:/"
//                + "program/soffice.exe -headless -accept=\"socket,host=127.0.0.1,port=8300;urp;\" -nofirststartwizard";
//        f:/program/soffice.exe -headless -accept="socket,host=127.0.0.1,port=8300;urp;" -nofirststartwizard
//        String command = "f:/"
//                + "program/soffice.exe -headless -accept="+"socket,host=127.0.0.1,port=8300;urp;"+" -nofirststartwizard";
//        f:/program/soffice.exe -headless -accept=socket,host=127.0.0.1,port=8300;urp; -nofirststartwizard
//        String command = "f:/"
////                + "program/soffice -headless -accept=\"socket,host=127.0.0.1,port=8300;urp;\" -nofirststartwizard &";
////
////                System.out.println(command);

//        String pdfPath="f:/简历.pdf";
//        String savePath="f:/简历2.pdf";
//        String waterFont="鸡巴蟹";
//        try {
////            converter.officePDF("f:/简历.doc","f:/简历.pdf");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        converter.addFooterAndWater(pdfPath,savePath,waterFont);

//        共18条记录
        PageHelper.startPage(2, 10);
        Page<User> userList = (Page)userService.queryUsers(new User());
        int pageNum = userList.getPageNum();
        int pages = userList.getPages();
        long total = userList.getTotal();
        System.out.println("当前页:"+pageNum);
        System.out.println("总页数:"+pages);
        System.out.println("总记录数:"+total);
        for (User userlist:
                userList) {
            System.out.println(userlist.toString());
        }

    }
}
