package com.example.wanandroid.json;

import android.os.Handler;
import android.os.Message;
import android.provider.ContactsContract;
import android.text.Html;
import android.text.Spanned;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.wanandroid.R;
import com.example.wanandroid.datas.Datas;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_SHORT;
import static androidx.constraintlayout.widget.Constraints.TAG;

public class Json {
    private Datas data;
    private List<Datas> articleList=new ArrayList<>();
    private int counter1=0;
    private List<Datas> temp_articles=new ArrayList<>();
    private int counter2=0;

    private String datas;
    public Json(String data){
        datas=data;
    }

    public List<Datas> getArticleList() {
        return articleList;
    }

    public Datas getData() {
        if(counter2==temp_articles.size()){
            data=new Datas("小余同学正在玩命肝代码中...","作者:余余爱摸鱼","未知","未知");
        }
        else {
            data = temp_articles.get(counter2++);
        }
        return data;
    }

    public void jsonRelease() throws JSONException {
//        Spanned it= Html.fromHtml(datas);试试解决破折号的问题？
        JSONObject jsonObject=new JSONObject(datas);
        JSONArray jsonArrayDatas=jsonObject.getJSONArray("data");
        //解析文章title和information和urls和分类
        for(int i=0;i<jsonArrayDatas.length();i++){
            Datas datas=new Datas();
            JSONObject jsonObjectBlock=jsonArrayDatas.getJSONObject(i);
            String information=jsonObjectBlock.getString("author");
            datas.setInformation("作者:"+information);
            String titleName=jsonObjectBlock.getString("title");
            datas.setTitles(titleName);
            String url=jsonObjectBlock.getString("link");
            datas.setUrls(url);
            String type2=jsonObjectBlock.getString("superChapterName");
            datas.setTypes2(type2);
            String type1=jsonObjectBlock.getString("chapterName");
            datas.setTypes1(type1);
            datas.setPicture_good(R.drawable.good);
            datas.setPicture_more(R.drawable.more);
            //拿到数组类型的tags[]
            JSONArray jsonArrayTags=jsonObjectBlock.getJSONArray("tags");
            if(jsonArrayTags.length()==0){
                datas.setOfficial("");
                datas.setQuestion("");
            }
            else {
                for (int j = 0; j < jsonArrayTags.length(); j++) {
                    JSONObject jsonObjectMblock = jsonArrayTags.getJSONObject(j);
                    String question = jsonObjectMblock.getString("url");
                    datas.setQuestion("https://www.wanandroid.com/"+question);
                    String official = jsonObjectMblock.getString("url");
                    datas.setOfficial("https://www.wanandroid.com/"+official);
                }
            }

            counter1++;
            if(counter1<7) {
                articleList.add(datas);
                Log.d(TAG, "hello"+datas.getTitles());
            }
            else{
                temp_articles.add(datas);
            }
        }
    }

}
