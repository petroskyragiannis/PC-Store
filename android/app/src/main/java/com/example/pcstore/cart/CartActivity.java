package com.example.pcstore.cart;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pcstore.login.LoginActivity;
import com.example.pcstore.order.OrderActivity;
import com.example.pcstore.R;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.OrderLine;
import java.io.Serializable;
import java.util.List;

public class CartActivity extends AppCompatActivity
        implements  CartItemSelectionListener<OrderLine>, CartView {

    private static final int REQUEST_CODE_ORDER = 1;
    public static final String UPDATED_CART = "updated cart";
    public static CartActivity cartActivity;

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
        btnCreateOrder = findViewById((R.id.btn_create_order));
        recyclerView = findViewById(R.id.rv_cart);

        cartActivity = this;
        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(LoginActivity.SIGNED_IN_CLIENT);

        viewModel = new ViewModelProvider(this).get(CartViewModel.class);
        final CartPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        final List<OrderLine> cartList = presenter.getCartList(client.getCart());

        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        adapter = new CartAdapter(cartList);
        recyclerView.setAdapter(adapter);
        adapter.setCartItemSelectionListener(this);

        btnCreateOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (client.getCart().isEmpty()) showStatus("Cart is empty.");
                else {
                    createOrder();
                    finish();
                }
            }
        });
    }

    public static CartActivity getInstance() {
        return cartActivity;
    }

    @Override
    public void onItemSelected(OrderLine orderLine) {
        viewModel.getPresenter().onItemSelected(client, orderLine);
        // Update Adapter's dataset
        adapter.setDataset(viewModel.getPresenter().getCartList(client.getCart()));
        adapter.notifyDataSetChanged();
        // Return Cart
        viewModel.getPresenter().returnCart(client);
    }

    @Override
    public void onItemSelected(OrderLine orderLine, int quantity) {
        if (viewModel.getPresenter().onItemSelected(orderLine, quantity)) {
            // Update Adapter's dataset
            adapter.setDataset(viewModel.getPresenter().getCartList(client.getCart()));
            adapter.notifyDataSetChanged();
            // Return Cart
            viewModel.getPresenter().returnCart(client);
        }
    }

    @Override
    public void returnCart(Client client) {
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
        intent.putExtra(LoginActivity.SIGNED_IN_CLIENT, client);
        startActivityForResult(intent, REQUEST_CODE_ORDER);
    }

}