package com.example.loginme;

import androidx.appcompat.app.AppCompatActivity;

public class Shared extends AppCompatActivity {
   private String zh;
   private String mm;

    public String getZh() {
        return zh;
    }

    public void setZh(String zh) {
        this.zh = zh;
    }

    public String getMm() {
        return mm;
    }

    public void setMm(String mm) {
        this.mm = mm;
    }
}
