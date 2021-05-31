package com.example.wanandroid.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.wanandroid.R;
import com.example.wanandroid.datas.Datas_system;

import java.util.List;

public class SystemAdapter extends ArrayAdapter<Datas_system> {
    int resourceId;
    private TextView title,information;
    private ImageView right;
    public SystemAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<Datas_system> objects) {
        super(context,textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Datas_system datas_system=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        initUi(view);
        title.setText(datas_system.getTitle());
        information.setText(datas_system.getInformation());
        right.setImageResource(datas_system.getPicId());
        return view;
    }

    private void initUi(View view){
        title=view.findViewById(R.id.title);
        information=view.findViewById(R.id.information);
        right=view.findViewById(R.id.right);
    }
}
