package com.quest.organdonor;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    private EditText mEmailField;
    private EditText mPasswordField;
    private  String email,password="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //get the fields
        mEmailField=findViewById(R.id.editEmail);
        mPasswordField=findViewById(R.id.editPass);
    }

    public void forgotPassView(View view) {
        //redirect to forgot password view
    }

    public void goToRegister(View view) {
        //redirect to register screen
    }

    private boolean validateForm() {
        boolean valid = true;

        email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(email)) {
            mEmailField.setError("Required.");
            valid = false;
        } else {
            mEmailField.setError(null);
        }

        password = mPasswordField.getText().toString();
        if (TextUtils.isEmpty(password)) {
            mPasswordField.setError("Required.");
            valid = false;
        } else {
            mPasswordField.setError(null);
        }

        return valid;
    }

    public void userClick(View view) {
        //user clicked login button->validate form
        if(!validateForm()){
            return;
        }
        //data valid
        signIn(email,password);
    }

    private void signIn(String email, String password) {
        //fetch data here
        String URL = "";
    }

    public void goToLogin(View view) {
    }
}
