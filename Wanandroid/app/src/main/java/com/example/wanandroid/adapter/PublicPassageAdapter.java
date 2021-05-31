package com.example.wanandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wanandroid.R;
import com.example.wanandroid.datas.Datas_publicPassage;

import java.util.List;

public class PublicPassageAdapter extends ArrayAdapter<Datas_publicPassage> {

    private TextView title;
    private int resourceId;
    public PublicPassageAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<Datas_publicPassage> objects) {
        super(context,textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Datas_publicPassage datas=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        initUi(view);
        title.setText(datas.getTitle());
        return view;
    }
    private void initUi(View view){
        title=view.findViewById(R.id.textView);
    }
}
