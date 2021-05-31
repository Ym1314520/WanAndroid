package com.example.wanandroid.mainactivity;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.WindowManager;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.example.wanandroid.R;
import com.example.wanandroid.fragment.FragmentGo;
import com.example.wanandroid.fragment.FragmentUrlGo;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;

public class Main_go extends AppCompatActivity{
    ViewPager2 vp2;
    BottomNavigationView bottomNavigationView;
    ArrayList<Fragment> list= new ArrayList<>();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_ADJUST_PAN);
        setContentView(R.layout.go);

        initUi();
        list.add(new FragmentUrlGo());
        list.add(new FragmentGo());

        vp2.setAdapter(new FragmentStateAdapter(this) {//这里填this是选择跟随活动的生命周期，又是一个小细节
            @NonNull
            @Override
            public Fragment getItem(int position) {
                return list.get(position);
            }

            @Override
            public int getItemCount() {
                return list.size();
            }
        });

        vp2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
            }
        });

        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                vp2.setCurrentItem(menuItem.getOrder());
                return true;
            }
        });
    }

    private void initUi() {
        vp2=findViewById(R.id.vp2);
        bottomNavigationView=findViewById(R.id.bottomview);
    }
}
