package com.example.wanandroid.mainactivity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.ProgramAdapter;
import com.example.wanandroid.datas.Datas_program;
import com.example.wanandroid.util.UpPullOnScrollListener;
import com.example.wanandroid.json.JsonPrograms;
import com.example.wanandroid.util.BackInternet;
import com.example.wanandroid.util.InternetUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class Main_Program extends AppCompatActivity implements BackInternet, UpPullOnScrollListener {
    private List<Datas_program> programList = new ArrayList<>();
    private RecyclerView recyclerView_progress;
    private ProgramAdapter programAdapter;
    private InternetUtil internetUtil;
    private JsonPrograms jsonPrograms;
    private SwipeRefreshLayout refreshLayout;
    private String page = "294";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.program_lists);
        recyclerView_progress = findViewById(R.id.Progress_recyclerview);
        openInternet();

    }


    private void openInternet() {
        internetUtil = new InternetUtil();
        internetUtil.sendRequestWithHttpURLConnection("https://www.wanandroid.com/project/list/1/json?cid=" + page, this);
    }

    @Override
    public void BackMethod(String datas) throws Exception {
        jsonPrograms = new JsonPrograms(datas);
        jsonPrograms.jsonRelease();
        programList = jsonPrograms.getDatasProgramList();
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                LinearLayoutManager manager = new LinearLayoutManager(recyclerView_progress.getContext());
                recyclerView_progress.setLayoutManager(manager);
                programAdapter = new ProgramAdapter(programList);
                recyclerView_progress.setAdapter(programAdapter);
                setRefresh();
            }
        });
    }

    private void setRefresh() {
        recyclerView_progress.addOnScrollListener(new Main_refresh(this));
    }

    @Override
    public void onLoadMore() {
        if (jsonPrograms.getProgram() == null) {
            Toast.makeText(Main_Program.this, "正在拼命更新中，敬请期待...", Toast.LENGTH_SHORT).show();
        } else {
            programList.add(jsonPrograms.getProgram());
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
                    Toast.makeText(Main_Program.this, "刷新成功", Toast.LENGTH_SHORT).show();
                    programAdapter.setmProgramList(programList);
                    programAdapter.notifyDataSetChanged();
                }
            });
        }
    }

    @Override
    public void OnRefresh() {
        setRefreshLayout();
    }

    //recyclerview刷新操作
    private void setRefreshLayout() {
        refreshLayout = findViewById(R.id.refresh);
        refreshLayout.setColorSchemeResources(R.color.color);
        refreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                refresh();
            }
        });
    }

    private void refresh() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                //切回主线程
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        String page1 = "402", page2 = "314";
                        Random random = new Random();
                        page = random.nextBoolean() ? page1 : page2;
                        openInternet();//网络申请，重新进入子线程
                        programAdapter.notifyDataSetChanged();//通知数据发生变化
                        refreshLayout.setRefreshing(false);
                    }
                });
            }
        }).start();
    }
}