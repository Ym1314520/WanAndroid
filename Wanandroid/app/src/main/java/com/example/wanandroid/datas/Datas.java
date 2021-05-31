package com.example.wanandroid.datas;


public class Datas {

    private int picture_good;
    private int picture_more;
    private String titles,information;
    private String urls,types1,types2;
    private String official,question;

    public Datas(){}

    public Datas(String titles, String information, String types1, String types2) {
        this.titles = titles;
        this.information = information;
        this.types1 = types1;
        this.types2 = types2;
        this.official = official;
        this.question = question;
    }

    public int getPicture_good() {
        return picture_good;
    }

    public void setPicture_good(int picture_good) {
        this.picture_good = picture_good;
    }

    public int getPicture_more() {
        return picture_more;
    }

    public void setPicture_more(int picture_more) {
        this.picture_more = picture_more;
    }

    public String getTitles() {
        return titles;
    }

    public void setTitles(String titles) {
        this.titles = titles;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getUrls() {
        return urls;
    }

    public void setUrls(String urls) {
        this.urls = urls;
    }

    public String getTypes1() {
        return types1;
    }

    public void setTypes1(String types1) {
        this.types1 = types1;
    }

    public String getTypes2() {
        return types2;
    }

    public void setTypes2(String types2) {
        this.types2 = types2;
    }

    public String getOfficial() {
        return official;
    }

    public void setOfficial(String official) {
        this.official = official;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }
}
