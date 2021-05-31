package com.example.wanandroid.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.wanandroid.R;
import com.example.wanandroid.datas.Datas_program;

import java.util.List;

public class ProgramAdapter extends RecyclerView.Adapter<ProgramAdapter.ViewHolder>  {
    private List<Datas_program> mProgramList;

    public void setmProgramList(List<Datas_program> mProgramList) {
        this.mProgramList = mProgramList;
    }

    public ProgramAdapter(List<Datas_program> programList){
        this.mProgramList=programList;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView mainPicture,titlePicture;
        TextView title,mTitle,time,author;
    public ViewHolder(@NonNull View itemView) {
        super(itemView);
        mainPicture=itemView.findViewById(R.id.mainPicture);
        titlePicture=itemView.findViewById(R.id.lovePicture);
        title=itemView.findViewById(R.id.title);
        mTitle=itemView.findViewById(R.id.mTitle);
        time=itemView.findViewById(R.id.time);
        author=itemView.findViewById(R.id.author);
    }
}
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.programs,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Datas_program datas=mProgramList.get(position);
        holder.titlePicture.setImageResource(datas.getPicId2());
        holder.title.setText(datas.getTitle());
        holder.mTitle.setText(datas.getInformation());
        holder.author.setText(datas.getAuthor());
        Glide.with(holder.itemView).load(datas.getPicId1()).into(holder.mainPicture);
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(datas.getLink())));
            }
        });
        holder.time.setText(datas.getTime());

    }

    @Override
    public int getItemCount() {
        return mProgramList.size();
    }


}
