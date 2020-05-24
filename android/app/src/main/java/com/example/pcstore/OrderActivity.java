package com.example.pcstore;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;


public class OrderActivity extends AppCompatActivity {

    TextView txtCreateOrder;
    TextView txtPayment;
    TextView txtDelivery;
    Spinner spinnerPayment;
    Spinner spinnerDelivery;
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
        spinnerPayment = findViewById(R.id.sp_payment);
        spinnerDelivery = findViewById(R.id.sp_delivery);
        btnCardInfo = findViewById(R.id.btn_card_info);
        btnAddress = findViewById(R.id.btn_address);
        btnCompleteOrder = findViewById(R.id.btn_complete_order);
    }

    @Override
    protected void onPause() {
        super.onPause();
    }
    
}
