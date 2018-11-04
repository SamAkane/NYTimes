package com.example.tumnova.nytimes;

import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import android.text.format.DateUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tumnova.nytimes.activities.NewsDetailsActivity;
import com.example.tumnova.nytimes.model.NewsItem;

import java.util.List;

public class NTAdapter extends RecyclerView.Adapter<NTAdapter.ViewHolder> {
    private final List<NewsItem> news;
    private final Context context;
    private final LayoutInflater inflater;

    public NTAdapter(Context context, List<NewsItem> newsItems){
        this.context = context;
        news = newsItems;
        inflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public NTAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        return new ViewHolder(
                inflater.inflate(R.layout.news_item, viewGroup, false), context
        );
    }

    @Override
    public void onBindViewHolder(@NonNull final NTAdapter.ViewHolder viewHolder, int i) {
        NewsItem n = news.get(i);

        viewHolder.category.setText(n.getCategory().getName());
        viewHolder.title.setText(n.getTitle());
        viewHolder.previewText.setText(n.getPreviewText());
        Glide.with(context).load(n.getImageUrl()).into(viewHolder.image);
        viewHolder.publicationDate.setText(n.getPublishDate().toString());
        viewHolder.publicationDate.setText(DateUtils.getRelativeTimeSpanString(
                n.getPublishDate().getTime(), System.currentTimeMillis(),
                DateUtils.MINUTE_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE));
        viewHolder.bind(n);
    }

    @Override
    public int getItemCount() {
        return news.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView category;
        public final TextView title;
        public final TextView previewText;
        public final ImageView image;
        public final TextView publicationDate;
        private NewsItem n;

        public ViewHolder(@NonNull final View itemView, final Context context) {
            super(itemView);
            category = itemView.findViewById(R.id.category);
            title = itemView.findViewById(R.id.title);
            previewText = itemView.findViewById(R.id.preview_text);
            image = itemView.findViewById(R.id.image);
            publicationDate = itemView.findViewById(R.id.publication_time);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = NewsDetailsActivity.newIntent(context, n);
                    context.startActivity(intent);
                }
            });
        }

        public void bind(NewsItem newsItem) {
            n = newsItem;
        }
    }
}
