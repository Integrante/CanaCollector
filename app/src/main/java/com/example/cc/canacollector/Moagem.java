package com.example.cc.canacollector;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.cc.canacollector.Model.Mosto;
import com.example.cc.canacollector.Model.Talhao;
import com.example.cc.canacollector.helper.AppUtils;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;


public class Moagem extends AppCompatActivity implements OnItemSelectedListener {

    private Spinner talhoes;
    private Button save;
    private EditText qtdeCana, qtdeCaldo, brix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moagem);

        //Carrega talhoes armazenados no banco da fazenda e insere no spinner
        List<String> itens = loadTalhoes();
        talhoes = (Spinner) findViewById(R.id.provenienteSpinner);
        talhoes.setOnItemSelectedListener(this);
        spinnerSetup(itens);

        qtdeCana = (EditText) findViewById(R.id.qtdeCanaEditText);
        qtdeCaldo = (EditText) findViewById(R.id.caldoEditText);
        brix = (EditText) findViewById(R.id.brixEditText);
        save = (Button) findViewById(R.id.moerSaveButton);
        save.setEnabled(true);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) parent.getChildAt(0)).setTextSize(22);
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public List<String> loadTalhoes() {
        final List<String> spinnerArray =  new ArrayList<String>();
        List<ParseObject> talhaoList;

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Talhao");
        query.whereEqualTo("alambique", AppUtils.getAlambique());
        query.orderByAscending("nome");
        // = query.find();
        //Query a ser realizada nao pode ser em background senao nao eh possivel atualizar o spinner;
        try {
            talhaoList = query.find();
            for (int i = 0; i < talhaoList.size(); i++) {
                Talhao temp = (Talhao) talhaoList.get(i);
                spinnerArray.add(temp.getName());
            }
        } catch (ParseException e)
        {

        }
        //query.findInBackground(new FindCallback<ParseObject>() {
          //  public void done(List<ParseObject> talhaoList, ParseException e) {
            //    if (e == null) {
              //      Log.d("TALHAO", "Retrieved " + talhaoList.size() + " talhoes");
//                    for (int i = 0; i < talhaoList.size(); i++) {
//                        Talhao temp = (Talhao) talhaoList.get(i);
//                        spinnerArray.add(temp.getName());
//                    }

                //} else {
                  //  Log.d("TALHAO", "Error: " + e.getMessage());
                //}
           // }
        //});

        return spinnerArray;
    }

    public void spinnerSetup(List<String> itens)
    {
        ArrayAdapter<String> talhaoAdapter;
        talhaoAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, itens);

        talhaoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        talhoes.setAdapter(talhaoAdapter);
    }

    public Talhao findTalhao(String nome) {
        Talhao talhao = new Talhao();
        ParseObject talhaoList;

        //Recupera o talhao do usuario logado com o nome fornecido
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Talhao");
        query.whereEqualTo("alambique", AppUtils.getAlambique());
        query.whereEqualTo("nome", nome);

        try {
            talhaoList = query.getFirst();
            talhao = (Talhao)talhaoList;
        }
        catch (ParseException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
        return talhao;
    }

    public void moerSaveButton_clicked(View v) {
        boolean saved = false;
        save.setEnabled(false);
        if ((!qtdeCana.getText().toString().isEmpty()) && (!talhoes.getSelectedItem().toString().isEmpty()) &&
                (!brix.getText().toString().isEmpty()) && (!qtdeCaldo.getText().toString().isEmpty())) {
            if (AppUtils.isOnline(this.getApplicationContext())) {
                try {
                    Mosto mosto = new Mosto();
                    mosto.setAlambique(AppUtils.getAlambique());
                    mosto.setBrix(Double.parseDouble(brix.getText().toString()));
                    mosto.setCaldo(Double.parseDouble(qtdeCaldo.getText().toString()));
                    mosto.setCana(Double.parseDouble(qtdeCana.getText().toString()));
                    mosto.setTalhaoProveniente(findTalhao(talhoes.getSelectedItem().toString()));
                    mosto.saveInBackground();
                    saved = true;
                } catch (Exception e) {
                    save.setEnabled(true);
                }
            }
            else {
                Toast.makeText(this, "Verifique sua conexao com a internet e tente novamente...", Toast.LENGTH_LONG).show();
                save.setEnabled(true);
            }
        }
        else
            Toast.makeText(this, "Por favor, insira todos os dados!", Toast.LENGTH_LONG).show();

        if (saved) {
            Toast.makeText(this, "Moagem salva com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

//    public boolean isOnline() {
//        ConnectivityManager cm =
//                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
//
//        return cm.getActiveNetworkInfo() != null &&
//                cm.getActiveNetworkInfo().isConnectedOrConnecting();
//    }
}
