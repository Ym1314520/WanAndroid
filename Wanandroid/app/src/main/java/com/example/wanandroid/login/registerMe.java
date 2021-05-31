package com.example.wanandroid.login;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.wanandroid.R;

public class registerMe extends AppCompatActivity {
    SharedPreferences pref;
    SharedPreferences.Editor editor;
    Button register;
    EditText text1;
    EditText text2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        init();
        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(text1.getText().toString().length()<8||text2.getText().toString().length()<8){
                    Toast.makeText(registerMe.this,"请输入至少八位数的账号和密码",Toast.LENGTH_SHORT).show();
                }
                else {
//                    data.putUser(text1.getText().toString(), text2.getText().toString());
                    int n=pref.getInt("counter",0);
                    editor.putString("account"+n,text1.getText().toString());
                    editor.putString("password"+n,text2.getText().toString());
                    editor.putInt("counter",n+1);
                    editor.apply();
                    Toast.makeText(registerMe.this, "注册成功！", Toast.LENGTH_SHORT).show();
                    Intent intent=new Intent(registerMe.this,loginMe.class);
                    startActivity(intent);
                    finish();
                }
            }
        });
    }
    private void init(){
        register=findViewById(R.id.button1);
        text1=findViewById(R.id.editText3);
        text2=findViewById(R.id.editText4);
        pref=getSharedPreferences("datas",MODE_PRIVATE);
        editor=pref.edit();

    }
}
