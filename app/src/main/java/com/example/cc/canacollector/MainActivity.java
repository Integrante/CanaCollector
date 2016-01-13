package com.example.cc.canacollector;

import android.app.ProgressDialog;
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

import com.example.cc.canacollector.Model.Alambique;
import com.example.cc.canacollector.Model.Dorna;
import com.example.cc.canacollector.Model.Talhao;
import com.example.cc.canacollector.Model.Tonel;
import com.example.cc.canacollector.helper.AppUtils;
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

//        Alambique userAlambique = AppUtils.getAlambique();
//        ProgressDialog pDialog = new ProgressDialog(MainActivity.this);
//        pDialog.setMessage("Cadastrando toneis...");
//        pDialog.setIndeterminate(false);
//        pDialog.setCancelable(true);
//        pDialog.show();
//        String temp;
//
//
//        for (int i = 1; i<=12; i++) {
//            Talhao talhao = new Talhao();
//            temp = new String("Talhao " + i);// + " - Jequitiba");
//
//            talhao.setName(temp);
//            talhao.setAlambique(userAlambique);
//            talhao.setArea(5.5);
//            talhao.setTipoCana("Precoce");
//            talhao.saveInBackground();
//        }
//        pDialog.dismiss();
//        Toast.makeText(this.getApplicationContext(), "DONE!", Toast.LENGTH_SHORT).show();
    }

    public void fermentaButton_clicked(View v) {
        Intent i = new Intent(MainActivity.this, Fermentacao.class);
        startActivity(i);
    }

    public void destilaButton_clicked(View v) {
        Intent i = new Intent(MainActivity.this, Destilacao.class);
        startActivity(i);
    }
}
