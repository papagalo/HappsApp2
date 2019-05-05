package com.example.happsapp2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.example.happsapp2.models.User;
import com.example.happsapp2.sqlite.UserDatabaseHelper;
import com.example.happsapp2.view_models.UserViewModel;

public class RegisterUserActivity extends AppCompatActivity {
    private UserViewModel userViewModel;
    private UserViewModel userViewModel2;
    private EditText editTextUserName;
    private EditText editTextFirstName;
    private EditText editTextLastName;
    private EditText editTextNewPassword;
    private EditText editTextConfirmPassword;
    public static final String TAG = "REGISTER USER ACTIVITY";
    private UserDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_user);

        editTextUserName  = findViewById(R.id.edit_text_new_user_name);
        editTextFirstName = findViewById(R.id.edit_text_first_name);
        editTextLastName = findViewById(R.id.edit_text_last_name);
        editTextNewPassword = findViewById(R.id.edit_text_new_password);
        editTextConfirmPassword = findViewById(R.id.edit_text_confirm_password);

        databaseHelper = new UserDatabaseHelper(getApplicationContext());

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close_black_24dp);

        setTitle("New User Registration");
    }

    private void saveUser() {
        String userName = editTextUserName.getText().toString();
        String firstName = editTextFirstName.getText().toString();
        String lastName = editTextLastName.getText().toString();
        String newPassword = editTextNewPassword.getText().toString();
        String confirmPassword = editTextConfirmPassword.getText().toString();

        if (userName.trim().isEmpty() || firstName.trim().isEmpty()
            || lastName.trim().isEmpty() || newPassword.trim().isEmpty()
            || confirmPassword.trim().isEmpty()) {
            Toast.makeText(this, "Please fill out all fields", Toast.LENGTH_SHORT).show();
            return;
        }

        if(newPassword.compareTo(confirmPassword) != 0) {
            Toast.makeText(this, "Passwords do not match", Toast.LENGTH_SHORT).show();
            return;
        }

        if(!databaseHelper.checkUserNameAlreadyExists(userName)) {
            Toast.makeText(this, "Successfully Registered", Toast.LENGTH_SHORT).show();
            User user = new User(userName, firstName, lastName, newPassword);
            databaseHelper.addUser(user);
        }
        else {
            Toast.makeText(this, "UserName already taken", Toast.LENGTH_SHORT).show();
            return;
        }

        finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_board_game_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_board_game_menu:
                saveUser();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

}
