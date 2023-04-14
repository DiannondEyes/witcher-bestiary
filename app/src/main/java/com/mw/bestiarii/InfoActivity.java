package com.mw.bestiarii;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getIntent() == null || !getIntent().hasExtra("categoryId") || !getIntent().hasExtra("entityId")) finishAffinity();
        Integer category = getIntent().getIntExtra("categoryId", 0);
        Integer entity = getIntent().getIntExtra("entityId", 0);
        String[] cat = getResources().getStringArray(getResources().getIdentifier("elements"+category, "array", getPackageName()));
        setTitle(cat[entity]);
        setContentView(R.layout.activity_info);
    }
}