package com.example.pcstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.OrderLine;
import java.io.Serializable;
import java.util.List;

public class CartActivity extends AppCompatActivity
        implements  CartItemSelectionListener<OrderLine>, CartView {

    private static final int REQUEST_CODE_ORDER = 1;
    public static final String UPDATED_CART = "updated cart";

    Client client;
    TextView txtCart;
    RecyclerView recyclerView;
    Button btnCreateOrder;
    private CartAdapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    private CartViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        txtCart = findViewById(R.id.txt_cart);
        recyclerView = findViewById(R.id.rv_cart);
        btnCreateOrder = findViewById((R.id.btn_create_order));

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(MainActivity.SIGNED_IN_CLIENT);

        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        final CartPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        presenter.setupList(client.getCart());
        List<OrderLine> cartList = presenter.getList();

        recyclerView.setHasFixedSize(true);
        // User a linear layout manager
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        // Specify an adapter
        adapter = new CartAdapter(cartList);
        recyclerView.setAdapter(adapter);
        // Register current activity as listener for product selection events
        adapter.setCartItemSelectionListener(this);

        btnCreateOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                createOrder();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onCartItemSelected(OrderLine orderLine) {
        viewModel.getPresenter().onItemSelected(client, orderLine);
        adapter.notifyDataSetChanged();
        viewModel.getPresenter().returnCart();
    }

    @Override
    public void onCartItemSelected(OrderLine orderLine, int quantity) {
        viewModel.getPresenter().onItemSelected(orderLine, quantity);
        viewModel.getPresenter().returnCart();
    }

    @Override
    public void returnCart() {
        Intent intent = new Intent();
        intent.putExtra(UPDATED_CART, (Serializable) client.getCart());
        setResult(RESULT_OK, intent);
    }

    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void createOrder() {
        Intent intent = new Intent(this, OrderActivity.class);
        intent.putExtra(MainActivity.SIGNED_IN_CLIENT, client);
        startActivityForResult(intent, REQUEST_CODE_ORDER);
    }

}