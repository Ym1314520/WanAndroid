package com.example.wanandroid.adapter;

import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.R;
import com.example.wanandroid.datas.Datas_internet;

import java.util.List;

public class InternetAdapter extends RecyclerView.Adapter<InternetAdapter.ViewHolder> {
    private List<Datas_internet> mInternets;
    public  InternetAdapter(List<Datas_internet> internets){
        this.mInternets=internets;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.urlgo,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Datas_internet datasInternet=mInternets.get(position);
        holder.sinal.setText(datasInternet.getName());
        holder.sinal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                v.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(datasInternet.getUrl())));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mInternets.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        Button sinal;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sinal=itemView.findViewById(R.id.sinal);

        }
    }
}
