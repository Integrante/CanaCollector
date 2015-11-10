package com.example.cc.canacollector;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import java.util.ArrayList;
import java.util.List;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

import com.example.cc.canacollector.Model.ControleFermento;
import com.example.cc.canacollector.Model.Dorna;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class Fermentacao extends AppCompatActivity implements OnItemSelectedListener {

    private Spinner spinner;
    private EditText temp, pH;
    private Button save;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fermentacao);

        //Carrega dornas armazenados no banco da fazenda e insere no spinner
        List<String> itens = loadDornas();
        spinner = (Spinner) findViewById(R.id.fermentaSpinner);
        spinner.setOnItemSelectedListener(this);
        setupDornas(itens);

        temp = (EditText) findViewById(R.id.tempEditText);
        pH = (EditText) findViewById(R.id.pHEditText);
        save = (Button) findViewById(R.id.fermentaSaveButton);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) parent.getChildAt(0)).setTextSize(22);
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public List<String> loadDornas() {
        final List<String> spinnerArray =  new ArrayList<String>();
        List<ParseObject> dornaList;

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Dorna");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.orderByAscending("nome");
        // = query.find();
        //Query a ser realizada nao pode ser em background senao nao eh possivel atualizar o spinner;
        try {
            dornaList = query.find();
            for (int i = 0; i < dornaList.size(); i++) {
                Dorna temp = (Dorna) dornaList.get(i);
                spinnerArray.add(temp.getName());
            }
        } catch (ParseException e)
        {

        }
        return spinnerArray;
    }

    public void setupDornas(List<String> itens)
    {
        ArrayAdapter<String> dornaAdapter;
        dornaAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, itens);

        dornaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(dornaAdapter);
    }

    public void fermentaSaveButton_clicked(View v) {
        boolean saved = false;
        save.setEnabled(false);
        if ((!temp.getText().toString().isEmpty()) && (!spinner.getSelectedItem().toString().isEmpty())) {
            if (isOnline()) {
                try {
                    ControleFermento controle = new ControleFermento();
                    controle.setUser(ParseUser.getCurrentUser());
                    controle.setTemperatura(Double.parseDouble(temp.getText().toString()));
                    //Verifica se o campo pH esta preenchido e deve ser salvo ao controle
                    if(!pH.getText().toString().isEmpty())
                        controle.setPH(Double.parseDouble(pH.getText().toString()));
                    controle.setDorna(findDorna(spinner.getSelectedItem().toString()));
                    controle.saveInBackground();
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
        else {
            Toast.makeText(this, "Os campos dorna e temperatura são obrigatórios!", Toast.LENGTH_LONG).show();
            save.setEnabled(true);
        }

        if (saved) {
            Toast.makeText(this, "Controle salvo com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public Dorna findDorna(String nome) {
        Dorna dorna= new Dorna();
        ParseObject dornaList;

        //Recupera a dorna do usuario logado com o nome fornecido
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Dorna");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.whereEqualTo("nome", nome);

        try {
            dornaList = query.getFirst();
            dorna = (Dorna)dornaList;
        }
        catch (ParseException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
        return dorna;
    }
}
