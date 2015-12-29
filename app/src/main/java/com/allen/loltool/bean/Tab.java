package com.allen.loltool.bean;

/**
 * Created by Allen on 2015/12/28.
 */
public class Tab {

    private int title;//导航菜单文字描述
    private int icon;//导航菜单图片
    private Class fragment;//装载Fragment

    public Tab(int icon, Class fragment) {
        this.icon = icon;
        this.fragment = fragment;
    }

    public Tab(int title, int icon, Class fragment) {
        this.title = title;
        this.icon = icon;
        this.fragment = fragment;
    }

    public int getTitle() {
        return title;
    }

    public void setTitle(int title) {
        this.title = title;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public Class getFragment() {
        return fragment;
    }

    public void setFragment(Class fragment) {
        this.fragment = fragment;
    }
}
