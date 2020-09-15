package com.example.handler;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                EditText e=findViewById(R.id.text2);

                e.setText("123");

            }
        };
        Button button=findViewById(R.id.button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.postDelayed(runnable,10000);
            }
        });

        final Handler handler2 = new Handler(){
            @Override
            public void handleMessage(Message msg) {
                ArrayList<String> list=(ArrayList<String>)msg.obj;
                String s="";
                for (int i=0;i<list.size();i++){
                    s=list.get(i)+s;
                }
                EditText e=findViewById(R.id.text);
                e.setText(s+" "+msg.arg1);
            }
        };
        final Runnable runnable2 = new Runnable() {
            @Override
            public void run() {
                EditText e=findViewById(R.id.text);
                int progress = 0;
                ArrayList<String> list=new ArrayList<String>();
                list.add(">");
                while(progress < 100) {
                    Message msg = new Message();
                    progress +=10;
                    list.add("-");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    msg.obj=list;
                    msg.arg1=progress;
                    handler2.sendMessage(msg);
                }

            }
        };
        Button button2=findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.postDelayed(runnable,10000);
                Thread workThread = new Thread(null, runnable2, "runnable2");
                workThread.start();
            }
        });
    }
}