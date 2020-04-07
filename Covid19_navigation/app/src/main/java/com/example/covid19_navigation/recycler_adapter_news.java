package com.example.covid19_navigation;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class recycler_adapter_news extends RecyclerView.Adapter<recycler_adapter_news.MyViewHolder> {

    private LayoutInflater inflater;
    private ArrayList<articles> dataModelArrayList;
    private recycler_adapter_news.OnNoteList onNoteList;

    public recycler_adapter_news(Context ctx, ArrayList<articles> dataModelArrayList, recycler_adapter_news.OnNoteList onNoteList) {
        inflater = LayoutInflater.from(ctx);
        this.dataModelArrayList = dataModelArrayList;
        this.onNoteList = onNoteList;
    }

    @NonNull
    @Override
    public recycler_adapter_news.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = inflater.inflate(R.layout.row_layout_news, parent, false);
        recycler_adapter_news.MyViewHolder holder = new recycler_adapter_news.MyViewHolder(view,onNoteList);

        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.title_news.setText(dataModelArrayList.get(position).getTitle());
        holder.author_news.setText("Author : "+dataModelArrayList.get(position).getAuthor());
        holder.date_news.setText("Published On : "+dataModelArrayList.get(position).getPublishedAt());
        holder.name_news.setText("The Times Of India");
        holder.desc_news.setText(dataModelArrayList.get(position).getDescription());


    }

    @Override
    public int getItemCount() {
        return dataModelArrayList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView name_news, desc_news, date_news, title_news, author_news;
        OnNoteList onNoteList;

        public MyViewHolder(@NonNull View itemView, OnNoteList onNoteList) {
            super(itemView);
            name_news = itemView.findViewById(R.id.name_news);
            desc_news  =itemView.findViewById(R.id.desc_news);
            date_news = itemView.findViewById(R.id.date_news);
            title_news = itemView.findViewById(R.id.title_news);
            author_news = itemView.findViewById(R.id.author_news);
            this.onNoteList = (OnNoteList) onNoteList;
            itemView.setOnClickListener(this);



        }

        @Override
        public void onClick(View v) {
            onNoteList.OnnoteClick(getAdapterPosition());


        }
    }
    public interface OnNoteList {
        void OnnoteClick(int position);


    }
}
