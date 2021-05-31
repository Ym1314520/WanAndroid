package com.example.wanandroid.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.datas.Datas_program;
import com.example.wanandroid.datas.Names;

import java.util.List;

public class NameAdapter extends RecyclerView.Adapter<NameAdapter.ViewHolder>{
    private List<Names> namesList;


    public void setNamesList(List<Names> names){
        namesList=names;
    }
    public NameAdapter(List<Names> names){
        namesList=names;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).
                inflate(R.layout.contents,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Names datas=namesList.get(position);
        holder.name.setText(datas.getName());
        holder.user.setImageResource(datas.getPic());
    }

    @Override
    public int getItemCount() {
        return namesList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView name;
        ImageView user;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name=itemView.findViewById(R.id.name);
            user=itemView.findViewById(R.id.user);
        }
    }
}
