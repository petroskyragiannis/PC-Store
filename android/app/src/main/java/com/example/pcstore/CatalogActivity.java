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
import com.example.pcstore.model.OrderLine;
import com.example.pcstore.model.PcConfiguration;
import com.example.pcstore.model.Product;
import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class CatalogActivity extends AppCompatActivity
        implements CatalogSelectionListener<Product>, CatalogView {

    public static final String CLIENT_CART = "client cart";
    public static final String CLIENT_WISHLIST = "client wishlist";
    public static final String SINGED_OUT_CLIENT = "singed out client";
    public static final int REQUEST_CODE_PC_CONFIGURATION = 1;
    public static final int REQUEST_CODE_WISHLIST = 2;
    public static final int REQUEST_CODE_CART = 3;

    Client client;
    TextView txtConfiguration;
    ImageButton btnConfiguration;
    Button btnViewCart;
    Button btnViewWishlist;
    RecyclerView recyclerView;
    private CatalogAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private CatalogViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);

        txtConfiguration = findViewById(R.id.txt_configuration);
        btnConfiguration = findViewById(R.id.btn_configuration);
        btnViewCart = findViewById(R.id.btn_view_cart);
        btnViewWishlist = findViewById(R.id.btn_view_wishlist);

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
        mAdapter.setCatalogSelectionListener(this);

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

        btnViewWishlist.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                previewWishlist();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onBackPressed() {
        //TODO use presenter
        Intent intent = new Intent();
        intent.putExtra(SINGED_OUT_CLIENT, client);
        setResult(RESULT_OK, intent);
        super.onBackPressed();
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
        startActivityForResult(intent, REQUEST_CODE_CART);
    }

    public void previewWishlist() {
        Intent intent = new Intent(this, WishlistActivity.class);
        intent.putExtra(MainActivity.SIGNED_IN_CLIENT, client);
        startActivityForResult(intent, REQUEST_CODE_WISHLIST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PC_CONFIGURATION) {
            if (resultCode == RESULT_OK) {
                PcConfiguration config = (PcConfiguration) data.getSerializableExtra(ConfigurationActivity.PC_CONFIGURATION);
                viewModel.getPresenter().onPcConfigurationSelected(client, config);
            }
        } else if (requestCode == REQUEST_CODE_WISHLIST) {
            if (resultCode == RESULT_OK) {
                Set<Product> wishlist = (Set<Product>) data.getSerializableExtra(WishlistActivity.UPDATED_WISHLIST);
                viewModel.getPresenter().onWishlistSelected(client, wishlist);
            }
        } else if (requestCode == REQUEST_CODE_CART) {
            if (resultCode == RESULT_OK) {
                Set<OrderLine> cart = (Set<OrderLine>) data.getSerializableExtra(CartActivity.UPDATED_CART);
                viewModel.getPresenter().onCartSelected(client, cart);
            }
        }
    }

}
