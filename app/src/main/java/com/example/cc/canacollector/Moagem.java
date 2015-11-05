package com.example.cc.canacollector;

import android.graphics.Color;
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

import com.example.cc.canacollector.Model.Talhao;
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
    private EditText qtde;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_moagem);

        //Carrega talhoes armazenados no banco da fazenda e insere no spinner
        List<String> itens = loadTalhoes();
        talhoes = (Spinner) findViewById(R.id.provenienteSpinner);
        talhoes.setOnItemSelectedListener(this);
        spinnerSetup(itens);

        qtde = (EditText) findViewById(R.id.qtdeCanaEditText);
        save = (Button) findViewById(R.id.moerSaveButton);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        // On selecting a spinner item
        ((TextView) parent.getChildAt(0)).setTextColor(Color.WHITE);
        ((TextView) parent.getChildAt(0)).setTextSize(22);
        String item = parent.getItemAtPosition(position).toString();

        // Showing selected spinner item
        Toast.makeText(parent.getContext(), "Selected: " + item, Toast.LENGTH_LONG).show();
    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }

    public List<String> loadTalhoes() {
        final List<String> spinnerArray =  new ArrayList<String>();
        List<ParseObject> talhaoList;

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Talhao");
        query.whereEqualTo("user", ParseUser.getCurrentUser());
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

//        // Spinner Drop down elements
//        List<String> categories = new ArrayList<String>();
//        categories.add("MACONHA");
//        categories.add("Business Services");
//        categories.add("Computers");
//        categories.add("Education");
//        categories.add("Personal");
//        categories.add("Travel");

        List<String> temp = spinnerArray;
        return temp;
    }

    public void spinnerSetup(List<String> itens)
    {
        ArrayAdapter<String> talhaoAdapter;
        talhaoAdapter = new ArrayAdapter<String>(
                this, android.R.layout.simple_spinner_item, itens);

        talhaoAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        talhoes.setAdapter(talhaoAdapter);
    }

    public void moerSaveButton_clicked(View v) {
        if ((qtde.getText() != null) && (talhoes.getSelectedItem().toString() != null))
            Toast.makeText(this, "Preenchidos", Toast.LENGTH_LONG).show();
        else
            Toast.makeText(this, " Nao Preenchidos", Toast.LENGTH_LONG).show();
    }
}
