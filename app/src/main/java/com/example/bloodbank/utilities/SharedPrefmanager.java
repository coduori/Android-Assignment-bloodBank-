package com.example.bloodbank.utilities;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import com.example.bloodbank.authenticate.LoginActivity;
import com.example.bloodbank.utilities.User;

public class SharedPrefmanager {

    //the constants
    private static final String SHARED_PREF_NAME = "ilabiotfarmerssystem";
    private static final String KEY_FIRSTNAME = "keyusername";
    private static final String KEY_OTHERNAMES = "keyusername";
    private static final String KEY_USERNAME = "keyusername";
    private static final String KEY_PHONE = "0700000000";
    private static final String KEY_EMAIL = "keyemail";
    private static final String KEY_COUNTY = "keygender";
    private static final String KEY_ID = "keyid";
    private static final String KEY_AUTH_TOKEN = "token";
 
    private static SharedPrefmanager mInstance;
    private static Context mCtx;
 
    private SharedPrefmanager(Context context) {
        mCtx = context;
    }
 
    public static synchronized SharedPrefmanager getInstance(Context context) {
        if (mInstance == null) {
            mInstance = new SharedPrefmanager(context);
        }
        return mInstance;
    }
 
    // Method to let the user login
    // This method will store the user data in shared preferences
    public void userLogin(User user) {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(KEY_ID, user.get_id());
        editor.putString(KEY_FIRSTNAME, user.getFirst_name());
        editor.putString(KEY_OTHERNAMES, user.getOther_names());
        editor.putString(KEY_USERNAME, user.getUsername());
        editor.putString(KEY_PHONE, user.getPhone_number());
        editor.putString(KEY_EMAIL, user.getEmail());
        editor.putString(KEY_COUNTY, user.getCounty());
        editor.putString(KEY_AUTH_TOKEN, user.getCounty());
        editor.apply();
    }
 
    //this method will checker whether user is already logged in or not
    public boolean isLoggedIn() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return sharedPreferences.getString(KEY_USERNAME, null) != null;
    }
 
    //this method will give the logged in user
    public User getUser() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        return new User(
                sharedPreferences.getString(KEY_ID, null),
                sharedPreferences.getString(KEY_FIRSTNAME, null),
                sharedPreferences.getString(KEY_OTHERNAMES, null),
                sharedPreferences.getString(KEY_USERNAME, null),
                sharedPreferences.getString(KEY_PHONE, null),
                sharedPreferences.getString(KEY_EMAIL, null),
                sharedPreferences.getString(KEY_COUNTY, null),
                sharedPreferences.getString(KEY_AUTH_TOKEN, null)
        );
    }
 
    // This method will logout the user
    public void logout() {
        SharedPreferences sharedPreferences = mCtx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.clear();
        editor.apply();
        mCtx.startActivity(new Intent(mCtx, LoginActivity.class));
    }
}
