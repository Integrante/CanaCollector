package com.example.cc.canacollector.Model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import java.util.UUID;

/**
 * Created by Breno on 1/4/2016.
 */
@ParseClassName("Alambique")
public class Alambique extends ParseObject{

    public void setCNPJ (String cnpj) { put("cnpj", cnpj);}

    public String getCNPJ () {return getString("cnpj");}

    public String getName () { return getString("nome"); }

    public void setName (String nome) { put("nome", nome);}

    public void setUuidString() {
        UUID uuid = UUID.randomUUID();
        put("uuid", uuid.toString());
    }

    public String getUuidString() {
        return getString("uuid");
    }

    public static ParseQuery<Alambique> getQuery() {
        return ParseQuery.getQuery(Alambique.class);
    }
}
