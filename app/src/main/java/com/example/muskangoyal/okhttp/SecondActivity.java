package com.example.muskangoyal.okhttp;

import android.content.Context;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class SecondActivity extends AppCompatActivity {
    TextView tv1,tv2,tv3;
    ImageView img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
         tv1=findViewById(R.id.tv1);
       tv2 = findViewById(R.id.tv2);
       tv3=findViewById(R.id.tv3);
       img2 = findViewById(R.id.img2);

       getIncomingIntent();

    }

    public void getIncomingIntent(){
        if(getIntent().hasExtra("login")&&getIntent().hasExtra("url")&&getIntent().hasExtra("score")
                &&getIntent().hasExtra("image_url")){
            String login =getIntent().getStringExtra("login");
            String url = getIntent().getStringExtra("url");
            String score = getIntent().getStringExtra("score");
            String image_url = getIntent().getStringExtra("image_url");
            setImage(login,url,score,image_url);
        }
    }

    private void setImage(String login,String url,String score,String image_url){

        TextView tv1 = findViewById(R.id.tv1);
        tv1.setText(login);
        TextView tv2 = findViewById(R.id.tv2);
        tv2.setText(url);
        TextView tv3 = findViewById(R.id.tv3);
        tv3.setText(score);
        ImageView img2 = findViewById(R.id.img2);
        Picasso.get().load(image_url)
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.ic_launcher_foreground)
                .into(img2);

    }
}
