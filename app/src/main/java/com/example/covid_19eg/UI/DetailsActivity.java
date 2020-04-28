package com.example.covid_19eg.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import com.example.covid_19eg.R;
import com.example.covid_19eg.Services.SoundService;

public class DetailsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        startService(new Intent(DetailsActivity.this, SoundService.class));
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_details);
    }

    protected void onDestroy() {
        //stop service and stop music
        stopService(new Intent(DetailsActivity.this, SoundService.class));
        super.onDestroy();
    }
}
