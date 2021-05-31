package com.example.wanandroid.mainactivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.PassageAdapter;
import com.example.wanandroid.datas.Datas_passage;
import com.example.wanandroid.json.Json;
import com.example.wanandroid.json.JsonPassage;
import com.example.wanandroid.util.BackInternet;
import com.example.wanandroid.util.InternetUtil_passages;

import java.util.List;

public class Main_passage extends AppCompatActivity implements BackInternet {
    List<Datas_passage> datas_passageList;
    InternetUtil_passages internetUtil_passages;
    JsonPassage jsonPassage;
    TextView getPage;
    Button button;
    String page="0";
    ListView lists;
    PassageAdapter adapter;
    private String TAG="Ym";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.passage_list);
        initUI();
        openInternet(page);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=getPage.getText().toString();
                openInternet(page);
            }
        });
    }

    private void initUI(){
        getPage=findViewById(R.id.text_page);
        button=findViewById(R.id.button);
    }
    private void openInternet(String page){
        String uri="https://www.wanandroid.com/article/list/"+page+"/json";
        internetUtil_passages=new InternetUtil_passages();
        internetUtil_passages.sendRequestWithHttpURLConnection(uri,this);
    }
    @Override
    public void BackMethod(String datas) throws Exception {
        jsonPassage=new JsonPassage(datas);
        Log.d(TAG, "BackMethod: "+datas);
        jsonPassage.jsonRelease();
        datas_passageList=jsonPassage.getLists_passage();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter=new PassageAdapter(Main_passage.this,R.layout.passages,datas_passageList);
                lists=findViewById(R.id.listView);
                lists.setAdapter(adapter);
                lists.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Datas_passage datasPassage=datas_passageList.get(position);
                        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(datasPassage.getLink_passage())));
                    }
                });
            }
        });
    }
}
