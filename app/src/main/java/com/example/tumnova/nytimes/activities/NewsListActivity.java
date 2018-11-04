package com.example.tumnova.nytimes.activities;

import android.content.Intent;
import android.content.res.Configuration;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import com.example.tumnova.nytimes.DataUtils;
import com.example.tumnova.nytimes.NTAdapter;
import com.example.tumnova.nytimes.NTItemDecorator;
import com.example.tumnova.nytimes.R;

public class NewsListActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private int noOfColumn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_news_list);

        init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_option, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        if(item.getItemId() == R.id.about) {
            startActivity(new Intent(this, AboutActivity.class));
        }

        return true;
    }

    private void init() {
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(new NTAdapter(this, DataUtils.generateNews()));
        recyclerView.setLayoutManager(getLayoutManager());

        NTItemDecorator itemDecorator = new NTItemDecorator(this, R.dimen.margins_4_dp, noOfColumn);
        recyclerView.addItemDecoration(itemDecorator);
    }

    private RecyclerView.LayoutManager getLayoutManager() {
        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            DisplayMetrics metrics = this.getResources().getDisplayMetrics();
            float dpWidth = metrics.widthPixels / metrics.density;
            noOfColumn = (int) dpWidth / 250;
            return new GridLayoutManager(this, noOfColumn);
        } else {
            return new LinearLayoutManager(this);
        }
    }
}
