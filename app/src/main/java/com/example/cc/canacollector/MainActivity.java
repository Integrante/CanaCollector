package com.example.cc.canacollector;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.cc.canacollector.Model.Dorna;
import com.example.cc.canacollector.Model.Talhao;
import com.parse.ParseAnonymousUtils;
import com.parse.ParseObject;
import com.parse.ParseUser;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume() {
        super.onResume();
//        Toast.makeText(this.getApplicationContext(), "VOLTANDO A MAIN PAGE!!!", Toast.LENGTH_SHORT).show();
    }

    public void moagemButton_clicked(View v) {
        Intent i = new Intent(MainActivity.this, Moagem.class);
        startActivity(i);
//        String temp;
//        ParseUser currentUser = ParseUser.getCurrentUser();

//        for (int i = 1; i<12; i++) {
//            Talhao talhao = new Talhao();
//            temp = new String("T" + i);
//            Toast.makeText(this.getApplicationContext(), temp, Toast.LENGTH_SHORT).show();
//            talhao.setName(temp);
//            talhao.setUser(currentUser);
//            talhao.saveInBackground();
//        }
    }

    public void fermentaButton_clicked(View v) {
        Intent i = new Intent(MainActivity.this, Fermentacao.class);
        startActivity(i);
    }

    public void destilaButton_clicked(View v) {
        Intent i = new Intent(MainActivity.this, Destilacao.class);
        startActivity(i);
    }

    private void syncTodosToParse() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo ni = cm.getActiveNetworkInfo();
        if ((ni != null) && (ni.isConnected())) {
            if (!ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
                // If we have a network connection and a current
                // logged in user, sync the todos
            } else {
                // If we have a network connection but no logged in user, direct
                // the person to log in or sign up.
                //ParseLoginBuilder builder = new ParseLoginBuilder(this); ---------------
                //startActivityForResult(builder.build(), LOGIN_ACTIVITY_CODE);----------------
            }
        } else {
            // If there is no connection, let the user know the sync didn't happen
            Toast.makeText(
                    getApplicationContext(),
                    "Your device appears to be offline. Some todos may not have been synced to Parse.",
                    Toast.LENGTH_LONG).show();
        }
    }

}
