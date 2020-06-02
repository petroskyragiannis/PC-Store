package com.example.pcstore.configuration;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pcstore.component.ComponentActivity;
import com.example.pcstore.login.LoginActivity;
import com.example.pcstore.R;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.PcConfiguration;

public class ConfigurationActivity extends AppCompatActivity
        implements ComponentTypeSelectionListener, ConfigurationView {

    public static final String COMPONENT_TYPE = "component type";
    public static final String PC_CONFIGURATION = "pc configuration";
    private static final int REQUEST_CODE_CHOOSE_COMPONENT = 1;

    Client client;
    PcConfiguration config;
    TextView txtComponentType;
    TextView txtPcConfiguration;
    Button btnConfirm;
    TextView txtPcConfigurationCost;
    RecyclerView recyclerView;
    private ConfigurationAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ConfigurationViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_configuration);
        txtPcConfiguration = findViewById(R.id.txt_pc_configuration);
        btnConfirm = findViewById(R.id.txt_confirm_configuration);
        txtPcConfigurationCost = findViewById(R.id.txt_pc_configuration_cost);
        recyclerView = findViewById(R.id.rv_pc_configuration);

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(LoginActivity.SIGNED_IN_CLIENT);
        config = new PcConfiguration();

        viewModel = new ViewModelProvider(this).get(ConfigurationViewModel.class);
        final ConfigurationPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);


        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new ConfigurationAdapter(presenter.getCategories());
        recyclerView.setAdapter(adapter);
        adapter.setComponentTypeSelectionListener(this);

        btnConfirm.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            public void onClick(View v) {
                if (presenter.checkPcConfiguration(config))
                    presenter.returnPcConfiguration(config);
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

    }
    @Override
    public void onComponentTypeSelected(String item, TextView textView) {
        // Extract the correct component type
        String[] split = item.split("\\s+");
        String type = split[split.length - 1].toUpperCase();

        Intent intent = new Intent(this, ComponentActivity.class);
        intent.putExtra(LoginActivity.SIGNED_IN_CLIENT, client);
        intent.putExtra(PC_CONFIGURATION, config);
        intent.putExtra(COMPONENT_TYPE, type);
        txtComponentType = textView;
        startActivityForResult(intent, REQUEST_CODE_CHOOSE_COMPONENT);
    }


    @Override
    public void returnPcConfiguration(PcConfiguration pcConfiguration) {
        Intent intent = new Intent();
        intent.putExtra(PC_CONFIGURATION, pcConfiguration);
        setResult(RESULT_OK, intent);
        finish();
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
                if (data != null) {
                    config = (PcConfiguration) data.getSerializableExtra(ComponentActivity.UPDATED_PC_CONFIGURATION);
                    txtComponentType.setText(data.getStringExtra(ComponentActivity.SELECTED_COMPONENT_NAME));
                    txtComponentType.setTypeface(null, Typeface.BOLD);
                    txtPcConfigurationCost.setText("Total Cost: " + config.getSubTotal() + " €.");
                }
    }

}