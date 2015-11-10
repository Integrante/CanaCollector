package com.example.cc.canacollector;

import android.content.Context;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.TextView;
import android.widget.Toast;

import com.example.cc.canacollector.Model.Cachaca;
import com.example.cc.canacollector.Model.Tonel;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class Destilacao extends AppCompatActivity implements OnItemSelectedListener {

    private EditText producao, acidez, gl, vinhoto;
    private Spinner tonelDestino;
    private Button armazena;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_destilacao);

        producao = (EditText) findViewById(R.id.producaoEditText);
        acidez = (EditText) findViewById(R.id.acidezEditText);
        gl = (EditText) findViewById(R.id.glEditText);
        vinhoto = (EditText) findViewById(R.id.vinhotoEditText);
        armazena = (Button) findViewById(R.id.armazenaButton);

        //Carrega dornas armazenados no banco da fazenda e insere no spinner
        List<String> itens = loadToneis();
        tonelDestino = (Spinner) findViewById(R.id.tonelSpinner);
        tonelDestino.setOnItemSelectedListener(this);
        setupTonel(itens);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) parent.getChildAt(0)).setTextSize(22);
    }

    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public List<String> loadToneis() {
        final List<String> spinnerArray =  new ArrayList<String>();
        List<ParseObject> tonelList;

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tonel");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.orderByAscending("nome");
        // = query.find();
        //Query a ser realizada nao pode ser em background senao nao eh possivel atualizar o spinner;
        try {
            tonelList = query.find();
            for (int i = 0; i < tonelList.size(); i++) {
                Tonel temp = (Tonel) tonelList.get(i);
                spinnerArray.add(temp.getName());
            }
        } catch (ParseException e)
        {

        }
        return spinnerArray;
    }

    public void setupTonel(List<String> itens)
    {
        ArrayAdapter<String> dornaAdapter;
        dornaAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, itens);

        dornaAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        tonelDestino.setAdapter(dornaAdapter);
    }

    public void armazenaButton_clicked(View v) {
        boolean saved = false;
        armazena.setEnabled(false);
        if ((!producao.getText().toString().isEmpty()) && (!tonelDestino.getSelectedItem().toString().isEmpty())
                && (!acidez.getText().toString().isEmpty()) && (!gl.getText().toString().isEmpty())
                && (!vinhoto.getText().toString().isEmpty())) {
            if (isOnline()) {
                try {
                    Cachaca cachaca = new Cachaca();
                    cachaca.setUser(ParseUser.getCurrentUser());
                    cachaca.setQuantidade(Double.parseDouble(producao.getText().toString()));
                    cachaca.setAcidez(Double.parseDouble(acidez.getText().toString()));
                    cachaca.setGL(Double.parseDouble(gl.getText().toString()));
                    cachaca.setDestinoVinhoto(vinhoto.getText().toString());

                    //Encontra tonel de destino e atualiza a quantidade armazenada
                    Tonel tonel = findTonel(tonelDestino.getSelectedItem().toString());
                    cachaca.setTonel(tonel);
                    tonel.setEstoque(tonel.getEstoque() + cachaca.getQuantidade());
                    cachaca.saveInBackground();
                    tonel.saveInBackground();
                    saved = true;
                } catch (Exception e) {
                    armazena.setEnabled(true);
                }
            }
            else {
                Toast.makeText(this, "Verifique sua conexao com a internet e tente novamente...", Toast.LENGTH_LONG).show();
                armazena.setEnabled(true);
            }
        }
        else {
            Toast.makeText(this, "Preencha todos os campos!", Toast.LENGTH_LONG).show();
            armazena.setEnabled(true);
        }

        if (saved) {
            Toast.makeText(this, "Cachaça registrada com sucesso!", Toast.LENGTH_LONG).show();
            finish();
        }
    }

    public boolean isOnline() {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public Tonel findTonel(String nome) {
        Tonel tonel = new Tonel();
        ParseObject tonelList;

        //Recupera a dorna do usuario logado com o nome fornecido
        ParseQuery<ParseObject> query = ParseQuery.getQuery("Tonel");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
        query.whereEqualTo("nome", nome);

        try {
            tonelList = query.getFirst();
            tonel = (Tonel)tonelList;
        }
        catch (ParseException e) {
            Toast.makeText(this, e.toString(), Toast.LENGTH_LONG).show();
        }
        return tonel;
    }
}
