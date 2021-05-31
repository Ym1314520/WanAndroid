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
import com.example.wanandroid.datas.Datas_passage;

import java.util.List;

public class PassageAdapter extends ArrayAdapter<Datas_passage> {
    ImageView user;
    ImageView love;
    TextView author,time,title;

    private  int resourceId;
    public PassageAdapter(@NonNull Context context, int textViewResourceId, @NonNull List<Datas_passage> objects) {
        super(context, textViewResourceId, objects);
        resourceId=textViewResourceId;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Datas_passage passages=getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
        initUi(view);
        user.setImageResource(R.drawable.users);
        love.setImageResource(R.drawable.pick);
        author.setText(passages.getAuthor());
        title.setText(passages.getTitle());
        time.setText(passages.getTime());
        return view;
    }

    private void initUi(View view){
        user= view.findViewById(R.id.user);
        love=view.findViewById(R.id.love);
        author=view.findViewById(R.id.author);
        time=view.findViewById(R.id.time);
        title=view.findViewById(R.id.title);
    }
}
