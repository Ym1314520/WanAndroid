package com.example.wanandroid.mainactivity;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.wanandroid.util.UpPullOnScrollListener;

public class Main_refresh extends RecyclerView.OnScrollListener {
    private UpPullOnScrollListener listener;
    public Main_refresh(UpPullOnScrollListener listener){
        this.listener=listener;
    }

    boolean isUpScroll =false;//是否正在上滑标记

    @Override
    public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
        super.onScrollStateChanged(recyclerView, newState);
        LinearLayoutManager manager= (LinearLayoutManager) recyclerView.getLayoutManager();

        if(newState==RecyclerView.SCROLL_STATE_IDLE){

            int itemCount=manager.getItemCount();//项目总数
            int lastItemPosition=manager.findLastCompletelyVisibleItemPosition();

            if(lastItemPosition==(itemCount-1)&&isUpScroll){
                listener.onLoadMore();
            }

        int firstItemPosition=manager.findFirstCompletelyVisibleItemPosition();
        if(firstItemPosition==0&&!isUpScroll){
            listener.OnRefresh();
        }
    }

    }
    public void onScrolled(RecyclerView recyclerView,int dx, int dy){
        super.onScrolled(recyclerView,dx,dy);
        isUpScroll=dy>0;
    }


}
