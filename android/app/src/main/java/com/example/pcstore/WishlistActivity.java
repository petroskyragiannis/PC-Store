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
import com.example.pcstore.model.Product;

import java.io.Serializable;
import java.util.List;

public class WishlistActivity extends AppCompatActivity
        implements ItemSelectionListener<Product>, WishlistView {

    public static final String UPDATED_WISHLIST = "updated wishlist";

    Client client;
    List<Product> wishlist;
    TextView txtWishlist;
    RecyclerView recyclerView;
    private WishlistAdapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private WishlistViewModel viewModel;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wishlist);

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(MainActivity.SIGNED_IN_CLIENT);

        txtWishlist = findViewById(R.id.txt_wishlist);

        viewModel = new ViewModelProvider(this).get(WishlistViewModel.class);
        final WishlistPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        presenter.setList(client.getWishlist());
        wishlist = presenter.getList();

        recyclerView = findViewById(R.id.rv_wishlist);
        recyclerView.setHasFixedSize(true);
        // User a linear layout manager
        mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        // Specify an adapter
        mAdapter = new WishlistAdapter(wishlist);
        recyclerView.setAdapter(mAdapter);
        // Register current activity as listener for product selection events
        mAdapter.setItemSelectionListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onItemSelected(Product item) {
        viewModel.getPresenter().onItemSelected(client, item);
        //wishlist.remove(item);
        mAdapter.notifyDataSetChanged();
        viewModel.getPresenter().returnWishlist();
    }

    @Override
    public void returnWishlist() {
        Intent intent = new Intent();
        intent.putExtra(UPDATED_WISHLIST, (Serializable) client.getWishlist());
        setResult(RESULT_OK, intent);
    }

    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}