package com.example.pcstore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class ConfigurationActivity extends AppCompatActivity implements ItemSelectionListener<String>{

    public static final String COMPONENT_CATEGORY = "category";

    TextView txtPcConfig;
    RecyclerView recyclerView;
    private ConfigurationAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);

        txtPcConfig = findViewById(R.id.txt_pc_config);
        recyclerView = findViewById(R.id.configuration_rv);

        String[] categories = new String[] {"Choose Case","Choose CPU","Choose Motherboard","Choose RAM","Choose GPU","Choose Hard Drive","Choose PSU","Choose Mouse","Choose Keyboard","Choose Monitor"};

        recyclerView.setHasFixedSize(true);
        // User a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        // Specify an adapter
        mAdapter = new ConfigurationAdapter(categories);
        recyclerView.setAdapter(mAdapter);
        // Register current activity as listener for product selection events
        mAdapter.setItemSelectionListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onItemSelected(String item) {
        Intent intent = new Intent(this, ChooseComponentActivity.class);
        intent.putExtra(COMPONENT_CATEGORY, item);
        startActivity(intent);
    }
}
