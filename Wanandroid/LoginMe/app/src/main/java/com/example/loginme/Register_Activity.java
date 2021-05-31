package com.example.loginme;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Register_Activity extends AppCompatActivity  {
    Button button1;
    EditText editText1;
    EditText editText2;
    static String zh="123";
    static String mm="yumeng";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        button1=(Button) findViewById(R.id.button3);
        editText1=findViewById(R.id.editText3);
        editText2=findViewById(R.id.editText4);
        button1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                zh=editText1.getText().toString();
                mm=editText2.getText().toString();
                Toast.makeText(Register_Activity.this,"成功注册！",Toast.LENGTH_SHORT).show();
                Intent intent=new Intent(Register_Activity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
//    @Override
//    public void onClick(View v) {
//        editText1=findViewById(R.id.editText3);
//        editText2=findViewById(R.id.editText4);
//        for(int i=0;i<zh.length;i++){
//            if(zh[i]==null){
//                zh[i]=editText1.getText().toString();
//                mm[i]=editText2.getText().toString();
//                break;
//            }
//        }
//        Toast.makeText(Register_Activity.this,"成功注册！",Toast.LENGTH_SHORT).show();
//
//    }
}
