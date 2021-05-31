package com.example.wanandroid.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wanandroid.R;
import com.example.wanandroid.mainactivity.Main;

public class loginMe extends AppCompatActivity {

    SharedPreferences pref,prefs;
    SharedPreferences.Editor editor;
    CheckBox rePass;
    TextView register;
    Button button;
    EditText text1;
    EditText text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginit);
        init();
        boolean isRemember=pref.getBoolean("rePass",false);
        if(isRemember){
            //将账号密码设置到文本框中
            text1.setText(pref.getString("account",""));
            text2.setText(pref.getString("password",""));
            rePass.setChecked(true);
        }
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(loginMe.this,registerMe.class);
                startActivity(intent);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(judge()){
                    editor=pref.edit();
                    if(rePass.isChecked()){
                        editor.putBoolean("rePass",true);
                        editor.putString("account",text1.getText().toString());
                        editor.putString("password",text2.getText().toString());
                    }
                    else{
                        editor.clear();
                    }
                    editor.apply();
                    Toast.makeText(loginMe.this,"登陆成功",Toast.LENGTH_SHORT).show();
                    Intent intent2=new Intent(loginMe.this, Main.class);
                    startActivity(intent2);
                }
                else{
                    Toast.makeText(loginMe.this,"账号密码错误！",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    private void init(){
        register = findViewById(R.id.textView);
        button=findViewById(R.id.button1);
        text1=findViewById(R.id.editText3);
        text2=findViewById(R.id.editText4);
        pref=getSharedPreferences("users",MODE_PRIVATE);
        rePass=findViewById(R.id.checkBox);
        prefs=getSharedPreferences("datas",MODE_PRIVATE);
    }
    private boolean judge(){
        for(int i=0;i<10;i++){
            if(prefs.getString("account"+i,"").equals(text1.getText().toString())){
                return prefs.getString("password"+i,"").equals(text2.getText().toString());
            }
        }
        return false;
    }
}
