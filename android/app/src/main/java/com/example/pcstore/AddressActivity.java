package com.example.pcstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.pcstore.model.Address;
import com.example.pcstore.model.Client;

public class AddressActivity extends AppCompatActivity implements CatalogView {

    public static final String ADDRESS = "address";
    Client client;
    Address address;
    TextView txtAddress;
    TextView txtStreet;
    EditText edtStreet;
    TextView txtStreetNumber;
    EditText edtStreetNumber;
    TextView txtTown;
    EditText edtTown;
    TextView txtZipCode;
    EditText edtZipCode;
    Button btnAddressOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        txtAddress = findViewById(R.id.txt_address);
        txtStreet = findViewById(R.id.txt_street);
        edtStreet = findViewById(R.id.edt_street);
        txtStreetNumber = findViewById(R.id.edt_street_number);
        edtStreetNumber = findViewById(R.id.edt_street_number);
        txtTown = findViewById(R.id.txt_town);
        edtTown = findViewById(R.id.edt_town);
        txtZipCode = findViewById(R.id.txt_zipcode);
        edtZipCode = findViewById(R.id.edt_zipcode);
        btnAddressOk = findViewById(R.id.btn_address_ok);

        Intent intent = getIntent();
        client = (Client) intent.getSerializableExtra(MainActivity.SIGNED_IN_CLIENT);
        if (client.getAddress() == null) address = new Address();
        else {
            address = client.getAddress();
            txtStreet.setText(client.getAddress().getStreet());
            txtStreetNumber.setText(client.getAddress().getNumber());
            txtTown.setText(client.getAddress().getTown());
            txtZipCode.setText(client.getAddress().getZipCode());
        }

        edtStreet.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
            String street = edtStreet.getText().toString();
            if (street != null)
                address.setStreet(street);
            }
        });

        edtStreetNumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String number = edtStreetNumber.getText().toString();
                if (number != null)
                    address.setNumber(number);
            }
        });

        edtTown.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String town = edtTown.getText().toString();
                if (town != null)
                    address.setTown(town);
            }
        });

        edtZipCode.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String zipcode = edtZipCode.getText().toString();
                if (zipcode != null)
                    address.setZipCode(zipcode);
            }
        });

        btnAddressOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (address.getStreet() == null || address.getNumber() == null
                        || address.getTown() == null || address.getZipCode() == null)
                    showStatus("Missing required information.");
                else {
                    Intent intent = new Intent();
                    intent.putExtra(ADDRESS, address);
                    setResult(RESULT_OK, intent);
                    finish();
                }

            }
        });
    }

    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}