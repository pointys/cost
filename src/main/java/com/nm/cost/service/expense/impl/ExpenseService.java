package com.nm.cost.service.expense.impl;

import com.nm.cost.mapper.AuditRecordMapper;
import com.nm.cost.mapper.CostMapper;
import com.nm.cost.mapper.ExpenseDetailMapper;
import com.nm.cost.mapper.ExpenseMapper;
import com.nm.cost.model.AuditRecord;
import com.nm.cost.model.Expense;
import com.nm.cost.model.ExpenseChart;
import com.nm.cost.model.ExpenseDetail;
import com.nm.cost.service.expense.IExpenseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.xml.soap.Detail;
import java.util.ArrayList;
import java.util.List;

/**
 * @Author: 韩老魔
 * @Date: 2019/3/13 0013 14:22
 */
@Slf4j
@Service
@Transactional
public class ExpenseService implements IExpenseService {
    @Autowired
    private ExpenseMapper expenseMapper;
    @Autowired
    private ExpenseDetailMapper expenseDetailMapper;
    @Autowired
    private AuditRecordMapper auditRecordMapper;

//    为什么为导致存放的都变成了最后一次进入的呢？那说明你在对同一个对象进行操作。
//有下标，说明是一个数组之类的东西，为一个引用类型。引用类型直接进行了地址操作，你每次添加一个，但是其地址仍然没变，也就是说每次Add的时候其实是同一个东西。
//而在add之前赋值，会改变所有Add的东西。
//如此说来，你需要每次实例化一个对象，可以在循环内部实例化然后赋值，再进行Add操作，这样就没问题了。
//    private ExpenseDetail expenseDetail = new ExpenseDetail();

    @Override
    @CacheEvict(cacheNames="expenseCache",allEntries=true)
    public boolean addExpense(Expense expense) {
        //添加报销
        int i = expenseMapper.insert(expense);
        // 查询出添加的报销单编号expenseId(主键自增无法get)
        int expenseId = expenseMapper.getLastId();

        //循环添加报销所含费用列表
        //1.分装成list数组
        List<ExpenseDetail> expenseDetailList = new ArrayList<ExpenseDetail>();
        Integer[] costIds = expense.getCostIds();
        Float[] detailMoneys = expense.getDetailMoneys();
        String[] detailDescs = expense.getDetailDescs();
        System.out.println(costIds + "\t" + detailMoneys + "\t");
        for (int k = 0; k < costIds.length; k++) {
//            在循环外面实例该对象那么每次的set会修改之前的值导致list数组都是最后一次set的值
            ExpenseDetail expenseDetail = new ExpenseDetail();
            expenseDetail.setExpenseId(expenseId);
            expenseDetail.setCostId(costIds[k]);
            expenseDetail.setDetailMoney(detailMoneys[k]);
            expenseDetail.setDetailDesc(detailDescs[k]);
            System.out.println("expenseDetail:" + expenseDetail.toString());
            expenseDetailList.add(expenseDetail);
        }
        for (ExpenseDetail ed :
                expenseDetailList) {
            System.out.println("遍历" + ed.toString());
        }
        //2.添加费用明细
        int j = expenseDetailMapper.insert(expenseDetailList);
        if (i > 0 && j > 0) {
            return true;
        }
        return false;
    }

    @Override
    @Cacheable(cacheNames = "expenseCache",key="#p0")
    public List<Expense> queryExpense(Expense expense) {
        return expenseMapper.queryExpense(expense);
    }

    @Override
    @Cacheable(cacheNames="expenseCache",key="#p0")
    public Expense queryExpenseById(Expense expense) {
        return expenseMapper.queryExpenseById(expense);
    }

    @Override
    public List <Expense> queryExpenseByUserId(Expense expense) {
        return expenseMapper.queryExpenseByUserId(expense);
    }

    @Override
    public List<ExpenseDetail> queryExpenseDetails(int expenseId) {
        return expenseDetailMapper.queryExpenseDetails(expenseId);
    }

    @Override
    public List<AuditRecord> queryAuditRecords(int expenseId) {
        return auditRecordMapper.queryAuditRecords(expenseId);
    }

    @Override
    public boolean auditExpense(AuditRecord audit) {
        System.out.println(audit.toString());
        //1.添加审核记录
        int row1 = auditRecordMapper.auditExpense(audit);
        //2.修改对应报销单状态为审核状态
        int row2 = expenseMapper.updateExpenseStateByExpenseId(audit.getExpenseId(), audit.getAuditState());
        if (row1 > 0 && row2 > 0) {
            return true;
        }
        return false;
    }

    @Override
    public List<ExpenseChart> expenseChart() {
        return expenseMapper.expenseChart();
    }
}
