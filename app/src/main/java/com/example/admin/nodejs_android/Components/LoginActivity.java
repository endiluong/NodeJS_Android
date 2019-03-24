package com.example.admin.nodejs_android.Components;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.admin.nodejs_android.R;
import com.example.admin.nodejs_android.Services.LoginService;
import com.example.admin.nodejs_android.Services.SignUpService;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    LoginService loginService;
    SignUpService signUpService;
    EditText etUsername, etPassword;
    Button btnLogin, btnSignUp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //DECLARATION
        etUsername = findViewById(R.id.et_Username);
        etPassword = findViewById(R.id.et_Password);
        btnLogin = findViewById(R.id.btnLogin);
        btnSignUp = findViewById(R.id.btnSignUp);
        //CREATE INSTANCE FOR  SERVICE
        loginService = new LoginService(this);
        signUpService = new SignUpService(this);

        //
        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
    }

    public void logInClicked(String username, String password) {
        Intent intent = new Intent(this, OrderActivity.class);
        if (this.loginService.Login(username, password)) {
            if (username.equals("admin")) {

            } else {

                //user => is admin=false
                intent.putExtra("userName",username);
            }
            startActivity(intent);
        } else {
            Toast.makeText(this, "Wrong UserName or Password", Toast.LENGTH_SHORT).show();
        }
    }

    public void signUpClick(String username, String password) {
        if (this.signUpService.SignUp(username, password)) {
            Toast.makeText(this, "User Created with Username: " + username, Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Some thing went wrong, User resignation failed, try again !", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnLogin:
                logInClicked(etUsername.getText().toString(), etPassword.getText().toString());
                break;
            case R.id.btnSignUp:
                signUpClick(etUsername.getText().toString(), etPassword.getText().toString());
                break;
        }
    }
}
