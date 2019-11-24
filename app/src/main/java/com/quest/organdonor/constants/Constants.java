package com.quest.organdonor.constants;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.quest.organdonor.MainActivity;

import java.util.regex.Pattern;

public class Constants {
    public static boolean checkEmail(final String email){
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\."+"[a-zA-Z0-9_+&*-]+)*@" +"(?:[a-zA-Z0-9-]+\\.)+[a-z" +"A-Z]{2,7}$";
        Pattern pat = Pattern.compile(emailRegex);
        if (email == null)
            return false;
        return pat.matcher(email).matches();
    }
    public static boolean checkPhone(final String phone){
        String passRegex = "^[0-9]{10,}+$";
        Pattern pat = Pattern.compile(passRegex);
        if (phone == null)
            return false;
        return pat.matcher(phone).matches();
    }
    public static boolean checkPassword(final String password) {
        String passRegex = "^[a-zA-Z0-9#?!@$%^&*+]{5,}+$";
        Pattern pat = Pattern.compile(passRegex);
        if (password == null)
            return false;
        return pat.matcher(password).matches();

    }
    //checks if internet connection available
    public static boolean checkInternet(Context context){
        ConnectivityManager cm=(ConnectivityManager)context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null &&  activeNetwork.isConnectedOrConnecting();
    }

    public static void logout(Context context){
        SharedPreferences preferences =context.getSharedPreferences("login", Context.MODE_PRIVATE);
        SharedPreferences.Editor sp = preferences.edit();
        sp.clear();
        sp.apply();

        Intent intent  = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        context.startActivity(intent);
        ((Activity) context).finish();

    }
}
