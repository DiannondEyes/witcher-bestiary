package com.mw.bestiarii;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("DiscouragedApi")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ListView list = findViewById(R.id.listOfCategories);
        boolean isSecondary = getIntent() != null && getIntent().hasExtra("secondaryList");
        String[] categories = isSecondary ? getIntent().getStringArrayExtra("secondaryList") : getResources().getStringArray(R.array.categories);
        if (isSecondary) setTitle(getResources().getStringArray(R.array.categories)[getIntent().getIntExtra("secondaryListId", 0)]+" - Бестиарий Ведьмака");

        list.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, categories));
        list.setOnItemClickListener((parent, v, position, id) -> {
            if (isSecondary) startActivity(new Intent(MainActivity.this, InfoActivity.class).putExtra("categoryId", getIntent().getIntExtra("secondaryListId", 0)).putExtra("entityId", position));
            else startActivity(new Intent(MainActivity.this, MainActivity.class).putExtra("secondaryList", getResources().getStringArray(getResources().getIdentifier("elements"+position, "array", getPackageName()))).putExtra("secondaryListId", position));
        });
    }
}