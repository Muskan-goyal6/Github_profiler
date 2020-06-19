package com.example.muskangoyal.okhttp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EdgeEffect;
import android.widget.EditText;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class MainActivity extends AppCompatActivity {
    Button btn;
    EditText et;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn=findViewById(R.id.btn);
        et=findViewById(R.id.et);
        recyclerView=findViewById(R.id.recycleview);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                makenetworkcall("https://api.github.com/search/users?q="+et.getText().toString());
            }
        });
    }

    public void makenetworkcall(String url){
        OkHttpClient client = new OkHttpClient();
        final RecyclerView recyclerView= findViewById(R.id.recycleview);
        Request request = new Request.Builder().url(url).build();

        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                   final String result = response.body().string();
               // final ArrayList<GithubUser> githubUsers = parseJson(result);

                Gson gson =new Gson();
                final ApiResult apiResult =gson.fromJson(result,ApiResult.class);

                   MainActivity.this.runOnUiThread(new Runnable() {
                       @Override
                       public void run() {
                        // UserAdapter userAdapter=new UserAdapter(githubUsers);
                           //UserAdapter userAdapter = new UserAdapter(apiResult.getItems());
                           JavaAdapter javaAdapter=new JavaAdapter(apiResult.getItems(),getBaseContext());
                         recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext()));
                         recyclerView.setAdapter(javaAdapter);
                       }
                   });
            }
        });

        }


    //    public ArrayList<GithubUser> parseJson(String s){
//        ArrayList<GithubUser> githubUserArrayList = new ArrayList<>();
//        try {
//            JSONObject jsonObject = new JSONObject(s);
//            JSONArray jsonArray = jsonObject.getJSONArray("items");
//
//            for (int i=0;i<jsonArray.length();i++){
//                JSONObject jsonObject1 = jsonArray.getJSONObject(i);
//                String login = jsonObject1.getString("login");
//                String url =jsonObject1.getString("html_url");
//                String profile = jsonObject1.getString("avatar_url");
//                String score = jsonObject1.getString("score");
//
//                GithubUser githubUser = new GithubUser(login,url,profile,score);
//                githubUserArrayList.add(githubUser);
//
//            }
//
//        }catch (JSONException e){
//            e.printStackTrace();
//        }
//
//        return githubUserArrayList;
//    }
}
