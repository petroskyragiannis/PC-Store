package com.example.pcstore.component;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pcstore.login.LoginActivity;
import com.example.pcstore.R;
import com.example.pcstore.configuration.ConfigurationActivity;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Component;
import com.example.pcstore.model.PcConfiguration;
import java.util.List;

public class ComponentActivity extends AppCompatActivity
        implements ItemSelectionListener<Component>, ComponentView {

    public static final String UPDATED_PC_CONFIGURATION = "updated config";
    public static final String SELECTED_COMPONENT_NAME = "selected component name";

    Client client;
    PcConfiguration config;
    String componentType;
    TextView txtSelectType;
    RecyclerView recyclerView;
    private ComponentAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private ComponentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);
        txtSelectType = findViewById(R.id.txt_select_component_type);
        recyclerView = findViewById(R.id.choose_component_rv);

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(LoginActivity.SIGNED_IN_CLIENT);
        config = (PcConfiguration) intent.getSerializableExtra((ConfigurationActivity.PC_CONFIGURATION));
        componentType = intent.getStringExtra(ConfigurationActivity.COMPONENT_TYPE);
        if (componentType.equals("DRIVE")) {
            txtSelectType.setText("Select a HARD DRIVE");
            componentType = "HARD_DRIVE";
        }
        else txtSelectType.setText("Select a " + componentType);

        viewModel = new ViewModelProvider(this).get(ComponentViewModel.class);
        final ComponentPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        List<Component> components = presenter.getComponents(componentType);
        adapter = new ComponentAdapter(components);
        recyclerView.setAdapter(adapter);
        adapter.setItemSelectionListener(this);

    }

    @Override
    public void returnPcConfiguration(PcConfiguration pcConfiguration, Component component) {
        Intent intent = new Intent();
        intent.putExtra(UPDATED_PC_CONFIGURATION, config);
        intent.putExtra(SELECTED_COMPONENT_NAME, component.getName());
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(Component item) {
        viewModel.getPresenter().onComponentSelected(config, item, componentType);
        viewModel.getPresenter().returnPcConfiguration(config, item);
    }

}