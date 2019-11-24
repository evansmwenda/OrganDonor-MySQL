package com.quest.organdonor;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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
import com.quest.organdonor.constants.Api;
import com.quest.organdonor.constants.Constants;
import com.quest.organdonor.authentication.RegisterActivity;

import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {
    private EditText mEmailField;
    private EditText mPasswordField;
    private  String user_email,password,user_id="";
    private SharedPreferences sharedPreferences;
    private SharedPreferences.Editor editor;
    private static final String TAG = "mwenda";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //check shared preferences for token
        sharedPreferences=getSharedPreferences("login",MODE_PRIVATE);
        user_id=sharedPreferences.getString("user_id","0");
        Log.d(TAG, "onCreate: user_id->"+user_id);


        //if user id  not empty -> user had not logged out
        if(!TextUtils.isEmpty(user_id)){
            //redirect to Home screen
            Intent intent = new Intent(this,HomeActivity.class);
            startActivity(intent);
        }


        //get the fields
        mEmailField=findViewById(R.id.editEmail);
        mPasswordField=findViewById(R.id.editPass);
    }

    public void forgotPassView(View view) {
        //redirect to forgot password view
        Toast.makeText(this, "forgot password screen", Toast.LENGTH_SHORT).show();
    }

    public void goToRegister(View view) {
        //redirect to register screen
        Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    private boolean validateForm() {
        boolean valid = true;

        user_email = mEmailField.getText().toString();
        if (TextUtils.isEmpty(user_email)) {
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
        //data valid->check Internet connection
        if(Constants.checkInternet(MainActivity.this)){
            //has internet
            signIn(user_email,password);
        }else{
            //no internet
            Toast.makeText(this, "Please check your internet connection and try again", Toast.LENGTH_SHORT).show();
        }

    }

    private void signIn(String email, String password) {
        Log.d(TAG, "signIn: clicked signin");
        //fetch data here
        String URL = Api.BASE_URL;//CARFIX_LOGIN;
        AndroidNetworking.post(URL)
                .addBodyParameter("email_address", email)
                .addBodyParameter("password", password)
                .setPriority(Priority.MEDIUM)
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject response) {
                        // do anything with response
                        Log.d(TAG, "onResponse: "+response.toString());
                        boolean status = response.optBoolean("success");
                        if(status){
                            String reply = response.optString("reply","Login successful");
                            JSONObject jsonObjectMessage = response.optJSONObject("message");
                            user_id = jsonObjectMessage.optString("id","");
                            user_email = jsonObjectMessage.optString("email_address","");

                            editor=sharedPreferences.edit();
                            editor.putString("user_id",user_id);
                            editor.putString("email_address",user_email);
                            editor.apply();

                            Toast.makeText(MainActivity.this, reply, Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(MainActivity.this,HomeActivity.class);
                            startActivity(intent);
                            finish();
                        }
                    }
                    @Override
                    public void onError(ANError error) {
                        // handle error
                        Log.d(TAG, "onError: "+error.getErrorBody());
                        Toast.makeText(MainActivity.this, "An error occurred, please try again", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
