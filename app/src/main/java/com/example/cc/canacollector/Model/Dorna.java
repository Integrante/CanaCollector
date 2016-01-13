package com.example.cc.canacollector.Model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.UUID;

/**
 * Created by Breno on 11/9/2015.
 */
@ParseClassName("Dorna")
public class Dorna extends ParseObject {
    public void setAlambique (Alambique alambique) { put("alambique", alambique);}

    public String getName () { return getString("nome"); }

    public void setName (String nome) { put("nome", nome);}

    public double getCapacidade () { return getDouble("capacidade");}

    public void setCapacidade (Double capacidade) { put("capacidade", capacidade);}

    public String getFermento () { return getString("fermento");}

    public void setFermento (String fermento) { put("fermento", fermento); }

    public void setUuidString() {
        UUID uuid = UUID.randomUUID();
        put("uuid", uuid.toString());
    }

    public String getUuidString() {
        return getString("uuid");
    }

    public static ParseQuery<Dorna> getQuery() {
        return ParseQuery.getQuery(Dorna.class);
    }
}
