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

public class RegisterActivity extends AppCompatActivity implements RegisterView {

    public static final String REGISTERED_CLIENT_USERNAME = "username";
    public static final String REGISTERED_CLIENT_PASSWORD = "password";

    EditText edtUsername;
    EditText edtPassword;
    Button btnRegister;
    TextView tvLogin;
    Button btnLogin;
    private RegisterViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        viewModel = new ViewModelProvider(this).get(RegisterViewModel.class);
        final RegisterPresenter presenter = viewModel.getPresenter();
        presenter.setView(this);

        edtUsername = findViewById(R.id.username_edt2);
        edtPassword = findViewById(R.id.password_edt2);
        btnRegister = findViewById(R.id.register_btn2);
        tvLogin = findViewById(R.id.login_tv);
        btnLogin = findViewById(R.id.login_btn2);

        btnRegister.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String username = edtUsername.getText().toString();
                String password = edtPassword.getText().toString();
                if (presenter.register(username, password) != null) {
                    Intent intent = new Intent();
                    intent.putExtra(REGISTERED_CLIENT_USERNAME, username);
                    intent.putExtra(REGISTERED_CLIENT_PASSWORD, password);
                    setResult(RESULT_OK, intent);
                    finish();
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
    public void showStatus(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

}

