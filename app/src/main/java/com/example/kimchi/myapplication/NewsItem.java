package com.example.kimchi.myapplication;

import java.io.Serializable;

public class NewsItem implements Serializable {
    private String mImageUrl;
    private String mNewstitle;
    private String mpubData;
    private String mtext;
    private String mlink;
    private String mName;
/*
public NewsItem(){}

    public NewsItem(String imageUrl , String title , String pubData ,String links,String text){
        mImageUrl = imageUrl;
        mNewstitle = title;
        mpubData = pubData;
        mlink = links;
        mtext = text;
    }
*/

    public String getmImageUrl() {
        return mImageUrl;
    }

    public String getMlink() {
        return mlink;
    }

    public String getmNewstitle() {
        return mNewstitle;
    }
    public String getMpubData(){
        return mpubData;
    }
    public String getMtext(){
        return  mtext;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }

    public void setmNewstitle(String mNewstitle) {
        this.mNewstitle = mNewstitle;
    }

    public void setMpubData(String mpubData) {
        this.mpubData = mpubData;
    }

    public void setMtext(String mtext) {
        this.mtext = mtext;
    }

    public void setMlink(String mlink) {
        this.mlink = mlink;
    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }
}
