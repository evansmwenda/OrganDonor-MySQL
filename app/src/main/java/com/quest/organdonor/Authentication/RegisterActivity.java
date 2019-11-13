package com.quest.organdonor.Authentication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.common.Priority;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.quest.organdonor.App.API;
import com.quest.organdonor.App.Constants;
import com.quest.organdonor.MainActivity;
import com.quest.organdonor.R;

import org.json.JSONObject;

public class RegisterActivity extends AppCompatActivity {
    private EditText editUsername,editPass,editConfPass,editEmail;
    String email,password,confirmPassword,username="";
    private static final String TAG = "quest";
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
        //data valid->check Internet connection
        if(Constants.checkInternet(RegisterActivity.this)){
            //has internet
            signUp(username,email,password);
        }else{
            //no internet
            Toast.makeText(this, "Please check your internet connection and try again", Toast.LENGTH_SHORT).show();
        }


    }

    private void signUp(String username, String email, String password) {
        //api endpoint here
        String URL= API.CARFIX_REGISTER;

        AndroidNetworking.post(URL)
                .addBodyParameter("username", username)
                .addBodyParameter("email_address", email)
                .addBodyParameter("password", password)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d(TAG, "onResponse: "+response.toString());
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d(TAG, "onError: "+error.getErrorBody());
                    }
                });
    }
}
