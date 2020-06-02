package com.example.pcstore.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pcstore.R;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Delivery;
import com.example.pcstore.model.Order;
import com.example.pcstore.model.Payment;


public class OrderCompletedActivity extends AppCompatActivity {

    Order order;
    Client client;
    TextView txtOrderCompleted;
    TextView txtOrderCreated;
    TextView txtOrderId;
    TextView txtOrderClient;
    TextView txtOrderPayment;
    TextView txtOrderDelivery;
    TextView txtOrderEmail;

    Button btnExit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_completed);
        txtOrderCompleted = findViewById(R.id.txt_order_completed);
        txtOrderCreated = findViewById(R.id.txt_order_created);
        txtOrderId = findViewById(R.id.txt_order_id);
        txtOrderClient = findViewById(R.id.txt_order_client);
        txtOrderPayment = findViewById(R.id.txt_order_payment);
        txtOrderDelivery = findViewById(R.id.txt_order_delivery);
        txtOrderEmail = findViewById(R.id.txt_order_email);
        btnExit = findViewById(R.id.btn_exit);

        Intent intent = getIntent();
        order = (Order) intent.getSerializableExtra(OrderActivity.ORDER_COMPLETED);
        client = order.getClient();

        txtOrderId.setText(txtOrderId.getText() + " " + order.getId());
        txtOrderClient.setText(txtOrderClient.getText() + " " + client.getName() + " " + client.getSurname());
        txtOrderEmail.setText(txtOrderEmail.getText() +  client.getEmail());

        if (order.getPaymentMethod().equals(Payment.CARD))
            txtOrderPayment.setText(txtOrderPayment.getText() + " " + order.getPaymentMethod().toString() + " " + client.getCard().getCardNumber());
        else txtOrderPayment.setText(txtOrderPayment.getText() + " " + order.getPaymentMethod().toString());

        if (order.getDeliveryMethod().equals(Delivery.ADDRESS))
            txtOrderDelivery.setText(txtOrderDelivery.getText() + " " + order.getDeliveryMethod().toString() + " - " + client.getAddress().getStreet() + " " + client.getAddress().getNumber());
        else txtOrderDelivery.setText(txtOrderDelivery.getText() + " " + order.getDeliveryMethod().toString());

        btnExit.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                    finish();
            }
        });

    }

}
