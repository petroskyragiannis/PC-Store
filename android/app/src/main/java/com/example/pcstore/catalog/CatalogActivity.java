package com.example.pcstore.catalog;

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

import com.example.pcstore.cart.CartActivity;
import com.example.pcstore.configuration.ConfigurationActivity;
import com.example.pcstore.login.LoginActivity;
import com.example.pcstore.R;
import com.example.pcstore.wishlist.WishlistActivity;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.OrderLine;
import com.example.pcstore.model.PcConfiguration;
import com.example.pcstore.model.Product;
import java.util.List;
import java.util.Set;

public class CatalogActivity extends AppCompatActivity
        implements CatalogSelectionListener<Product>, CatalogView {

    public static final String SINGED_OUT_CLIENT = "singed out client";
    public static final int REQUEST_CODE_PC_CONFIGURATION = 1;
    public static final int REQUEST_CODE_WISHLIST = 2;
    public static final int REQUEST_CODE_CART = 3;
    public static CatalogActivity catalogActivity;

    Client client;
    TextView txtConfiguration;
    ImageButton btnConfiguration;
    Button btnViewCart;
    Button btnViewWishlist;
    RecyclerView recyclerView;
    private CatalogAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private CatalogViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_catalog);
        txtConfiguration = findViewById(R.id.txt_configuration);
        btnConfiguration = findViewById(R.id.btn_configuration);
        btnViewCart = findViewById(R.id.btn_view_cart);
        btnViewWishlist = findViewById(R.id.btn_view_wishlist);
        recyclerView = findViewById(R.id.rv_catalog);

        catalogActivity = this;
        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(LoginActivity.SIGNED_IN_CLIENT);

        viewModel = new ViewModelProvider(this).get(CatalogViewModel.class);
        final CatalogPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);
        List<Product> catalog = presenter.getCatalog();

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CatalogAdapter(catalog);
        recyclerView.setAdapter(adapter);
        adapter.setCatalogSelectionListener(this);

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

    public static CatalogActivity getInstance() {
        return catalogActivity;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        viewModel.getPresenter().signOutClient(client);
    }

    @Override
    public void onItemSelectedCart(Product product) {
        if (viewModel.getPresenter().onItemSelectedCart(client, product)) {
            adapter.setDataset(viewModel.getPresenter().getCatalog());
            adapter.notifyDataSetChanged();
        }

    }

    @Override
    public void onItemSelectedWishlist(Product product) {
        viewModel.getPresenter().onItemSelectedWishlist(client, product);

    }

    @Override
    public void returnClient(Client client) {
        Intent intent = new Intent();
        intent.putExtra(SINGED_OUT_CLIENT, client);
        setResult(RESULT_OK, intent);
    }

    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void createCustomConfiguration() {
        Intent intent = new Intent(this, ConfigurationActivity.class);
        intent.putExtra(LoginActivity.SIGNED_IN_CLIENT, client);
        startActivityForResult(intent, REQUEST_CODE_PC_CONFIGURATION);
    }

    public void previewCart() {
        if (client.getCart().isEmpty()) showStatus("Cart is empty.");
        else {
            Intent intent = new Intent(this, CartActivity.class);
            intent.putExtra(LoginActivity.SIGNED_IN_CLIENT, client);
            startActivityForResult(intent, REQUEST_CODE_CART);
        }
    }

    public void previewWishlist() {
        if (client.getWishlist().isEmpty()) showStatus("Wishlist is empty.");
        else {
            Intent intent = new Intent(this, WishlistActivity.class);
            intent.putExtra(LoginActivity.SIGNED_IN_CLIENT, client);
            startActivityForResult(intent, REQUEST_CODE_WISHLIST);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_PC_CONFIGURATION) {
            if (resultCode == RESULT_OK) {
                PcConfiguration config = (PcConfiguration) data.getSerializableExtra(ConfigurationActivity.PC_CONFIGURATION);
                if (viewModel.getPresenter().onPcConfigurationSelected(client, config)) {
                    adapter.setDataset(viewModel.getPresenter().getCatalog());
                    adapter.notifyDataSetChanged();
                }
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
