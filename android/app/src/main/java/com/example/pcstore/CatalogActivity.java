package com.example.pcstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.PcConfiguration;
import com.example.pcstore.model.Product;
import java.io.Serializable;
import java.util.List;

public class CatalogActivity extends AppCompatActivity
        implements ProductSelectionListener<Product>, CatalogView {

    public static final String CLIENT_CART = "client cart";
    public static final int REQUEST_CODE_PC_CONFIGURATION = 1;

    Client client;

    TextView txtConfiguration;
    ImageButton btnConfiguration;
    Button btnViewCart;
    RecyclerView recyclerView;
    private ProductAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private ProductViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        txtConfiguration = findViewById(R.id.txt_configuration);
        btnConfiguration = findViewById(R.id.btn_configuration);
        btnViewCart = findViewById(R.id.btn_view_cart);

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(MainActivity.SIGNED_IN_CLIENT);

        viewModel = new ViewModelProvider(this).get(ProductViewModel.class);
        final ProductPresenter productPresenter = viewModel.getPresenter();
        productPresenter.setView(this);
        List<Product> catalog = productPresenter.getCatalog();

        recyclerView = findViewById(R.id.catalog_rv);
        recyclerView.setHasFixedSize(true);
        // User a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        // Specify an adapter
        mAdapter = new ProductAdapter(catalog);
        recyclerView.setAdapter(mAdapter);
        // Register current activity as listener for product selection events
        mAdapter.setProductSelectionListener(this);

        btnConfiguration.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createCustomConfiguration();
            }
        });

        btnViewCart.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                previewCart();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onProductSelectedCart(Product product) {
        viewModel.getPresenter().onProductSelectedCart(client, product);
    }

    @Override
    public void onProductSelectedWishlist(Product product) {
        viewModel.getPresenter().onProductSelectedWishlist(client, product);
    }

    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void createCustomConfiguration() {
        Intent intent = new Intent(this, ConfigurationActivity.class);
        intent.putExtra(MainActivity.SIGNED_IN_CLIENT, client);
        startActivityForResult(intent, REQUEST_CODE_PC_CONFIGURATION);
    }

    public void previewCart() {
        Intent intent = new Intent(this, CartActivity.class);
        intent.putExtra(MainActivity.SIGNED_IN_CLIENT, client);
        intent.putExtra(CLIENT_CART, (Serializable) client.getCart());
        startActivity(intent);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PC_CONFIGURATION) {
            if (resultCode == RESULT_OK) {
                PcConfiguration config = (PcConfiguration) data.getSerializableExtra(ConfigurationActivity.PC_CONFIGURATION);
                viewModel.getPresenter().onPcConfigurationSelected(client, config);
            }
        }
    }

}
