package com.example.newhelloandroid;

public class restaurantObject {

    public String name;
    public String picUrl;

    public restaurantObject(String name, String picUrl) {
        this.name = name;
        this.picUrl = picUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }
}
