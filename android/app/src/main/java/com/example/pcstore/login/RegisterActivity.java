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

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    public static final String REGISTERED_CLIENT_USERNAME = "username";
    public static final String REGISTERED_CLIENT_PASSWORD = "password";

    TextView txtCreateAccount;
    EditText edtUsername;
    EditText edtPassword;
    Button btnRegister;
    TextView txtLogin;
    Button btnLogin;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        txtCreateAccount = findViewById(R.id.txt_create_account);
        edtUsername = findViewById(R.id.edt_username_2);
        edtPassword = findViewById(R.id.edt_password_2);
        btnRegister = findViewById(R.id.btn_register_2);
        txtLogin = findViewById(R.id.txt_login_2);
        btnLogin = findViewById(R.id.btn_login_2);

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        final RegisterPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                if (presenter.register(username, password) != null) {
                    presenter.returnCredentials(username,password);
                }
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }
        });
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    public void returnCredentials(String username, String password) {
        Intent intent = new Intent();
        intent.putExtra(REGISTERED_CLIENT_USERNAME, username);
        intent.putExtra(REGISTERED_CLIENT_PASSWORD, password);
        setResult(RESULT_OK, intent);
        finish();
    }

    @Override
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}

