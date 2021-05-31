package com.example.wanandroid.json;

import com.example.wanandroid.datas.Datas_passage;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonPassage{
    private List<Datas_passage> lists_passage=new ArrayList<>();
    private Datas_passage dataPassage;
    private String datas;
    public JsonPassage(String datas){
        this.datas=datas;
    }

    public List<Datas_passage> getLists_passage() {
        return lists_passage;
    }

    public void jsonRelease() throws JSONException {
        JSONObject jsonObject=new JSONObject(datas);
        JSONObject jsonObjectMain=jsonObject.getJSONObject("data");
        JSONArray jsonArrayData=jsonObjectMain.getJSONArray("datas");
        for(int i=0;i<jsonArrayData.length();i++){
            dataPassage=new Datas_passage();
            JSONObject jsonObjectBlock=jsonArrayData.getJSONObject(i);
            String author=jsonObjectBlock.getString("author");
            dataPassage.setAuthor(author);
            if(author==null){
                dataPassage.setAuthor("无名氏");
            }
            String title=jsonObjectBlock.getString("title");
            dataPassage.setTitle(title);
            String time=jsonObjectBlock.getString("niceDate");
            dataPassage.setTime(time);
            String link=jsonObjectBlock.getString("link");
            dataPassage.setLink_passage(link);
            lists_passage.add(dataPassage);
        }

    }
}
