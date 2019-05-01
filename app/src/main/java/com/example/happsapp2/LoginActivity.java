package com.example.happsapp2;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.happsapp2.models.User;
import com.example.happsapp2.view_models.UserViewModel;

import java.util.List;

public class LoginActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private Button loginButton;
    private Button registerButton;
    private EditText editTextUserName;
    private EditText editTextPassword;
    public static final String TAG = "LOGIN_ACTIVITY";
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_screen_layout);

        //Assign Buttons to XML buttons
        loginButton = findViewById(R.id.btn_login);
        registerButton = findViewById(R.id.btn_register);

        //Assign EditTexts to XML edit texts
        editTextUserName = findViewById(R.id.edit_text_user_name);
        editTextPassword = findViewById(R.id.edit_text_password);

        //assign ViewModel + set observable
        userViewModel = ViewModelProviders.of(this).get(UserViewModel.class);
        userViewModel.getAllUsers().observe(this, new Observer<List<User>>() {
            @Override
            public void onChanged(@Nullable List<User> users) {
                Log.d(TAG, "onChanged: LOGIN");
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openLoginCheck(editTextUserName.getText().toString(),
                        editTextPassword.getText().toString());
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });

    }

    public void openLoginCheck(String uName, String pw) {

        Intent intent = new Intent(this, HomeScreenActivity.class);
        startActivity(intent);
    }

    public void openRegisterActivity() {

    }

}
