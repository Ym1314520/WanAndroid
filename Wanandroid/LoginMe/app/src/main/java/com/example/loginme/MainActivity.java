package com.example.loginme;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity{
    Button button1, button2;
    EditText editText1;
    EditText editText2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        editText1 = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);

        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Register_Activity.mm.equals(editText2.getText().toString())&&Register_Activity.zh.equals(editText1.getText().toString())) {
                    Toast.makeText(MainActivity.this, "成功登录！", Toast.LENGTH_SHORT).show();
                    Intent intent2 = new Intent(MainActivity.this, MyWorld2.class);
                    startActivity(intent2);
                }
                else {
                    Toast.makeText(MainActivity.this, "账号密码错误！", Toast.LENGTH_SHORT).show();
                }
            }
        });
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intents = new Intent(MainActivity.this, Register_Activity.class);
                startActivity(intents);
            }
        });

    }

}
