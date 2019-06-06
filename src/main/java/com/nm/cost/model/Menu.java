package com.nm.cost.model;

import java.io.Serializable;

/**
 * t_menu
 * @author 
 */
public class Menu implements Serializable {
    private Integer menuId;

    private String menuName;

    private Integer pMenuId;

    private String menuUrl;

    private static final long serialVersionUID = 1L;

    @Override
    public String toString() {
        return "Menu{" +
                "menuId=" + menuId +
                ", menuName='" + menuName + '\'' +
                ", pMenuId=" + pMenuId +
                ", menuUrl='" + menuUrl + '\'' +
                '}';
    }

    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    public String getMenuName() {
        return menuName;
    }

    public void setMenuName(String menuName) {
        this.menuName = menuName;
    }

    public Integer getpMenuId() {
        return pMenuId;
    }

    public void setpMenuId(Integer pMenuId) {
        this.pMenuId = pMenuId;
    }

    public String getMenuUrl() {
        return menuUrl;
    }

    public void setMenuUrl(String menuUrl) {
        this.menuUrl = menuUrl;
    }
}