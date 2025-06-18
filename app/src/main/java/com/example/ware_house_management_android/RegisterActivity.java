package com.example.ware_house_management_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ware_house_management_android.contracts.auth.RegisterContract;
import com.example.ware_house_management_android.dtos.RegisterRequestDto;
import com.example.ware_house_management_android.presenters.RegisterPresenter;

public class RegisterActivity extends AppCompatActivity implements RegisterContract.View, View.OnClickListener {

    private RegisterPresenter registerPresenter;
    private EditText fullNameEditText;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button registerButton;
    private TextView loginLink;
    private ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        registerPresenter = new RegisterPresenter(this, this);

        // Initialize views with correct IDs
        fullNameEditText = findViewById(R.id.register_et_fullName);
        emailEditText = findViewById(R.id.register_et_email);
        passwordEditText = findViewById(R.id.register_et_password);
        registerButton = findViewById(R.id.register_btn_register);
        loginLink = findViewById(R.id.register_tv_login);
        loadingProgressBar = findViewById(R.id.register_progress_bar);

        // Set click listeners
        registerButton.setOnClickListener(this);
        loginLink.setOnClickListener(this);

        registerPresenter.checkAlreadyRegister();
    }

    @Override
    public void showRegisterSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
        // Điều hướng về MainActivity sau khi đăng ký thành công
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        finish(); // Đóng RegisterActivity
    }

    @Override
    public void showRegisterError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        loadingProgressBar.setVisibility(View.VISIBLE);
        fullNameEditText.setEnabled(false);
        emailEditText.setEnabled(false);
        passwordEditText.setEnabled(false);
        registerButton.setEnabled(false);
        loginLink.setEnabled(false);
    }

    @Override
    public void hideLoading() {
        runOnUiThread(() -> {
            loadingProgressBar.setVisibility(View.GONE);
            fullNameEditText.setEnabled(true);
            emailEditText.setEnabled(true);
            passwordEditText.setEnabled(true);
            registerButton.setEnabled(true);
            loginLink.setEnabled(true);
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.register_btn_register) {
            String fullName = fullNameEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            RegisterRequestDto registerRequestDto = new RegisterRequestDto(email, password, fullName);

            if (registerRequestDto.isValid()) {
                registerPresenter.register(registerRequestDto);
            } else {
                showRegisterError(registerRequestDto.getErrorMessage());
            }
        } else if (id == R.id.register_tv_login) {
            // Điều hướng về LoginActivity
            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
            finish(); // Đóng RegisterActivity
        }
    }
}