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
import com.example.pcstore.model.Product;

import java.util.List;


public class ChooseComponentActivity extends AppCompatActivity
        implements ItemSelectionListener<Product>, CatalogView {

    public static final String UPDATED_PC_CONFIGURATION = "updated config";
    //public static final String SELECTED_COMPONENT_NAME = "selected component name";

    Client client;
    PcConfiguration config;
    String componentType;

    TextView txtSelectType;
    RecyclerView recyclerView;
    private ProductAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private ProductViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_component);
        txtSelectType = findViewById(R.id.txt_select_type);

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(MainActivity.SIGNED_IN_CLIENT);
        config = (PcConfiguration) intent.getSerializableExtra((ConfigurationActivity.PC_CONFIGURATION));
        componentType = intent.getStringExtra(ConfigurationActivity.COMPONENT_TYPE);
        txtSelectType.setText("Select a " + componentType);

        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        final ProductPresenter productPresenter = viewModel.getPresenter();
        productPresenter.setView(this);

        recyclerView = findViewById(R.id.choose_component_rv);
        recyclerView.setHasFixedSize(true);
        // User a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        // Specify an adapter
        List<Product> components = productPresenter.getComponents(componentType);
        mAdapter = new ProductAdapter(components);
        recyclerView.setAdapter(mAdapter);
        // Register current activity as listener for product selection events
        mAdapter.setItemSelectionListener(this);

    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(Product item) {
        Component component = (Component) item;
        Intent intent = new Intent();
        viewModel.getPresenter().onComponentSelected(config, component, componentType);
        intent.putExtra(UPDATED_PC_CONFIGURATION, config);
        //intent.putExtra(SELECTED_COMPONENT_NAME, component.getName());
        setResult(RESULT_OK, intent);
        finish();
    }

}