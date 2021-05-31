package com.example.wanandroid.json;


import com.example.wanandroid.R;
import com.example.wanandroid.datas.Datas_system;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonSystem {
    private String datas;
    private Datas_system datasSystem;
    private List<Datas_system> list_systems=new ArrayList<>();
    private List<Datas_system> temp_systems=new ArrayList<>();
    private int number=0;
    private int size=0;

    public JsonSystem(String datas) {
        this.datas = datas;
    }

    public Datas_system GetDatasSystem(){//拿一个数据对象走
        datasSystem=temp_systems.get(size++);
        return datasSystem;
    }


    public List<Datas_system> getList_systems() {
        return list_systems;
    }

    public void jsonRelease() throws JSONException {
        JSONObject jsonObject=new JSONObject(datas);
        JSONArray jsonArrayData=jsonObject.getJSONArray("data");
        for(int i=0;i<jsonArrayData.length();i++){
            String value = "";
            Datas_system datas_system=new Datas_system();
            JSONObject jsonObjectBlocks= jsonArrayData.getJSONObject(i);
            String typeName=jsonObjectBlocks.getString("name");
            datas_system.setTitle(typeName);
            //开始拿下一层数组里的数据
            JSONArray jsonArrayChildren=jsonObjectBlocks.getJSONArray("children");
            for(int j=0;j<jsonArrayChildren.length();j++){
                JSONObject jsonObjectmBlocks=jsonArrayChildren.getJSONObject(j);
                String childrenName=jsonObjectmBlocks.getString("name");
                value+=childrenName+"  ";
            }
            datas_system.setInformation(value);
            datas_system.setPicId(R.drawable.right);
            if(number<7) {
                list_systems.add(datas_system);
            }
            else{
                temp_systems.add(datas_system);
            }
            number++;
        }
    }
}