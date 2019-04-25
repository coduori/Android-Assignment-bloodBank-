package com.example.bloodbank.authenticate;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.app.LoaderManager.LoaderCallbacks;

import android.content.Loader;
import android.database.Cursor;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
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
public class LoginActivity extends AppCompatActivity implements LoaderCallbacks<Cursor> {

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
    // private UserLoginTask mAuthTask = null;

    // UI references.
    private EditText mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        progressBar = (ProgressBar) findViewById(R.id.login_progress);
        mEmailView = (EditText) findViewById(R.id.input_email);
        mPasswordView = (EditText) findViewById(R.id.input_password);
 
        //if the user is already logged in we will directly start the profile activity
        // if (SharedPrefmanager.getInstance(this).isLoggedIn()) {
        //     finish();
        //     startActivity(new Intent(this, DashboardActivity.class));
        //     return;
        // }
        
        // Set up the login form.

        Button mEmailSignInButton = (Button) findViewById(R.id.input_sign_in_button);
        mEmailSignInButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                attemptLogin();
                // checkAuth();

            }
        });

        Button mLearnMoreButton = findViewById(R.id.learn_more_button);
        mLearnMoreButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                // Learn more Button
                attemptLogin();
            }
        });

        Button mEmailSignUpButton  = findViewById(R.id.email_sign_up_button);
        mEmailSignUpButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                gotoRegister();
            }
        });

        // mLoginFormView = findViewById(R.id.login_form);
        // mProgressView = findViewById(R.id.login_progress);

    }

    private void attemptLogin() {

        final String email = mEmailView.getText().toString();
        final String password = mPasswordView.getText().toString();

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


        // progressBar.setMessage("Signing In . . .");
        // progressBar.show();

        //if everything is fine
        StringRequest loginRequest = new StringRequest(Request.Method.POST, URLs.URL_LOGIN,
                new Response.Listener<String>(){
                    @Override
                    public void onResponse(String response) {
                        try {
                            JSONObject data = new JSONObject(response);

                            // Converting response to json object
                            JSONObject payload = data.getJSONObject("user");

                            // If no error in response
                            if (data.getBoolean("success")) {
                                Toast.makeText(getApplicationContext(), data.getString("message"), Toast.LENGTH_SHORT).show();

                                // Getting the user from the response

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
                                    //storing the user in shared preferences
                                    SharedPrefmanager.getInstance(getApplicationContext()).userLogin(user);
                                // }
                                //starting the profile activity
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
                params.put("email", email);
                params.put("password", password);
                return params;
            }
        };

        VolleySingleton.getInstance(this).addToRequestQueue(loginRequest);
        
    }

    private void gotoRegister() {
        Intent register = new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(register);
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

