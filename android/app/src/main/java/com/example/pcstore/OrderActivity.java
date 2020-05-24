package com.example.pcstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import com.example.pcstore.model.Address;
import com.example.pcstore.model.CardInfo;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Delivery;
import com.example.pcstore.model.Payment;

public class OrderActivity extends AppCompatActivity
        implements ItemSelectionListener<Object>, OrderView {

    public static final int REQUEST_CODE_CARD_INFO = 1;
    public static final int REQUEST_CODE_ADDRESS = 2;

    Client client;
    TextView txtCreateOrder;
    TextView txtPayment;
    TextView txtDelivery;
    Spinner spinPayment;
    Spinner spinDelivery;
    Button btnCardInfo;
    Button btnAddress;
    Button btnCompleteOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        txtCreateOrder = findViewById(R.id.txt_create_order);
        txtPayment = findViewById(R.id.txt_payment);
        txtDelivery = findViewById(R.id.txt_delivery);
        spinPayment = findViewById(R.id.spin_payment);
        spinDelivery = findViewById(R.id.spin_delivery);
        btnCardInfo = findViewById(R.id.btn_card_info);
        btnAddress = findViewById(R.id.btn_address);
        btnCompleteOrder = findViewById(R.id.btn_complete_order);

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(MainActivity.SIGNED_IN_CLIENT);

        // Setting Up Spinner <Payment>
        ArrayAdapter<Payment> pAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Payment.values());
        pAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinPayment.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        // Setting Up Spinner <Delivery>
        ArrayAdapter<Delivery> dAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, Delivery.values());
        dAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDelivery.setOnItemSelectedListener((AdapterView.OnItemSelectedListener) this);

        btnCardInfo.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertCardInfo();
            }
        });

        btnAddress.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertAddress();
            }
        });

        btnCompleteOrder.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //TODO
            }
        });
    }


    @Override
    protected void onPause() {
        super.onPause();
    }

    public void insertCardInfo() {
        Intent intent = new Intent(this, CardInfoActivity.class);
        intent.putExtra(MainActivity.SIGNED_IN_CLIENT, client);
        startActivityForResult(intent, REQUEST_CODE_CARD_INFO);
    }

    public void insertAddress() {
        Intent intent = new Intent(this, AddressActivity.class);
        intent.putExtra(MainActivity.SIGNED_IN_CLIENT, client);
        startActivityForResult(intent, REQUEST_CODE_ADDRESS);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_CARD_INFO) {
            if (resultCode == RESULT_OK) {
                CardInfo cardInfo = (CardInfo) data.getSerializableExtra(CardInfoActivity.CARD);
                client.setCard(cardInfo);
            }
        } else if (requestCode == REQUEST_CODE_ADDRESS) {
            if (resultCode == RESULT_OK) {
                Address address = (Address) data.getSerializableExtra(AddressActivity.ADDRESS);
                client.setAddress(address);
            }
        }
    }


    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public void onItemSelected(Object item) {
        if (item instanceof CardInfo) {
            CardInfo cardInfo = (CardInfo) item;
            //presenter.onItemSelected(cardInfo);
        } else if (item instanceof Address) {
            Address address = (Address) item;
            //presenter.onItemSelected(address);
        }
    }
}