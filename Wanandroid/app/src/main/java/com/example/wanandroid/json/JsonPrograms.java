package com.example.wanandroid.json;

import android.util.Log;

import com.example.wanandroid.R;
import com.example.wanandroid.datas.Datas_passage;
import com.example.wanandroid.datas.Datas_program;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonPrograms {
    private static final String TAG ="Doc.Ym" ;
    private List<Datas_program> datasProgramList=new ArrayList<>();
    private List<Datas_program> tempProgramList=new ArrayList<>();
    private Datas_program datas_program;
    private String datas;
    private int size1=0;
    private int size2=0;

    public Datas_program getProgram() {
        if(size2==tempProgramList.size()){
            return new Datas_program("正在拼命更新中...");
        }
        return tempProgramList.get(size2++);
    }

    public List<Datas_program> getDatasProgramList() {
        return datasProgramList;
    }

    public JsonPrograms(String datas){
        this.datas=datas;
    }
    public void jsonRelease() throws JSONException {
        Log.d(TAG, "jsonRelease: "+datas);
        JSONObject jsonObject = new JSONObject(datas);
        JSONObject jsonObjectMain=jsonObject.getJSONObject("data");
        JSONArray jsonArray = jsonObjectMain.getJSONArray("datas");
        for (int i = 0; i < jsonArray.length(); i++) {
            datas_program=new Datas_program();
            JSONObject jsonObjectBlock = jsonArray.getJSONObject(i);
            String title=jsonObjectBlock.getString("title");
           datas_program.setTitle(title);
            String information=jsonObjectBlock.getString("desc");
            datas_program.setInformation(information);
            String author=jsonObjectBlock.getString("author");
            datas_program.setAuthor(author);
            String picUrl=jsonObjectBlock.getString("envelopePic");
            datas_program.setPicId1(picUrl);
            String titleUrl=jsonObjectBlock.getString("link");
            datas_program.setLink(titleUrl);
            String time=jsonObjectBlock.getString("niceDate");
            datas_program.setTime(time);
            if(i%2==0) {
                int makePic = R.drawable.picture;
                datas_program.setPicId2(makePic);
            }
            else{
                int makePic=R.drawable.ppt;
                datas_program.setPicId2(makePic);
            }

            size1++;
            if(size1<5) {
                datasProgramList.add(datas_program);
            }
            else{
                tempProgramList.add(datas_program);
            }
        }
    }
}
