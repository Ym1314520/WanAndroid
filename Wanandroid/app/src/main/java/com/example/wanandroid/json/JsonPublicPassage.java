package com.example.wanandroid.json;

import android.util.Log;

import com.example.wanandroid.R;
import com.example.wanandroid.datas.Datas_publicPassage;
import com.example.wanandroid.datas.Names;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class JsonPublicPassage {
    private Datas_publicPassage datas_publicPassage;
    private String datas;
    private List<Datas_publicPassage> publicPassages = new ArrayList<>();
    private List<Datas_publicPassage> temp_public=new ArrayList<>();


    //公众号作者姓名表
    private List<Names> namesList =new ArrayList<>();
    private List<Names> temp_names=new ArrayList<>();
    private Names names;
    private int number=0;//从0开始计数，前七个存在nameslist里，后面的全部放在temp中方便刷新。
    private int size=0;//temp_names的计数器
    private String TAG = "Ym";

    public List<Names> getNamesList() {
        return namesList;
    }

    public Names getNames() {
        if(temp_names.size()==size){
            names=new Names("小余正在熬夜更新中，敬请期待...");
        }
        else {
            names = temp_names.get(size++);
        }
        return names;
    }

    public JsonPublicPassage(String datas) {
        this.datas = datas;
    }

    public List<Datas_publicPassage> getPublicPassages() {
        return publicPassages;
    }


//    public void jsonRelease() throws JSONException {
////        Log.d(TAG, "jsonRelease: " + datas);
////        JSONObject jsonObject = new JSONObject(datas);
////        JSONObject jsonObjectData = jsonObject.getJSONObject("data");
////        JSONArray jsonArray = jsonObjectData.getJSONArray("datas");
//////        Log.d(TAG, "jsonRelease: hellohello");
////        for (int i = 0; i < jsonArray.length(); i++) {
////            datas_publicPassage = new Datas_publicPassage();
////            JSONObject jsonObjectBlocks = jsonArray.getJSONObject(i);
////            JSONArray tags = jsonObjectBlocks.getJSONArray("tags");
////            if (tags.length() == 0) {
////                continue;
////            } else {
////                String public_title = jsonObjectBlocks.getString("title");
////                Log.d(TAG, "jsonRelease: " + public_title);
////                datas_publicPassage.setTitle(public_title);
////                for (int j = 0; j < tags.length(); j++) {
////                    JSONObject jsonObjectmBlocks = tags.getJSONObject(j);
////                    String url = jsonObjectmBlocks.getString("url");
////                    datas_publicPassage.setUrl_publicPassage("https://www.wanandroid.com/" + url);
////                    Log.d(TAG, "jsonRelease: " + public_title + url);
////                }
////            }
////            publicPassages.add(datas_publicPassage);
////        }
////    }


    public void RealeaseIt() throws JSONException {
        Random random=new Random();
        JSONObject jsonObject=new JSONObject(datas);
        JSONArray jsonArrayData=jsonObject.getJSONArray("data");
        for(int i=0;i<jsonArrayData.length();i++) {
            Names names= new Names();
            JSONObject jsonObjectBlocks = jsonArrayData.getJSONObject(i);
            String name=jsonObjectBlocks.getString("name");
            names.setName(name);
            int picId=random.nextBoolean()? R.drawable.picture:R.drawable.ppt;
            names.setPic(picId);
            number++;
            if(number<9) {
                namesList.add(names);
            }
            else{
                temp_names.add(names);
            }
        }
    }

}
