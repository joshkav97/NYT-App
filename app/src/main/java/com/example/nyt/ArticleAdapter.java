package com.example.nyt;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class ArticleAdapter extends RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder> {

    public ArrayList <Article> articles;
    @NonNull
    @Override
    public ArticleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.article, parent, false);

        ArticleViewHolder articleViewHolder = new ArticleViewHolder(view);
        return articleViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ArticleViewHolder holder, int position) {
    final Article articleAtPosition = articles.get(position);
        holder.newsHeadline.setText(articleAtPosition.getHeadline());
        holder.newsDetails.setText(articleAtPosition.getSummary());

        holder.view.setOnClickListener(new View.OnClickListener(){
        @Override
        public void onClick(View view) {
            Context context = view.getContext();
            Intent intent = new Intent(context, ArticleDetailActivity.class);
            intent.putExtra("ArticleID", articleAtPosition.getArticleID());
            context.startActivity(intent);
        }
    });

        holder.newsShareButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Context context = view.getContext();
                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.putExtra(Intent.EXTRA_TEXT, articleAtPosition.getHeadline());
                intent.setType("text/plain");
                context.startActivity(intent);
            }
        });
}

    @Override
    public int getItemCount() {
        return articles.size();
    }

    public void setData(ArrayList <Article> articles) {
        this.articles = articles;
    }

    public class ArticleViewHolder extends RecyclerView.ViewHolder{
        public TextView newsHeadline;
        public TextView newsDetails;
        public ImageView newsShareButton;
        public View view;

        public ArticleViewHolder(View itemView) {
            super(itemView);
            view = itemView;
            newsHeadline = itemView.findViewById(R.id.newsHeadline);
            newsDetails = itemView.findViewById(R.id.newsDetails);
            newsShareButton = itemView.findViewById(R.id.newsShareButton);
        }
    }
}

