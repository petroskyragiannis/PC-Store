package com.example.pcstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Product;
import java.util.List;

public class CatalogActivity extends AppCompatActivity
        implements ItemSelectionListener<Product>, CatalogView {

    TextView txtConfiguration;
    ImageButton btnConfiguration;

    RecyclerView recyclerView;
    private CatalogAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private CatalogViewModel viewModel;

    Client client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        txtConfiguration = findViewById(R.id.txt_configuration);
        btnConfiguration = findViewById(R.id.btn_configuration);

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(MainActivity.SIGNED_IN_CLIENT);


        viewModel = new ViewModelProvider(this).get(CatalogViewModel.class);
        final CatalogPresenter catalogPresenter = viewModel.getPresenter();
        catalogPresenter.setView(this);
        List<Product> catalog = catalogPresenter.getCatalog();

        recyclerView = findViewById(R.id.catalog_rv);
        recyclerView.setHasFixedSize(true);
        // User a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        // Specify an adapter
        mAdapter = new CatalogAdapter(catalog);
        recyclerView.setAdapter(mAdapter);
        // Register current activity as listener for product selection events
        mAdapter.setItemSelectionListener(this);

        btnConfiguration.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createCustomConfiguration();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onItemSelected(Product item) {
        viewModel.getPresenter().onProductSelected(item);
    }

    @Override
    public void addToCart(Product product) {
        client.addToCart(product, 1);
    }

    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void createCustomConfiguration() {
        Intent intent = new Intent(this, ConfigurationActivity.class);
        startActivity(intent);
    }
}
