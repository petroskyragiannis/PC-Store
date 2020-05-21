package com.example.pcstore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
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
    private ComponentAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ComponentViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_component);
        txtSelectType = findViewById(R.id.txt_select_type);

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(MainActivity.SIGNED_IN_CLIENT);
        config = (PcConfiguration) intent.getSerializableExtra((ConfigurationActivity.PC_CONFIGURATION));
        componentType = intent.getStringExtra(ConfigurationActivity.COMPONENT_TYPE);
        txtSelectType.setText("Select a " + componentType);

        viewModel = new ViewModelProvider(this).get(ComponentViewModel.class);
        final ComponentPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        recyclerView = findViewById(R.id.choose_component_rv);
        recyclerView.setHasFixedSize(true);
        // User a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        // Specify an adapter
        List<Component> components = presenter.getComponents(componentType);
        mAdapter = new ComponentAdapter(components);
        recyclerView.setAdapter(mAdapter);
        // Register current activity as listener for product selection events
        mAdapter.setItemSelectionListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
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