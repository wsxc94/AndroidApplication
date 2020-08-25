package com.example.kimchi.myapplication;

import android.graphics.drawable.Drawable;

import com.android.volley.toolbox.StringRequest;


public class AppData {
    private String name;
    private String appLink;
    private String picture;
    public AppData(){

    }
    public AppData(String _name,String applink,String picture){
        this.name = _name;
        this.appLink = applink;
        this.picture = picture;
    }
    public String getName(){
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getApplink(){
        return appLink;
    }
    public void setApplink(String applink) {
        this.appLink = applink;
    }
    public String getPicture(){
        return picture;
    }
    public void setPicture(String picture) {
        this.picture = picture;
    }

}
