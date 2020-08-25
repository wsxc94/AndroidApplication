package com.example.kimchi.myapplication;

import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.widget.ImageView;

import com.google.firebase.firestore.Exclude;

import java.util.Date;
import java.util.List;

public class ProjectInfo {
    private String title; //프로젝트 제목

    private String documentId;
    private String startyears;
    private String startdate;
    private String startday;
    private String endyears;         //기간
    private String enddate;
    private String endday;

    private String purpose;  //목적
    private String qualification; //참여자격
    private String picture; //사진
    private String hashtag1; //해시태그
    private String hashtag2;
    private String hashtag3;
    private String hashtag4;
    private long time;
    private String date;

    private String userID;
    public ProjectInfo(){ }
    public ProjectInfo(String userID,String title, String startyears, String startdate, String startday, String endyears, String enddate, String endday, String purpose, String qualification, String picture, String hashtag1, String hashtag2, String hashtag3, String hashtag4, long time, String day){
        this.title =title;
        this.startyears =startyears;
        this.startdate = startdate;
        this.startday = startday;
        this.endyears = endyears;;
        this.enddate = enddate;
        this.endday = endday;
        this.purpose = purpose;
        this.qualification = qualification;
        this.picture = picture;
        this.hashtag1 = hashtag1;
        this.hashtag2 = hashtag2;
        this.hashtag3 = hashtag3;
        this.hashtag4 = hashtag4;
        this.time = time;
        this.date = day;
        this.userID = userID;


    }
    @Exclude
    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getStartyears() {
        return startyears;
    }

    public void setStartyears(String startyears) {
        this.startyears = startyears;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getStartday() {
        return startday;
    }

    public void setStartday(String startday) {
        this.startday = startday;
    }

    public String getEndyears() {
        return endyears;
    }

    public void setEndyears(String endyears) {
        this.endyears = endyears;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getEndday() {
        return endday;
    }

    public void setEndday(String endday) {
        this.endday = endday;
    }

    public String getPurpose() {
        return purpose;
    }

    public void setPurpose(String purpose) {
        this.purpose = purpose;
    }

    public String getQualification() {
        return qualification;
    }

    public void setQualification(String qualification) {
        this.qualification = qualification;
    }

    public String getHashtag() {
        return hashtag1;
    }

    public void setHashtag (String hashtag) {
        this.hashtag1 = hashtag;
    }

    public String getHashtag1() {
        return hashtag1;
    }

    public void setHashtag1(String hashtag1) {
        this.hashtag1 = hashtag1;
    }

    public String getHashtag2() {
        return hashtag2;
    }

    public void setHashtag2(String hashtag2) {
        this.hashtag2 = hashtag2;
    }

    public String getHashtag3() {
        return hashtag3;
    }

    public void setHashtag3(String hashtag3) {
        this.hashtag3 = hashtag3;
    }

    public String getHashtag4() {
        return hashtag4;
    }

    public void setHashtag4(String hashtag4) {
        this.hashtag4 = hashtag4;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}

