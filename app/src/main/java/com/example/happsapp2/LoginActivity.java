package com.example.happsapp2;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.happsapp2.models.User;
import com.example.happsapp2.sqlite.UserDatabaseHelper;

public class LoginActivity extends AppCompatActivity {
    private UserDatabaseHelper databaseHelper;
    private User currentUser;

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

        databaseHelper = new UserDatabaseHelper(getApplicationContext());

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLoginCheck(editTextUserName.getText().toString(),
                        editTextPassword.getText().toString());
            }
        });

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openRegisterActivity();
            }
        });

    }

    public void openLoginCheck(String uName, String pw) {
        Toast.makeText(this, pw, Toast.LENGTH_SHORT).show();
        if (databaseHelper.validateUserCredentials(uName, pw)) {
            Intent intent = new Intent(this, HomeScreenActivity.class);

            //create and attach a user so you can display field values throughout session
            currentUser = databaseHelper.getUser(uName);
            //Toast.makeText(this, "" + currentUser.getUserPassword(), Toast.LENGTH_SHORT).show();
            intent.putExtra("current_userName", currentUser.getUserName());
            intent.putExtra("current_last_name", currentUser.getLName());
            intent.putExtra("current_first_name", currentUser.getFName());
            intent.putExtra("current_passWord", currentUser.getUserPassword());

            startActivity(intent);
            finish();
        } else {
            Toast.makeText(this, "Incorrect Name/Password", Toast.LENGTH_SHORT).show();
        }
    }

    public void openRegisterActivity() {
        Intent intent = new Intent(this, RegisterUserActivity.class);
        startActivity(intent);
    }

}
