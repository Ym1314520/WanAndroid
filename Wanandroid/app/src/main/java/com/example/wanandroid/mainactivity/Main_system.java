package com.example.wanandroid.mainactivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.SystemAdapter;
import com.example.wanandroid.datas.Datas_system;
import com.example.wanandroid.json.JsonSystem;
import com.example.wanandroid.util.BackInternet;
import com.example.wanandroid.util.InternetUtil;

import java.util.List;

public class Main_system extends AppCompatActivity implements BackInternet {
    private List<Datas_system> datas_systems;
    InternetUtil internet;
    JsonSystem jsonSystem;
    SystemAdapter adapter;
    ListView listViewSystem;
    SwipeRefreshLayout refreshLayout;
    Main_refresh refresh;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.system_list);
        openInternet();
//        setRefreshLayout();
    }

    private void setRefreshLayout() {
        refreshLayout=findViewById(R.id.load);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(1000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        refresh();
                    }
                });

            }
        });
    }
    private void refresh() {
        datas_systems.add(jsonSystem.GetDatasSystem());
        adapter=new SystemAdapter(Main_system.this,R.layout.systems,datas_systems);
        listViewSystem=findViewById(R.id.listview_system);
        listViewSystem.setAdapter(adapter);
        listViewSystem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Datas_system datas_system=datas_systems.get(position);
            }
        });
        refreshLayout.setRefreshing(false);
    }


    private void openInternet() {
        internet=new InternetUtil();
        internet.sendRequestWithHttpURLConnection("https://www.wanandroid.com/tree/json",this);
    }

    @Override
    public void BackMethod(String datas) throws Exception {
        jsonSystem=new JsonSystem(datas);
        jsonSystem.jsonRelease();
        datas_systems=jsonSystem.getList_systems();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                adapter=new SystemAdapter(Main_system.this,R.layout.systems,datas_systems);
                listViewSystem=findViewById(R.id.listview_system);
                listViewSystem.setAdapter(adapter);
                listViewSystem.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Datas_system datas_system=datas_systems.get(position);

                    }
                });
            }
        });
    }

//    Main_refresh.UpPullOnScrollListener onScrollListener=new Main_refresh.UpPullOnScrollListener() {
//        @Override
//        public void onLoadMore() {
////            datas_systems.add(jsonSystem.GetDatasSystem());
//            setRefreshLayout();
//        }
//
//        @Override
//        public void OnRefresh() {
//
//        }
//    };
}
