package com.example.covid_19eg.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.webkit.WebView;

import com.example.covid_19eg.R;

public class InteractiveMapActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_interactive_map);

        WebView wv = findViewById(R.id.root_web_view);
        wv.getSettings().setJavaScriptEnabled(true);
        wv.loadUrl("https://bing.com/covid");

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
