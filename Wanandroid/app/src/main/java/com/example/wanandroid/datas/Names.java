package com.example.wanandroid.datas;

public class Names {
    String name;
    int pic;

    public Names(String name, int pic) {
        this.name = name;
        this.pic = pic;
    }

    public Names(){}
    public Names(String name) {
        this.name = name;
    }


    public int getPic() {
        return pic;
    }

    public void setPic(int pic) {
        this.pic = pic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
