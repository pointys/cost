package com.nm.cost.model;

import java.io.Serializable;

/**
 * t_role
 * @author 
 */
public class Role implements Serializable {
    private Integer roleId;

    private String roleName;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Role{" +
                "roleId=" + roleId +
                ", roleName='" + roleName + '\'' +
                '}';
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
}