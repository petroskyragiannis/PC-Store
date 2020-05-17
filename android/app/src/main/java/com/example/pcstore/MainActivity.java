package com.example.pcstore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.pcstore.dao.Initializer;
import com.example.pcstore.memorydao.MemoryInitializer;
import com.example.pcstore.model.Client;
import com.example.pcstore.model.Employee;
import com.example.pcstore.model.User;


public class MainActivity extends AppCompatActivity implements RegisterView {

    public static final String SIGNED_IN_CLIENT = "client";

    EditText edtUsername;
    EditText edtPassword;
    Button btnLogin;
    TextView tvRegister;
    Button btnRegister;
    TextView tvGuest;
    Button btnGuest;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Uncomment to use the activity standalone
        Initializer initializer = new MemoryInitializer();
        initializer.prepareData();

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        final RegisterPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        edtUsername = findViewById(R.id.username_edt2);
        edtPassword = findViewById(R.id.password_edt2);
        btnLogin = findViewById(R.id.login_btn);
        tvRegister = findViewById(R.id.register_tv);
        btnRegister = findViewById(R.id.register_btn2);
        tvGuest = findViewById(R.id.guest_tv);
        btnGuest = findViewById(R.id.guest_btn);

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                User user = presenter.login(username, password);
                if (user instanceof Client)  showCatalog((Client) user);
                else if (user instanceof Employee) {
                    //TODO showCatalog for Employee
                }
            }
        });

        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startRegisterActivity();
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
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    public void startRegisterActivity() {
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void showCatalog(Client client) {
        Intent intent = new Intent(this, CatalogActivity.class);
        intent.putExtra(SIGNED_IN_CLIENT, client);
        startActivity(intent);
    }

}
