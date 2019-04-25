package com.example.bloodbank.authenticate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.Loader;
import android.database.Cursor;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.example.bloodbank.authenticate.ProfileActivity;
import com.example.bloodbank.R;
import com.example.bloodbank.utilities.SharedPrefmanager;
import com.example.bloodbank.utilities.URLs;
import com.example.bloodbank.utilities.User;
import com.example.bloodbank.utilities.VolleySingleton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * A login screen that offers login via email/password.
 */
public class RegisterActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]{
            "foo@example.com:hello", "bar@example.com:world"
    };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    // private UserRegisterTask mAuthTask = null;

    // UI references.
    private EditText mFirstNameView;
    private EditText mOtherNamesView;
    private EditText mUsernameView;
    private EditText mEmailView;
    private EditText mPhoneView;
    private EditText mCountyView;
    private EditText mPasswordView;
    private EditText mConfirmPasswordView;
    private View mProgressView;
    private View mRegisterFormView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mFirstNameView = (EditText) findViewById(R.id.input_first_name);
        mOtherNamesView = (EditText) findViewById(R.id.input_other_names);
        mUsernameView = (EditText) findViewById(R.id.input_username);
        mEmailView = (EditText) findViewById(R.id.input_email);
        mPhoneView = (EditText) findViewById(R.id.input_phone);
        mCountyView = (EditText) findViewById(R.id.input_county);
        mPasswordView = (EditText) findViewById(R.id.input_password);
        mConfirmPasswordView = (EditText) findViewById(R.id.input_confirm_password);
        
        // Set up the r=sign up form.

        Button mRegisterButton = findViewById(R.id.input_register_button);
        mRegisterButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                registerUser();
            }
        });

        Button mLearnMoreButton = findViewById(R.id.learn_more_button);
        mLearnMoreButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // Learn more Button
                registerUser();
            }
        });

        Button mEmailSignInButton = findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoLogin();
            }
        });

        // mRegisterFormView = findViewById(R.id.register_form);
        // mProgressView = findViewById(R.id.register_progress);

    }

    private void registerUser() {
        final String firstName = mFirstNameView.getText().toString().trim();
        final String otherNames = mOtherNamesView.getText().toString().trim();
        final String username = mUsernameView.getText().toString().trim();
        final String email = mEmailView.getText().toString().trim();
        final String phone = mPhoneView.getText().toString().trim();
        final String county = mCountyView.getText().toString().trim();
        final String password = mPasswordView.getText().toString().trim();
        final String cpassword = mConfirmPasswordView.getText().toString().trim();

        //first we will do the validations
 
        if (TextUtils.isEmpty(username)) {
            mUsernameView.setError("Please enter username");
            mUsernameView.requestFocus();
            return;
        }
 
        if (TextUtils.isEmpty(email)) {
            mEmailView.setError("Please enter your email");
            mEmailView.requestFocus();
            return;
        }
 
        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            mEmailView.setError("Enter a valid email");
            mEmailView.requestFocus();
            return;
        }
 
        if (TextUtils.isEmpty(password)) {
            mPasswordView.setError("Enter a password");
            mPasswordView.requestFocus();
            return;
        }
 
        StringRequest registerRequest = new StringRequest(Request.Method.POST, URLs.URL_REGISTER,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {

                            System.out.println(response);
                            JSONObject data = new JSONObject(response);

                            // Converting response to json object
                            JSONObject payload = data.getJSONObject("user");

                            //if no error in response
                            if (data.getBoolean("success")) {
                                Toast.makeText(getApplicationContext(), data.getString("message"), Toast.LENGTH_SHORT).show();

                                    String _id = payload.getString("_id");
                                    String first_name = payload.getString("first_name");
                                    String other_names = payload.getString("other_names");
                                    String username = payload.getString("username");
                                    String phone_number = payload.getString("phone_number");
                                    String email = payload.getString("email");
                                    String county = payload.getString("county");
                                    String auth_token = data.getString("auth_token");

                                    //creating a new user object
                                    User user = new User(
                                            _id,
                                            first_name,
                                            other_names,
                                            username,
                                            phone_number,
                                            email,
                                            county,
                                            auth_token
                                    );
                                    // Storing the user in shared preferences
                                    SharedPrefmanager.getInstance(getApplicationContext()).userLogin(user);

                                // Starting the profile activity
                                finish();
                                startActivity(new Intent(getApplicationContext(), ProfileActivity.class));
                            } else {
                                Toast.makeText(getApplicationContext(), data.getString("message"), Toast.LENGTH_SHORT).show();
                            }
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                    }
                }) {
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                Map<String, String> params = new HashMap<>();
                params.put("first_name", firstName);
                params.put("other_names", otherNames);
                params.put("username", username);
                params.put("phone_number", phone);
                params.put("county", county);
                params.put("email", email);
                params.put("password", password);
                params.put("password_confirmation", cpassword);
                return params;
            }
        };
 
        VolleySingleton.getInstance(this).addToRequestQueue(registerRequest);
 
    }
    private void gotoLogin() {
        Intent login = new Intent(RegisterActivity.this, LoginActivity.class);
        startActivity(login);
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle) {
        return null;
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {

    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {

    }
}

