package com.example.wanandroid.fragment;

import android.content.Context;
import android.content.Intent;
import android.net.MacAddress;
import android.net.Uri;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.wanandroid.R;
import com.example.wanandroid.adapter.InternetAdapter;
import com.example.wanandroid.datas.Datas_internet;
import com.example.wanandroid.json.JsonInternet;
import com.example.wanandroid.util.BackInternet;
import com.example.wanandroid.util.InternetUtil;

import java.util.ArrayList;
import java.util.List;

import static android.widget.Toast.LENGTH_LONG;


public class FragmentUrlGo extends Fragment implements BackInternet {
    private List<Datas_internet> internets=new ArrayList<>();
    InternetAdapter adapter;
    RecyclerView recyclerView;
    InternetUtil internet;
    JsonInternet jsonInternet;
    EditText search;
    String tempText;
    ImageView confirm;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        openInternet();





    }

    private Datas_internet judgeIt(String temp) {
        for(int i=0;i<internets.size();i++){
            if(internets.get(i).getName().equals(temp)){
                return internets.get(i);
            }
            else{continue;}
        }
        return null;
    }

    private void openInternet() {
        internet=new InternetUtil();
        internet.sendRequestWithHttpURLConnection("https://www.wanandroid.com/navi/json",this);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        return inflater.inflate(R.layout.urlgo_fragment, container, false);
    }

    @Override
    public void BackMethod(String datas) throws Exception {
        jsonInternet=new JsonInternet(datas);
        jsonInternet.jsonRelease();
        internets=jsonInternet.getInternets();
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                initUi();
//                LinearLayoutManager manager=new LinearLayoutManager(recyclerView.getContext());
//                recyclerView.setLayoutManager(manager);
                StaggeredGridLayoutManager manager=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
                recyclerView.setLayoutManager(manager);
                adapter=new InternetAdapter(internets);
                recyclerView.setAdapter(adapter);
                doIt();//recyclerview监听器内容
            }
        });

    }

    private void initUi(){
        recyclerView=getView().findViewById(R.id.urlgo_recyclerview);
        search=getActivity().findViewById(R.id.findIt);
        confirm=getActivity().findViewById(R.id.confirm);
    }

    private void doIt(){
        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tempText=search.getText().toString();
                if (judgeIt(tempText)== null) {
                    Toast.makeText(getActivity(), "没有该网站资源，敬请期待...", Toast.LENGTH_SHORT).show();
                }
                else{
                    startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(judgeIt(tempText).getUrl())));
                }
            }
        });
    }
}
