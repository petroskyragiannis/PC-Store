package com.example.pcstore.login;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.pcstore.R;
import com.example.pcstore.catalog.CatalogActivity;
import com.example.pcstore.catalog_update.CatalogUpdateActivity;
import com.example.pcstore.dao.Initializer;
import com.example.pcstore.memorydao.MemoryInitializer;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Employee;
import com.example.pcstore.model.User;
import static com.example.pcstore.login.RegisterActivity.REGISTERED_CLIENT_PASSWORD;
import static com.example.pcstore.login.RegisterActivity.REGISTERED_CLIENT_USERNAME;

public class LoginActivity extends AppCompatActivity implements StatusView {

    public static final String SIGNED_IN_CLIENT = "client";
    public static final String SIGNED_IN_EMPLOYEE = "employee";
    private static final int REQUEST_CODE_REGISTERED_CLIENT = 1;
    private static final int REQUEST_CODE_SIGNED_OUT_CLIENT = 2;
    private static final int REQUEST_CODE_SIGNED_OUT_EMPLOYEE = 3;

    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;
    TextView txtRegister;
    Button btnRegister;
    TextView txtGuest;
    Button btnGuest;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        edtUsername = findViewById(R.id.edt_username_login);
        edtPassword = findViewById(R.id.edt_password_login);
        btnLogin = findViewById(R.id.btn_login);
        txtRegister = findViewById(R.id.txt_register);
        btnRegister = findViewById(R.id.btn_register);
        txtGuest = findViewById(R.id.txt_guest);
        btnGuest = findViewById(R.id.btn_guest);

        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        final RegisterPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                User user = presenter.login(username, password);
                if (user instanceof Client) showCatalog((Client) user);
                else if (user instanceof Employee) showCatalog((Employee) user);
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                registerClient();
            }
        });

        btnGuest.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Client guest = presenter.loginAsGuest();
                showCatalog(guest);
            }
        });

    }

    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void registerClient() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivityForResult(intent, REQUEST_CODE_REGISTERED_CLIENT);

    }

    public void showCatalog(Client client) {
        Intent intent = new Intent(this, CatalogActivity.class);
        intent.putExtra(SIGNED_IN_CLIENT, client);
        startActivityForResult(intent, REQUEST_CODE_SIGNED_OUT_CLIENT);
    }

    public void showCatalog(Employee employee) {
        Intent intent = new Intent(this, CatalogUpdateActivity.class);
        intent.putExtra(SIGNED_IN_EMPLOYEE, employee);
        startActivityForResult(intent, REQUEST_CODE_SIGNED_OUT_EMPLOYEE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CODE_REGISTERED_CLIENT) {
            if (resultCode == RESULT_OK) {
                edtUsername.setText(data.getStringExtra(REGISTERED_CLIENT_USERNAME));
                edtPassword.setText(data.getStringExtra(REGISTERED_CLIENT_PASSWORD));
            }
        } else if (requestCode == REQUEST_CODE_SIGNED_OUT_CLIENT) {
            if (resultCode == RESULT_OK) {
                Client singedOutClient = (Client) data.getSerializableExtra(CatalogActivity.SINGED_OUT_CLIENT);
                viewModel.getPresenter().logout(singedOutClient);
            }
        } else if (requestCode == REQUEST_CODE_SIGNED_OUT_EMPLOYEE) {
            if (resultCode == RESULT_OK) {
                Employee signedOutEmployee = (Employee) data.getSerializableExtra(CatalogUpdateActivity.SIGNED_OUT_EMPLOYEE);
                //TODO return updated products?
                viewModel.getPresenter().logout(signedOutEmployee);
            }
        }
    }

}