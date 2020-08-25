package com.example.kimchi.myapplication;

public class FreeBoradData {
    private String mtitle;
    private String mdate;
    private String mtext;
    private String memail;

    public FreeBoradData(){}
    public FreeBoradData(String title,String date, String text , String email){
        this.mtitle = title;
        this.mdate = date;
        this.mtext = text;
        this.memail = email;

    }

    public String getMtitle() {
        return mtitle;
    }

    public void setMtitle(String mtitle) {
        this.mtitle = mtitle;
    }

    public String getMdate() {
        return mdate;
    }

    public void setMdate(String mdate) {
        this.mdate = mdate;
    }

    public String getMtext() {
        return mtext;
    }

    public void setMtext(String mtext) {
        this.mtext = mtext;
    }

    public String getMemail() {
        return memail;
    }
}
