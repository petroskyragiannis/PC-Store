package com.example.pcstore;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.OrderLine;
import java.util.Set;

import static com.example.pcstore.CatalogActivity.CLIENT_CART;

public class CartActivity extends AppCompatActivity
        implements ItemSelectionListener, CatalogView {

    Client client;
    Set<OrderLine> cart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(MainActivity.SIGNED_IN_CLIENT);
        cart = (Set<OrderLine>) intent.getSerializableExtra(CLIENT_CART);

        if (cart==null) showStatus("CART IS NULL");
        else if (cart.size()==0) showStatus("CART IS EMPTY");
        else showStatus("CART IS NOT EMPTY MPOREI KAI OXI");
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void onItemSelected(Object item) {

    }

    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }



}
