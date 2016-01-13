package com.example.cc.canacollector.Model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.UUID;

/**
 * Created by Breno on 11/1/2015.
 */
@ParseClassName("Tonel")
public class Tonel extends ParseObject {
    public void setAlambique (Alambique alambique) { put("alambique", alambique);}

    public String getName () { return getString("nome"); }

    public void setName (String nome) { put("nome", nome);}

    public double getCapacidade () { return getDouble("capacidade");}

    public void setCapacidade (Double capacidade) { put("capacidade", capacidade);}

    public String getTipo () { return getString("tipo");}

    public void setTipo (String tipo) { put("tipo", tipo); }

    public double getEstoque () { return getDouble("estoque");}

    public void setEstoque (Double estoque) { put("estoque", estoque);}

    public void setUuidString() {
        UUID uuid = UUID.randomUUID();
        put("uuid", uuid.toString());
    }

    public String getUuidString() {
        return getString("uuid");
    }

    public static ParseQuery<Tonel> getQuery() {
        return ParseQuery.getQuery(Tonel.class);
    }
}
