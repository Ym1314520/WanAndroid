package com.example.wanandroid.mainactivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.PublicPassageAdapter;
import com.example.wanandroid.datas.Datas_publicPassage;
import com.example.wanandroid.json.JsonPublicPassage;
import com.example.wanandroid.util.BackInternet;
import com.example.wanandroid.util.InternetUtil_passages;

import java.util.ArrayList;
import java.util.List;

public class Main_publicPassage extends AppCompatActivity implements BackInternet {
    List<Datas_publicPassage> lists_public=new ArrayList<>();
    InternetUtil_passages internet;
    JsonPublicPassage jsonPublicPassage;
    PublicPassageAdapter adapter;
    ListView lists;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.public_passage);
        openInternet();
    }

    private void openInternet(){
        internet=new InternetUtil_passages();
        internet.sendRequestWithHttpURLConnection("https://www.wanandroid.com/article/list/0/json",this);
    }
    @Override
    public void BackMethod(String datas) throws Exception {
        jsonPublicPassage=new JsonPublicPassage(datas);
//        jsonPublicPassage.jsonRelease();
        lists_public=jsonPublicPassage.getPublicPassages();

        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter=new PublicPassageAdapter(Main_publicPassage.this,R.layout.publicpassages,lists_public);
                lists=findViewById(R.id.list_public);
                lists.setAdapter(adapter);
                lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Datas_publicPassage datas_publicPassage=lists_public.get(position);
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(datas_publicPassage.getUrl_publicPassage())));
                    }
                });
            }
        });
    }

}
