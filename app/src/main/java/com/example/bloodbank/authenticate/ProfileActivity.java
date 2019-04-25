package com.example.bloodbank.authenticate;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.example.bloodbank.CollectorActivity;
import com.example.bloodbank.DonorActivity;
import com.example.bloodbank.HomeActivity;
import com.example.bloodbank.R;
import com.example.bloodbank.utilities.Constants;
import com.example.bloodbank.utilities.SharedPrefmanager;
import com.example.bloodbank.utilities.User;
import com.pubnub.api.PNConfiguration;
import com.pubnub.api.PubNub;

public class ProfileActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    public static PubNub pubnub; // Pubnub instance
    TextView textViewId, textViewUsername, textViewNavUsername, textViewFullName, textViewFirstName, textViewOtherNames, textViewPhone, textViewEmail, textViewCounty;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        initPubnub();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
                startActivity(intent);
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        // Enter Code Here

        textViewFullName = (TextView) findViewById(R.id.user_profile_name);
        textViewUsername = (TextView) findViewById(R.id.user_username);

        textViewNavUsername = (TextView) findViewById(R.id.nav_user_username);
        textViewFirstName = (TextView) findViewById(R.id.user_first_name);
        textViewOtherNames = (TextView) findViewById(R.id.user_other_names);
        textViewPhone = (TextView) findViewById(R.id.user_phone_number);
        textViewEmail = (TextView) findViewById(R.id.user_email);
        textViewCounty = (TextView) findViewById(R.id.user_county);


        //getting the current user
        User user = SharedPrefmanager.getInstance(this).getUser();

        //setting the values to the textviews
        // textViewId.setText(String.valueOf(user.getId()));

        String fullName = user.getFirst_name() + " " + user.getOther_names();
        textViewFullName.setText(fullName);
        textViewNavUsername.setText(user.getUsername());

        textViewUsername.setText(user.getUsername());
        textViewFirstName.setText(user.getFirst_name());
        textViewOtherNames.setText(user.getOther_names());
        textViewPhone.setText(user.getPhone_number());
        textViewEmail.setText(user.getEmail());
        textViewCounty.setText(user.getCounty());
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.profile, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            startActivity(new Intent(ProfileActivity.this, CollectorActivity.class));
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(ProfileActivity.this, DonorActivity.class));
        } else if (id == R.id.nav_slideshow) {
            Intent intent = new Intent(ProfileActivity.this, HomeActivity.class);
            startActivity(intent);
        } else if (id == R.id.nav_manage) {
            startActivity(new Intent(ProfileActivity.this, ProfileActivity.class));

        } else if (id == R.id.nav_share) {
            Intent intent = new Intent(ProfileActivity.this, LoginActivity.class);
            startActivity(intent);

        } else if (id == R.id.nav_send) {
            // Calling the logout method
            finish();
            SharedPrefmanager.getInstance(getApplicationContext()).logout();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void initPubnub() {
        PNConfiguration pnConfiguration = new PNConfiguration();
        pnConfiguration.setSubscribeKey(Constants.PUBNUB_SUBSCRIBE_KEY);
        pnConfiguration.setPublishKey(Constants.PUBNUB_PUBLISH_KEY);
        pnConfiguration.setSecure(true);
        pubnub = new PubNub(pnConfiguration);
    }
}
