package com.mw.bestiarii;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InfoActivity extends AppCompatActivity {
    @Override
    @SuppressLint("DiscouragedApi")
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int category = getIntent().getIntExtra("categoryId", 0);
        int entity = getIntent().getIntExtra("entityId", 0);
        setTitle(getResources().getStringArray(getResources().getIdentifier("elements"+category, "array", getPackageName()))[entity] +" — Бестиарий Ведьмака");
        setContentView(R.layout.activity_info);
        StringBuilder data = new StringBuilder();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(getAssets().open(category+"_"+entity+".txt")))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) data.append(line).append("\n");
        } catch (IOException e) {((TextView)findViewById(R.id.descripton)).setText("Не удалось прочитать информацию из файла");}
        ((TextView)findViewById(R.id.descripton)).setText(data.toString());
        ((ImageView)findViewById(R.id.imageView)).setImageResource(getResources().getIdentifier("r"+category+"_"+entity, "drawable", getPackageName()));
    }
}