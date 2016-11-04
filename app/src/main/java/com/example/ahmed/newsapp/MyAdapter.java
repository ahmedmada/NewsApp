package com.example.ahmed.newsapp;

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

/**
 * Created by master on 01/11/2016.
 */
public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    ArrayList<NewsItem>newsItems;
    Context context;
    public MyAdapter(Context context,ArrayList<NewsItem>newsItems){
        this.newsItems = newsItems;
        this.context = context;

    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_row_news_item,parent,false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }



    @Override
    public void onBindViewHolder(MyViewHolder holder, final int position) {
        final NewsItem current = newsItems.get(position);
        holder.title.setText(current.getTitle());
        holder.desc.setText(current.getDescription());
        holder.date.setText(current.getPubDate());
        Picasso.with(context).load(current.getSmallPhoto()).into(holder.smallpic);
        holder.next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NewsItem data = current;
                Intent i = new Intent(context,Details.class);
                i.putExtra("f1", data.getTitle());
                i.putExtra("f2", data.getDescription());
                i.putExtra("author", data.getAuthor());
                i.putExtra("img",data.getBigPhoto());
                i.putExtra("link",data.getLink());
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return newsItems.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,desc,date;
        ImageView smallpic;
        LinearLayout next;
        public MyViewHolder(View itemView) {
            super(itemView);
            title = (TextView) itemView.findViewById(R.id.title);
            desc = (TextView) itemView.findViewById(R.id.desc);
            date = (TextView) itemView.findViewById(R.id.date);
            smallpic = (ImageView) itemView.findViewById(R.id.smallpic);
            next = (LinearLayout)itemView.findViewById(R.id.next);
        }
    }
}
