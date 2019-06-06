package com.nm.cost.mapper;

import com.nm.cost.model.AuditRecord;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;
@Mapper
public interface AuditRecordMapper {
    int deleteByPrimaryKey(Integer auditid);

    int insert(AuditRecord record);

    int insertSelective(AuditRecord record);

    AuditRecord selectByPrimaryKey(Integer auditid);

    int updateByPrimaryKeySelective(AuditRecord record);

    int updateByPrimaryKey(AuditRecord record);

    @Select("select tad.*,tu.userName from t_audit_record tad inner join t_users tu on tad.userId=tu.userId where tad.expenseId=#{expenseId}")
    List<AuditRecord> queryAuditRecords(@Param("expenseId") int expenseId);

    @Insert("insert into t_audit_record(userId,expenseId,auditState,auditSugg,auditDate) values(#{audit.userId},#{audit.expenseId},#{audit.auditState},#{audit.auditSugg},now())")
    int auditExpense(@Param("audit") AuditRecord audit);
}