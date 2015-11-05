package com.example.cc.canacollector.Model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.UUID;

/**
 * Created by Breno on 10/29/2015.
 */
@ParseClassName("Talhao")
public class Talhao extends ParseObject {

    public void setUser (ParseUser user) { put("user", user);}

    public String getName () { return getString("nome"); }

    public void setName (String nome) { put("nome", nome);}

    public double getArea() {
        return getDouble("area");
    }

    public void setArea(Double area) {
        put("area", area);
    }

    public String getTipoCana() {
        return getString("tipo_cana");
    }

    public void setTipoCana(String tipoCana) {
        put("tipo_cana", tipoCana);
    }

    public void setUuidString() {
        UUID uuid = UUID.randomUUID();
        put("uuid", uuid.toString());
    }

    public String getUuidString() {
        return getString("uuid");
    }

    public static ParseQuery<Talhao> getQuery() {
        return ParseQuery.getQuery(Talhao.class);
    }
}
