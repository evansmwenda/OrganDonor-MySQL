package com.quest.organdonor.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

import com.quest.organdonor.MainActivity;
import com.quest.organdonor.R;

public class RegisterActivity extends AppCompatActivity {
    private EditText editUsername,editPass,editConfPass,editEmail;
    String email,password,confirmPassword,username="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //get the fields
        editEmail=findViewById(R.id.editEmail);
        editUsername=findViewById(R.id.editUsername);
        editPass=findViewById(R.id.editPass);
        editConfPass=findViewById(R.id.editConfPass);
    }

    public void goToLogin(View view) {
        //go to login screen
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private boolean validateForm() {
        boolean valid = true;

        email = editEmail.getText().toString();
        if (TextUtils.isEmpty(email)) {
            editEmail.setError("Required.");
            valid = false;
        } else {
            editEmail.setError(null);
        }


        username = editUsername.getText().toString();
        if (TextUtils.isEmpty(username)) {
            editUsername.setError("Required.");
            valid = false;
        } else {
            editUsername.setError(null);
        }

        password = editPass.getText().toString();
        if (TextUtils.isEmpty(password)) {
            editPass.setError("Required.");
            valid = false;
        } else {
            editPass.setError(null);
        }

        confirmPassword = editConfPass.getText().toString();
        if (TextUtils.isEmpty(confirmPassword)) {
            editConfPass.setError("Required.");
            valid = false;
        }else if(!TextUtils.equals(password,confirmPassword)){
            editConfPass.setError("Password mismatch.");
            valid=false;
        } else{
            editConfPass.setError(null);
        }

        return valid;
    }

    public void userRegister(View view) {
        //user clicked register button->validate form
        if(!validateForm()){
            return;
        }
        //data valid
        signUp(username,email,password);

    }

    private void signUp(String username, String email, String password) {
        //api endpoint here
    }
}
