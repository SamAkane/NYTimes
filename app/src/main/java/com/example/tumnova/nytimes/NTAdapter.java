package com.example.tumnova.nytimes;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tumnova.nytimes.model.NewsItem;

import java.util.Date;
import java.util.List;

public class NTAdapter extends RecyclerView.Adapter<NTAdapter.ViewHolder> {
    private final List<NewsItem> news;
    private final Context context;
    private final LayoutInflater inflater;

    public NTAdapter(Context c, List<NewsItem> n){
        context = c;
        news = n;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NTAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(
                inflater.inflate(R.layout.news_item, viewGroup, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull NTAdapter.ViewHolder viewHolder, int i) {
        NewsItem n = news.get(i);

        viewHolder.category.setText(n.getCategory().getName());
        viewHolder.title.setText(n.getTitle());
        viewHolder.previewText.setText(n.getPreviewText());
        Glide.with(context).load(n.getImageUrl()).into(viewHolder.image);
        viewHolder.publicationDate.setText(n.getPublishDate().toString());
        viewHolder.publicationDate.setText(DateUtils.getRelativeTimeSpanString(
                n.getPublishDate().getTime(), System.currentTimeMillis(),
                DateUtils.MINUTE_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE));
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    private String dsteFormat(Date date) {

        return "";
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView category;
        public final TextView title;
        public final TextView previewText;
        public final ImageView image;
        public final TextView publicationDate;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            category = itemView.findViewById(R.id.category);
            title = itemView.findViewById(R.id.title);
            previewText = itemView.findViewById(R.id.preview_text);
            image = itemView.findViewById(R.id.image);
            publicationDate = itemView.findViewById(R.id.publication_time);
        }
    }
}
