package com.example.pcstore.order;

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
import androidx.lifecycle.ViewModelProvider;
import com.example.pcstore.catalog.CatalogActivity;
import com.example.pcstore.client.AddressActivity;
import com.example.pcstore.client.CardInfoActivity;
import com.example.pcstore.login.LoginActivity;
import com.example.pcstore.client.PersonalInformationActivity;
import com.example.pcstore.R;
import com.example.pcstore.model.Address;
import com.example.pcstore.model.CardInfo;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Delivery;
import com.example.pcstore.model.Order;
import com.example.pcstore.model.Payment;

public class OrderActivity extends AppCompatActivity implements OrderView {

    public static final int REQUEST_CODE_CARD_INFO = 1;
    public static final int REQUEST_CODE_ADDRESS = 2;
    public static final int REQUEST_CODE_PERSONAL_INFORMATION = 3;
    public static final String ORDER_COMPLETED = "order completed";

    Payment[] payments = Payment.values();
    Delivery[] deliveries = Delivery.values();

    Client client;
    Order order;
    TextView txtCreateOrder;
    TextView txtPayment;
    Spinner spinPayment;
    TextView txtCardInfo;
    Button btnCardInfo;
    TextView txtDelivery;
    Spinner spinDelivery;
    TextView txtAddress;
    Button btnAddress;
    TextView txtPersonalInformation;
    Button btnPersonalInformation;
    TextView txtCost;
    Button btnCompleteOrder;
    private OrderViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        txtCreateOrder = findViewById(R.id.txt_create_order);
        txtPayment = findViewById(R.id.txt_payment);
        spinPayment = findViewById(R.id.spin_payment);
        txtCardInfo = findViewById(R.id.txt_card_info);
        btnCardInfo = findViewById(R.id.btn_card_info);
        txtDelivery = findViewById(R.id.txt_delivery);
        spinDelivery = findViewById(R.id.spin_delivery);
        txtAddress = findViewById(R.id.txt_address);
        btnAddress = findViewById(R.id.btn_address);
        txtPersonalInformation = findViewById(R.id.txt_personal_information);
        btnPersonalInformation = findViewById(R.id.btn_personal_information);
        txtCost = findViewById(R.id.txt_cost);
        btnCompleteOrder = findViewById(R.id.btn_complete_order);

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(LoginActivity.SIGNED_IN_CLIENT);
        if (client.getName() == null || client.getSurname() == null || client.getEmail() == null || client.getPhoneNumber() == null) {
           txtPersonalInformation.setVisibility(View.VISIBLE);
           btnPersonalInformation.setVisibility(View.VISIBLE);
        }
        else {
            txtPersonalInformation.setVisibility(View.INVISIBLE);
            btnPersonalInformation.setVisibility(View.INVISIBLE);
        }

        viewModel = new ViewModelProvider(this).get(OrderViewModel.class);
        final OrderPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);
        order = presenter.createOrder(client);
        txtCost.setText("Total Cost: " + order.getTotal() + " €");

        // Setting Up Spinner <Payment>
        ArrayAdapter<Payment> pAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, payments);
        pAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinPayment.setAdapter(pAdapter);
        spinPayment.setOnItemSelectedListener(new PaymentItemSelectionListener());

        // Setting Up Spinner <Delivery>
        ArrayAdapter<Delivery> dAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, deliveries);
        dAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinDelivery.setAdapter(dAdapter);
        spinDelivery.setOnItemSelectedListener(new DeliveryItemSelectionListener());

        btnPersonalInformation.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                insertPersonalInformation();
            }
        });

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
                if (presenter.completeOrder(order)) {
                    CatalogActivity.getInstance().finish();
                    orderCompleted();
                }
            }
        });

    }

    public void insertPersonalInformation() {
        Intent intent = new Intent(this, PersonalInformationActivity.class);
        intent.putExtra(LoginActivity.SIGNED_IN_CLIENT, client);
        startActivityForResult(intent, REQUEST_CODE_PERSONAL_INFORMATION);
    }

    public void insertCardInfo() {
        Intent intent = new Intent(this, CardInfoActivity.class);
        intent.putExtra(LoginActivity.SIGNED_IN_CLIENT, client);
        startActivityForResult(intent, REQUEST_CODE_CARD_INFO);
    }

    public void insertAddress() {
        Intent intent = new Intent(this, AddressActivity.class);
        intent.putExtra(LoginActivity.SIGNED_IN_CLIENT, client);
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
        } else if (requestCode == REQUEST_CODE_PERSONAL_INFORMATION) {
            if (resultCode == RESULT_OK) {
                client = (Client) data.getSerializableExtra(PersonalInformationActivity.PERSONAL_INFORMATION);
                order.setClient(client);
            }
        }
    }

    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void orderCompleted() {
        Intent intent = new Intent(this, OrderCompletedActivity.class);
        intent.putExtra(ORDER_COMPLETED, order);
        startActivity(intent);
        finish();
    }

    private class PaymentItemSelectionListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Payment payment = payments[position];
            viewModel.getPresenter().onItemSelected(order, payment);
            if (payment.equals(Payment.CARD) && client.getCard()==null) {
                txtCardInfo.setVisibility(View.VISIBLE);
                btnCardInfo.setVisibility(View.VISIBLE);
            }
            else {
                txtCardInfo.setVisibility(View.INVISIBLE);
                btnCardInfo.setVisibility(View.INVISIBLE);
            }

        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }

    private class DeliveryItemSelectionListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
            Delivery delivery = deliveries[position];
            viewModel.getPresenter().onItemSelected(order, delivery);
            if (delivery.equals(Delivery.ADDRESS) && client.getAddress()==null) {
                txtAddress.setVisibility(View.VISIBLE);
                btnAddress.setVisibility(View.VISIBLE);
            }
            else {
                txtAddress.setVisibility(View.INVISIBLE);
                btnAddress.setVisibility(View.INVISIBLE);
            }
        }
        @Override
        public void onNothingSelected(AdapterView<?> parent) {}
    }

}