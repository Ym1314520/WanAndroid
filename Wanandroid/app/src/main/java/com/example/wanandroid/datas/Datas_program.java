package com.example.wanandroid.datas;

public class Datas_program {
    private  String picId1;
    private  String title,information,time,author,link;
    private int picId2;

    public Datas_program(){}

    public Datas_program(String title){
        this.title=title;
    }
    public String getPicId1() {
        return picId1;
    }

    public void setPicId1(String picId1) {
        this.picId1 = picId1;
    }

    public int getPicId2() {
        return picId2;
    }

    public void setPicId2(int picId2) {
        this.picId2 = picId2;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getInformation() {
        return information;
    }

    public void setInformation(String information) {
        this.information = information;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
