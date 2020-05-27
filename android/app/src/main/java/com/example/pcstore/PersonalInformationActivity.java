package com.example.pcstore;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.pcstore.model.Client;

public class PersonalInformationActivity extends AppCompatActivity implements CatalogView {

    public static final String PERSONAL_INFORMATION = "personal information";

    Client client;
    String name;
    String surname;
    String phoneNumber;
    String email;
    TextView txtPersonalInformation;
    TextView txtName;
    EditText edtName;
    TextView txtSurname;
    EditText edtSurname;
    TextView txtPhoneNumber;
    EditText edtPhoneNumber;
    TextView txtEmail;
    EditText edtEmail;
    Button edtPersonalInformationOk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_information);
        txtPersonalInformation = findViewById(R.id.txt_personal_information);
        txtName = findViewById(R.id.txt_name);
        edtName = findViewById(R.id.edt_name);
        txtSurname = findViewById(R.id.txt_surname);
        edtSurname = findViewById(R.id.edt_surname);
        txtPhoneNumber = findViewById(R.id.txt_phone_number);
        edtPhoneNumber = findViewById(R.id.edt_phone_number);
        txtEmail = findViewById(R.id.txt_email);
        edtEmail = findViewById(R.id.edt_email);
        edtPersonalInformationOk = findViewById(R.id.btn_personal_information_ok);

        edtName.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String temp = edtName.getText().toString();
                if (temp != null) name = temp;
            }
        });

        edtSurname.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String temp = edtSurname.getText().toString();
                if (temp != null) surname = temp;
            }
        });

        edtPhoneNumber.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String temp = edtPhoneNumber.getText().toString();
                if (temp != null) {
                    if (temp.length() == 10)
                        phoneNumber = temp;
                    else
                        showStatus("Wrong phone number format.");
                }
            }
        });

        edtEmail.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String temp = edtEmail.getText().toString();
                if (temp != null) {
                    if (temp.contains("@"))
                        email = temp;
                    else
                        showStatus("Wrong email format.");
                }
            }
        });

        edtPersonalInformationOk.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                if (name == null || surname == null || phoneNumber == null || email == null)
                    showStatus("Missing required information.");
                else {
                    client.setName(name);
                    client.setSurname(surname);
                    client.setPhoneNumber(phoneNumber);
                    client.setEmail(email);
                    Intent intent = new Intent();
                    intent.putExtra(PERSONAL_INFORMATION, client);
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
