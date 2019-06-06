package com.nm.cost.mapper;

import com.nm.cost.model.Menu;
import com.nm.cost.model.User;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface UserMapper {

    /**
     * 根据userAccount或userName
     * @return 返回条件查询列表
     */
    List<User> queryUsers(User user);

    /**
     * 登录验证
     * @param user
     * @return
     */
    @Select("select tu.*,tr.roleName from t_users tu inner join t_role tr on tu.roleId=tr.roleId where tu.userAccount=#{user.userAccount} and tu.userPwd=#{user.userPwd} and userMark='0'")
    User verifyUser(@Param("user") User user);

    /**
     * 在已经拥有权限的条件下根据userId查user所有信息
     * @param user
     * @return
     */
    @Select("select tu.*,tr.roleName from t_users tu inner join t_role tr on tu.roleId=tr.roleId where tu.userId=#{user.userId} and userMark='0'")
    User queryUser(@Param("user") User user);

    /**
     * 根据role返回菜单列表
     * @param roleId
     * @return
     */
    @Select("select * from t_role_menu trm inner join t_menu tm on trm.menuId=tm.menuId where trm.roleId=#{roleId}")
    List<Menu> queryMenus(@Param("roleId") Integer roleId);

    /**
     * 用户登录查看账户是否存在
     * @param userAccount
     * @return
     */
    @Select("select COUNT(*) from t_users where userAccount=#{userAccount}")
    int checkAccount(String userAccount);

    /**
     * 根据userId修改user
     * @param user
     * @return
     */
    @Update("update t_users set userName=#{userName},userSex=#{userSex},userAge=#{userAge},userPhone=#{userPhone},userPwd=#{userPwd},userSalary=#{userSalary},roleId=#{roleId} where userId=#{userId,jdbcType=INTEGER}")
    int updateUser(User user);

    /**
     * 假删除 多个数据用@param
     * @param ids
     */
    void delteUsers(@Param("ids") Integer[] ids);

    /**
     * 添加记录
     * @param user
     * @return
     */
    @Insert("insert into t_users (roleId,userName,userSex,userAge,userPhone,userAccount,userPwd,userSalary,userMark) values(#{roleId},#{userName},#{userSex},#{userAge},#{userPhone},#{userAccount},#{userPwd},#{userSalary},'0')")
    int addUser(User user);

    @Insert("insert into t_users (roleId,userName,userSex,userAge,userPhone,userAccount,userPwd,userSalary,userMark) values(#{roleId},#{userName},#{userSex},#{userAge},#{userPhone},#{userAccount},#{userPwd},#{userSalary},'0')")
    int register(User user);
}