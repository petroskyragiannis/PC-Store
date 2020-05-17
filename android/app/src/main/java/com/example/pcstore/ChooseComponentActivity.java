package com.example.pcstore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class ChooseComponentActivity extends AppCompatActivity {

    TextView txtCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_component);

        txtCategory = findViewById(R.id.txt_category);

        Intent intent = getIntent();
        String category = intent.getStringExtra(ConfigurationActivity.COMPONENT_CATEGORY);
        txtCategory.setText(category);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

}
