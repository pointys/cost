package com.nm.cost.model;

import java.io.Serializable;

/**
 * t_role_menu
 * @author 
 */
public class RoleMenuKey implements Serializable {
    private Integer roleId;

    private Integer menuId;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "RoleMenuKey{" +
                "roleId=" + roleId +
                ", menuId=" + menuId +
                '}';
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}