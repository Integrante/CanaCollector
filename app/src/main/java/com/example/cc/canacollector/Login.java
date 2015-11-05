package com.example.cc.canacollector;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.example.cc.canacollector.Model.Cachaca;
import com.example.cc.canacollector.Model.Mosto;
import com.example.cc.canacollector.Model.Talhao;
import com.example.cc.canacollector.Model.Tonel;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

public class Login extends AppCompatActivity {

    private static final String USER = "Taverna Real";
    private static final String PASSWORD = "tavernademinas";
    private static final String TAG_ERROR = "Failed to Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Enable Local Datastore.
        Parse.enableLocalDatastore(this);

        //Register objects in Parse as well
        ParseObject.registerSubclass(Mosto.class);
        ParseObject.registerSubclass(Talhao.class);
        ParseObject.registerSubclass(Tonel.class);
        ParseObject.registerSubclass(Cachaca.class);

        Parse.initialize(this, "lR6GLeaXbzKFKbog1yK4ya0DU6D0R2HHrcA471Rb", "H6JEZRtTbqRdME8YjWNgtP2W3tByfxzDvYwLAaYe");
    }

    @Override
    public void onBackPressed() {
        ParseUser.logOut();
        finish();
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public void loginButton_Clicked(View v){
        //Verifica conexao com a internet
        if (!isOnline()) {
            Toast.makeText(this.getApplicationContext(), "No internet Connection", Toast.LENGTH_SHORT).show();
            return;
        }

        final ProgressDialog pDialog = new ProgressDialog(Login.this);
        pDialog.setMessage("Attempting login...");
        pDialog.setIndeterminate(false);
        pDialog.setCancelable(true);
        pDialog.show();

        ParseUser.logInInBackground(USER, PASSWORD, new LogInCallback() {
            public void done(ParseUser user, ParseException e) {
                if (user != null) {
                    pDialog.dismiss();
                    Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);
                } else {
                    // Signup failed. Look at the ParseException to see what happened.
                    System.out.println(TAG_ERROR);
                    Log.d("LOGIN:", e.toString());
                    pDialog.dismiss();
                    Toast.makeText(Login.this, TAG_ERROR, Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    public void createAccountButton_clicked(View v) {
        ParseUser user = new ParseUser();
        user.setUsername(USER);
        user.setPassword(PASSWORD);
        user.setEmail("email@example.com");

// other fields can be set just like with ParseObject
        //       user.put("phone", "650-253-0000");

        user.signUpInBackground(new SignUpCallback() {
            public void done(ParseException e) {
                if (e == null) {
                    Intent i = new Intent(Login.this, MainActivity.class);
                    startActivity(i);
                } else {
                    //return
                    System.out.println(e.toString());
                }
            }
        });
    }
}
