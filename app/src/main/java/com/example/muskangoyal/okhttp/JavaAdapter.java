package com.example.muskangoyal.okhttp;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class JavaAdapter extends RecyclerView.Adapter<JavaAdapter.MyHolder> {

    ArrayList<GithubUser> githubUsers;
    private Context context;

    public JavaAdapter(ArrayList<GithubUser> githubUsers,Context context) {
        this.githubUsers = githubUsers;
        this.context=context;
    }

    @Override
    public MyHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row, parent, false);
        return new MyHolder(v);
    }

    @Override
    public void onBindViewHolder(final MyHolder holder, int position) {
        final GithubUser githubUser = githubUsers.get(position);
        holder.userName.setText(githubUser.getLogin());
        holder.userUrl.setText(githubUser.getHtml_url());
        holder.userScore.setText(githubUser.getScore());
        Picasso.get().load(githubUser.getAvatar_url())
                .placeholder(R.mipmap.ic_launcher_round)
                .error(R.drawable.ic_launcher_foreground)
                .into(holder.img);
        holder.parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context,SecondActivity.class);
                intent.putExtra("login",githubUser.getLogin());
                intent.putExtra("url",githubUser.getHtml_url());
                intent.putExtra("score",githubUser.getScore());
                intent.putExtra("image_url",githubUser.getAvatar_url());
                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return githubUsers.size();
    }

    public class MyHolder extends RecyclerView.ViewHolder {
        TextView userName, userUrl, userScore;
        ImageView img;
        LinearLayout parent;

        public MyHolder(View itemView) {
            super(itemView);
            userName = itemView.findViewById(R.id.username);
            userUrl = itemView.findViewById(R.id.userurl);
            userScore = itemView.findViewById(R.id.userscore);
            img = itemView.findViewById(R.id.img);
            parent = itemView.findViewById(R.id.parent);
        }
    }
}
