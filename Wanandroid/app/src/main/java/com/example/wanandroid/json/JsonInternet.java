package com.example.wanandroid.json;

import android.provider.ContactsContract;
import android.util.Log;

import com.example.wanandroid.datas.Datas_internet;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonInternet {
    private static final String TAG ="YuMeng" ;
    private String datas;
    private List<Datas_internet> internets=new ArrayList<>();

    public JsonInternet(String datas) {
        this.datas = datas;
    }

    public List<Datas_internet> getInternets() {
        return internets;
    }

    public void jsonRelease() throws JSONException {
        Log.d(TAG, "jsonRelease: "+datas);
        JSONObject jsonObject=new JSONObject(datas);
        JSONArray jsonArrayData=jsonObject.getJSONArray("data");
        for(int i=0;i<jsonArrayData.length();i++){
            JSONObject jsonObjectBlocks=jsonArrayData.getJSONObject(i);
            JSONArray jsonArrayArticles =jsonObjectBlocks.getJSONArray("articles");
            for(int j=0;j<jsonArrayArticles.length();j++){
                Datas_internet datasInternet=new Datas_internet();
                JSONObject jsonObjectmBlocks=jsonArrayArticles.getJSONObject(j);
                int judge=jsonObjectmBlocks.getInt("chapterId");
                if(judge==272) {
                    String title = jsonObjectmBlocks.getString("title");
                    datasInternet.setName(title);
                    String url = jsonObjectmBlocks.getString("link");
                    datasInternet.setUrl(url);
                    internets.add(datasInternet);
                }
                else{continue;}

            }
        }
    }
}
