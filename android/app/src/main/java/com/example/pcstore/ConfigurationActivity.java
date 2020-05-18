package com.example.pcstore;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcstore.model.Client;
import com.example.pcstore.model.PcConfiguration;

public class ConfigurationActivity extends AppCompatActivity implements ItemSelectionListener<String>, CatalogView {

    public static final String COMPONENT_TYPE = "component type";
    public static final String PC_CONFIGURATION = "pc configuration";
    private static final int REQUEST_CODE_CHOOSE_COMPONENT = 1;

    Client client;
    PcConfiguration config;
    TextView txtPcConfig;
    Button btnConfirm;
    RecyclerView recyclerView;
    private ConfigurationAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        txtPcConfig = findViewById(R.id.txt_pc_config);
        btnConfirm = findViewById(R.id.txt_confirm_configuration);
        recyclerView = findViewById(R.id.configuration_rv);

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(MainActivity.SIGNED_IN_CLIENT);
        config = new PcConfiguration();
        String[] categories = new String[]{
                "Select a Case",
                "Select a CPU",
                "Select a Motherboard",
                "Select a RAM",
                "Select a GPU",
                "Select a Hard Drive",
                "Select a PSU",
                "Select a Mouse",
                "Select a Keyboard",
                "Select a Monitor"
        };

        recyclerView.setHasFixedSize(true);
        // User a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        // Specify an adapter
        mAdapter = new ConfigurationAdapter(categories);
        recyclerView.setAdapter(mAdapter);
        // Register current activity as listener for product selection events
        mAdapter.setItemSelectionListener(this);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onClick(View v) {
                try {
                    if (config.checkRequirements())
                        if (config.checkCompatibility()) {
                            client.addToCart(config);
                            showStatus("Custom PC Configuration added to cart.");
                            finish();
                        }
                } catch (PcConfiguration.CompatibilityException e) {
                    showStatus(e.getMessage());
                }
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onItemSelected(String item) {
        // Extract the correct component type
        String[] split = item.split("\\s+");
        String type = split[split.length - 1].toUpperCase();
        if (type.equals("DRIVE")) type = "HARD_DRIVE";

        Intent intent = new Intent(this, ChooseComponentActivity.class);
        intent.putExtra(MainActivity.SIGNED_IN_CLIENT, client);
        intent.putExtra(PC_CONFIGURATION, config);
        intent.putExtra(COMPONENT_TYPE, type);
        startActivityForResult(intent, REQUEST_CODE_CHOOSE_COMPONENT);
    }

    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE_COMPONENT)
            if (resultCode == RESULT_OK)
                config = (PcConfiguration) data.getSerializableExtra(ChooseComponentActivity.UPDATED_PC_CONFIGURATION);
                //String componentName = data.getStringExtra(ChooseComponentActivity.SELECTED_COMPONENT_NAME);
    }

}