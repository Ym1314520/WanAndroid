package com.example.wanandroid.adapter;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.wanandroid.R;
import com.example.wanandroid.datas.Datas;
import com.example.wanandroid.mainactivity.Main_passage;

import java.util.List;

import static android.content.ContentValues.TAG;

public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ViewHolder> {
    private List<Datas> mDataList;

    public void setmDataList(List<Datas> dataList){
        mDataList=dataList;
    }
    public ArticleAdapter(List<Datas> articles){
        mDataList=articles;
    }
    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView good;
        ImageView more;
        TextView title;
        TextView information;
        TextView types2,types1;
        public ViewHolder(@NonNull View view) {
            super(view);
            good=view.findViewById(R.id.good);
            more=view.findViewById(R.id.more);
            title=view.findViewById(R.id.title);
            information=view.findViewById(R.id.information);
            types2=view.findViewById(R.id.type);
            types1=view.findViewById(R.id.type2);
        }
    }

    @NonNull
    @Override
    public ArticleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.articles,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleAdapter.ViewHolder holder, int position) {
        final Datas data=mDataList.get(position);
        holder.good.setImageResource(data.getPicture_good());
        holder.more.setImageResource(data.getPicture_more());
        holder.more.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(v.getContext(),Main_passage.class));
            }
        });
        holder.information.setText(data.getInformation());
        holder.title.setText(data.getTitles());
        holder.title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url=data.getUrls();
                v.getContext().startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse(url)));
            }
        });
        holder.types2.setText(data.getTypes2()+"/");
        holder.types1.setText(data.getTypes1());
        holder.types2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url1= data.getOfficial();
                if(url1.length()==0){
                    Toast.makeText(v.getContext(),"暂未开放该功能，敬请期待...",Toast.LENGTH_SHORT).show();
                }
                else {
                    v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url1)));
                }
            }
        });
        holder.types1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url2= data.getQuestion();
                if(url2.length()==0){
                    Toast.makeText(v.getContext(),"暂未开放该功能，敬请期待...",Toast.LENGTH_SHORT).show();
                }
                else {
                    v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url2)));
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }
}
