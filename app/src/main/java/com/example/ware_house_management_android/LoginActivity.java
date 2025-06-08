package com.example.ware_house_management_android;

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.ware_house_management_android.contracts.LoginContract;
import com.example.ware_house_management_android.dtos.LoginRequestDto;
import com.example.ware_house_management_android.presenters.LoginPresenter;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, View.OnClickListener {

    private LoginPresenter loginPresenter;
    private EditText emailEditText;
    private EditText passwordEditText;
    private Button loginButton;
    private TextView signUpTextView;
    private ProgressBar loadingProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginPresenter = new LoginPresenter(this,this);

        emailEditText = findViewById(R.id.login_et_email);
        passwordEditText = findViewById(R.id.login_et_password);
        loginButton = findViewById(R.id.login_btn_login);
        signUpTextView = findViewById(R.id.login_tv_signup);
        loadingProgressBar = findViewById(R.id.login_progress_bar);

        loginButton.setOnClickListener(this);
        signUpTextView.setOnClickListener(this);
    }

    @Override
    public void showLoginSuccess(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoginError(String error) {
        Toast.makeText(this, error, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {
        loadingProgressBar.setVisibility(View.VISIBLE);
        emailEditText.setEnabled(false);
        passwordEditText.setEnabled(false);
        loginButton.setEnabled(false);
        signUpTextView.setEnabled(false);
    }

    @Override
    public void hideLoading() {
        runOnUiThread(() -> {
            loadingProgressBar.setVisibility(View.GONE);
            emailEditText.setEnabled(true);
            passwordEditText.setEnabled(true);
            loginButton.setEnabled(true);
            signUpTextView.setEnabled(true);
        });

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();

        if (id == R.id.login_btn_login) {
            String email = emailEditText.getText().toString().trim();
            String password = passwordEditText.getText().toString().trim();
            LoginRequestDto loginRequestDto = new LoginRequestDto(email, password);

            loginPresenter.login(loginRequestDto);
        }
    }
}
