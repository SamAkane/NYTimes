package com.example.tumnova.nytimes.activities;

import android.content.Context;
import android.content.Intent;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.appcompat.widget.Toolbar;
import android.text.format.DateUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.tumnova.nytimes.R;
import com.example.tumnova.nytimes.model.NewsItem;

public class NewsDetailsActivity extends AppCompatActivity {
    private final static String NEWS_CATEGORY_KEY = "category";
    private final static String NEWS_TITLE_KEY = "title";
    private final static String NEWS_IMAGE_URL_KEY = "imageUrl";
    private final static String NEWS_PUBLISH_DATE_KEY = "publishDate";
    private final static String NEWS_FULL_TEXT_KEY = "fullText";
    private ImageView detailsImage;
    private TextView detailsPublicationDate;
    private TextView detailsTitle;
    private TextView detailsFullDescription;
    private Toolbar ntToolbar;
    private String category;
    private String title;
    private String imageUrl;
    private String publishDate;
    private String fullText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_details);
        init();
    }

    public static Intent newIntent(Context context, NewsItem news) {
        Intent intent = new Intent(context, NewsDetailsActivity.class);
        intent.putExtra(NEWS_CATEGORY_KEY, news.getCategory().getName());
        intent.putExtra(NEWS_TITLE_KEY, news.getTitle());
        intent.putExtra(NEWS_FULL_TEXT_KEY, news.getFullText());
        intent.putExtra(NEWS_IMAGE_URL_KEY, news.getImageUrl());
        String d = (String) DateUtils.getRelativeTimeSpanString(
                news.getPublishDate().getTime(), System.currentTimeMillis(),
                DateUtils.MINUTE_IN_MILLIS, DateUtils.FORMAT_ABBREV_RELATIVE);
        intent.putExtra(NEWS_PUBLISH_DATE_KEY, d);

        return intent;
    }

    private void init() {
        detailsImage = findViewById(R.id.details_image);
        detailsTitle = findViewById(R.id.details_title);
        detailsPublicationDate = findViewById(R.id.details_publication_date);
        detailsFullDescription = findViewById(R.id.details_full_text);
        ntToolbar = findViewById(R.id.toolbar);
        getExtras();
        setter();
    }

    private void getExtras() {
        category = getIntent().getStringExtra(NEWS_CATEGORY_KEY);
        title = getIntent().getStringExtra(NEWS_TITLE_KEY);
        fullText = getIntent().getStringExtra(NEWS_FULL_TEXT_KEY);
        imageUrl = getIntent().getStringExtra(NEWS_IMAGE_URL_KEY);
        publishDate = getIntent().getStringExtra(NEWS_PUBLISH_DATE_KEY);
    }

    private void setter() {
        setSupportActionBar(ntToolbar);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(getIntent().getStringExtra(NEWS_CATEGORY_KEY));
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setDisplayShowHomeEnabled(true);
        }
        Glide.with(this).load(imageUrl).into(detailsImage);
        detailsPublicationDate.setText(publishDate);
        detailsFullDescription.setText(fullText);
        detailsTitle.setText(title);
    }
}
