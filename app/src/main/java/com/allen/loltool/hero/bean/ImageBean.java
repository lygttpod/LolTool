package com.allen.loltool.hero.bean;

/**
 * Created by Allen on 2015/12/29.
 */
public class ImageBean {

    private String title;
    private String img;

    public ImageBean(String title, String img) {
        this.title = title;
        this.img = img;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
