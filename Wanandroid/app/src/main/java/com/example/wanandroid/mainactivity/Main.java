package com.example.wanandroid.mainactivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioButton;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.ArticleAdapter;
import com.example.wanandroid.adapter.PictureAdapter;
import com.example.wanandroid.datas.Datas;
import com.example.wanandroid.util.UpPullOnScrollListener;
import com.example.wanandroid.json.Json;
import com.example.wanandroid.util.BackInternet;
import com.example.wanandroid.util.InternetUtil;

import org.json.JSONException;

import java.util.ArrayList;
import java.util.List;


public class Main extends AppCompatActivity implements BackInternet, UpPullOnScrollListener {
    private static final String TAG ="MainHaveJson";
    private List<Datas> articles =new ArrayList<>();
    private RecyclerView recyclerView;
    private ViewPager pictures;
    private int[] mImage=new int[5];
    private InternetUtil internetUtil;
    private Json json;
    private RadioButton rb1,rb2,rb3;
    private ArticleAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.article_list);

        //设置pager适配器
        initPicture();
        PictureAdapter pictureAdapter=new PictureAdapter(mImage);
        pictures.setAdapter(pictureAdapter);


        recyclerView=findViewById(R.id.recyclerView);
        openInternet();//开始请求网络
        initRadiobutton();

        rb2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(Main.this, Main_go.class);
                startActivity(intent);
            }
        });
        rb3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1=new Intent(Main.this,Main_Program.class);
                startActivity(intent1);
            }
        });
        rb1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2=new Intent(Main.this,Main_passage.class);
                startActivity(intent2);
            }
        });
    }

    private void initRadiobutton(){
        rb1=findViewById(R.id.mainpage);
        rb2=findViewById(R.id.pragram);
        rb3=findViewById(R.id.go);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.mainpassage:
                startActivity(new Intent(Main.this,Main_system.class));
                break;
            case R.id.search:
                Intent intent=new Intent(Main.this, Main_go.class);
                startActivity(intent);
                break;
            case R.id.progress:
                Intent intent1=new Intent(Main.this,Main_Program.class);
                startActivity(intent1);
                break;
            case R.id.collection:
                Intent intent2=new Intent(Main.this, Main_go.class);
                startActivity(intent2);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    private void initPicture(){

        pictures=findViewById(R.id.vp1);
        mImage[0]=R.drawable.p1;
        mImage[1]=R.drawable.p2;
        mImage[2]=R.drawable.p3;
    }


    public void openInternet(){
        internetUtil =new InternetUtil();
        internetUtil.sendRequestWithHttpURLConnection("https://www.wanandroid.com/article/top/json",this);
    }
    @Override
    public void BackMethod(final String data) throws JSONException {//网络请求回调,拿回得到的数据字符串
        Log.d(TAG, "BackMethod: "+data);
        json=new Json(data);//解析字符串
        json.jsonRelease();
        articles=json.getArticleList();
        runOnUiThread(new Runnable() {//拿到全部解析数据，并在Datas类里设置好之后，切换到主线程，在主线程里更新UI：recyclerview
            @Override
            public void run() {
                try {
                    json.jsonRelease();
                    LinearLayoutManager manager=new LinearLayoutManager(recyclerView.getContext());
                    recyclerView.setLayoutManager(manager);
                    adapter=new ArticleAdapter(articles);
                    recyclerView.setAdapter(adapter);
                    setRefresh();//设置刷新
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private void setRefresh() {
        recyclerView.addOnScrollListener(new Main_refresh(this));
    }


    @Override
    public void onLoadMore() {
        articles.add(json.getData());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }
        }).start();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(Main.this,"刷新成功", Toast.LENGTH_SHORT).show();
                adapter.setmDataList(articles);
                adapter.notifyDataSetChanged();
            }
        });

    }

    @Override
    public void OnRefresh() {
    }
}
