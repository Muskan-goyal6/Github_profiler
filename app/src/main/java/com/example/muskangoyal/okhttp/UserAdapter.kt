package com.example.muskangoyal.okhttp

import android.content.Context
import android.content.Intent
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.squareup.picasso.Picasso


class UserAdapter(val githubUsers: ArrayList<GithubUser>):RecyclerView.Adapter<UserAdapter.MyHolder>(){

    override fun onBindViewHolder(holder: MyHolder, position: Int) {

            val username = holder.itemView.findViewById<TextView>(R.id.username);
            val userurl = holder.itemView.findViewById<TextView>(R.id.userurl);
            val userscore = holder.itemView.findViewById<TextView>(R.id.userscore);
            val img = holder.itemView.findViewById<ImageView>(R.id.img)
            val githubUser = githubUsers[position]
            username.text = githubUser.login
            userurl.text = githubUser.html_url
            userscore.text = githubUser.score
            Picasso.get().load(githubUser.avatar_url)
                    .placeholder(R.mipmap.ic_launcher_round)
                    .error(R.drawable.ic_launcher_foreground)
                    .into(img)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        val v=LayoutInflater.from(parent.context).inflate(R.layout.item_row,parent,false)
        return MyHolder(v)
    }

    override fun getItemCount(): Int {
        return githubUsers.size
    }


    inner class MyHolder(itemView:View):RecyclerView.ViewHolder(itemView)

}