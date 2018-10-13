package com.example.tumnova.nytimes.activities;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import com.example.tumnova.nytimes.R;

public class AboutActivity extends AppCompatActivity {

    private EditText sendMessageEditText;
    private TextView sendTextView;
    private ImageView instagram;
    private ImageView telegram;
    private String message;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        init();
        sendTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmailOnClick();
            }
        });
        instagram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToInstagramm();
            }
        });
        telegram.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToTelegram();
            }
        });
    }

    private void init() {
        setTitle(R.string.my_name);
        sendMessageEditText = findViewById(R.id.message);
        sendTextView = findViewById(R.id.send_message_text_view);
        instagram = findViewById(R.id.instagramm_icon);
        telegram = findViewById(R.id.telegram_icon);
        //add disclaimer
        createDisclaimer();
    }

    private void sendEmailOnClick() {
        message = sendMessageEditText.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:"));
        intent.putExtra(Intent.EXTRA_EMAIL, new String[]{getString(R.string.email_address)});
        intent.putExtra(Intent.EXTRA_SUBJECT, getString(R.string.email_theme));
        intent.putExtra(Intent.EXTRA_TEXT, message);
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        } else {
            Toast.makeText(this, R.string.no_email_apps, Toast.LENGTH_SHORT).show();
        }
    }

    private void goToInstagramm() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        String instagramLink = "https://www.instagram.com";
        intent.setData(Uri.parse(instagramLink));
        startActivity(intent);
    }

    private void goToTelegram() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.setPackage("com.telegram");
        if(intent.resolveActivity(getPackageManager()) != null) {
            startActivity(Intent.createChooser(intent, "Share with"));
        } else {
            Toast.makeText(this, R.string.telegram_no_installed, Toast.LENGTH_SHORT).show();
        }
    }

    private void createDisclaimer() {
        LinearLayout linearLayout = findViewById(R.id.linear_layout);
        TextView disclaimer = new TextView(this);
        disclaimer.setText(R.string.disclaimer);
        disclaimer.setGravity(Gravity.CENTER_HORIZONTAL);
        linearLayout.addView(disclaimer);
    }
}
