package com.example.wanandroid.fragment;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.NameAdapter;

import com.example.wanandroid.datas.Names;
import com.example.wanandroid.json.JsonPublicPassage;
import com.example.wanandroid.mainactivity.Main_refresh;
import com.example.wanandroid.util.BackInternet;
import com.example.wanandroid.util.InternetUtil_passages;
import com.example.wanandroid.util.UpPullOnScrollListener;

import java.util.ArrayList;
import java.util.List;

public class FragmentGo extends Fragment implements BackInternet, UpPullOnScrollListener {
    List<Names> lists_public=new ArrayList<>();
    InternetUtil_passages internet;
    JsonPublicPassage jsonPublicPassage;
    NameAdapter adapter;
    RecyclerView nameView;
    SwipeRefreshLayout refreshLayout;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        openInternet();
    }

    private void openInternet() {
        internet=new InternetUtil_passages();
        internet.sendRequestWithHttpURLConnection("https://wanandroid.com/wxarticle/chapters/json ",this);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.content_fragment, container, false);
    }

    @Override
    public void BackMethod(String datas) throws Exception {
        jsonPublicPassage=new JsonPublicPassage(datas);
//        jsonPublicPassage.jsonRelease();
        jsonPublicPassage.RealeaseIt();
        lists_public=jsonPublicPassage.getNamesList();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                nameView=getView().findViewById(R.id.nameview);
                LinearLayoutManager manager=new LinearLayoutManager(nameView.getContext());
                nameView.setLayoutManager(manager);
                adapter=new NameAdapter(lists_public);
                nameView.setAdapter(adapter);
                //添加刷新操作
                setRefresh();
            }
        });
    }

    private void setRefresh() {
        nameView.addOnScrollListener(new Main_refresh(this));
    }

    @Override
    public void onLoadMore() {//上拉加载更多
        lists_public.add(jsonPublicPassage.getNames());
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(),"刷新成功",Toast.LENGTH_SHORT).show();
                adapter.setNamesList(lists_public);
                adapter.notifyDataSetChanged();

            }
        });

    }

    @Override
    public void OnRefresh() {
        refreshLayout=getView().findViewById(R.id.refresh);
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
            }
        });
        refreshLayout.setRefreshing(false);
    }
}
