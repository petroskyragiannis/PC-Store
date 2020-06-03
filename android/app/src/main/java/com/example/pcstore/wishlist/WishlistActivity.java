package com.example.pcstore.wishlist;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pcstore.component.ItemSelectionListener;
import com.example.pcstore.login.LoginActivity;
import com.example.pcstore.R;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Product;
import java.io.Serializable;
import java.util.List;

public class WishlistActivity extends AppCompatActivity
        implements ItemSelectionListener<Product>, WishlistView {

    public static final String UPDATED_WISHLIST = "updated wishlist";

    Client client;
    TextView txtWishlist;
    RecyclerView recyclerView;
    private WishlistAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private WishlistViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);
        txtWishlist = findViewById(R.id.txt_wishlist);
        recyclerView = findViewById(R.id.rv_wishlist);

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(LoginActivity.SIGNED_IN_CLIENT);

        viewModel = new ViewModelProvider(this).get(WishlistViewModel.class);
        final WishlistPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        List<Product> wishlist = presenter.getList(client.getWishlist());

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new WishlistAdapter(wishlist);
        recyclerView.setAdapter(adapter);
        adapter.setItemSelectionListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onItemSelected(Product item) {
        viewModel.getPresenter().onItemSelected(client, item);
        List<Product> wishlist = viewModel.getPresenter().getList(client.getWishlist());
        adapter.setDataset(wishlist);
        adapter.notifyDataSetChanged();
        viewModel.getPresenter().returnWishlist(client);
    }

    @Override
    public void returnWishlist(Client client) {
        Intent intent = new Intent();
        intent.putExtra(UPDATED_WISHLIST, (Serializable) client.getWishlist());
        setResult(RESULT_OK, intent);
    }

    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}