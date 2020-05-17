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

public class ConfigurationActivity extends AppCompatActivity implements ItemSelectionListener<String>, CatalogView{

    public static final String COMPONENT_TYPE = "type";
    public static final String PC_CONFIGURATION = "config";
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
        recyclerView = findViewById(R.id.configuration_rv);
        btnConfirm = findViewById(R.id.txt_confirm_configuration);

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(MainActivity.SIGNED_IN_CLIENT);
        String[] categories = new String[] {
                "Choose your Case",
                "Choose your CPU",
                "Choose your Motherboard",
                "Choose your RAM",
                "Choose your GPU",
                "Choose your Hard Drive",
                "Choose your PSU",
                "Choose your Mouse",
                "Choose your Keyboard",
                "Choose your Monitor"
        };
        config = new PcConfiguration();

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
    public void onItemSelected(String item) {
        Intent intent = new Intent(this, ChooseComponentActivity.class);
        intent.putExtra(MainActivity.SIGNED_IN_CLIENT, client);
        intent.putExtra(COMPONENT_TYPE, item);
        intent.putExtra(PC_CONFIGURATION, config);
        startActivityForResult(intent, REQUEST_CODE_CHOOSE_COMPONENT);
    }

    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CHOOSE_COMPONENT) {
            if (resultCode == RESULT_OK)
                config = (PcConfiguration) data.getSerializableExtra(ChooseComponentActivity.UPDATED_PC_CONFIGURATION);
        }
    }

}
