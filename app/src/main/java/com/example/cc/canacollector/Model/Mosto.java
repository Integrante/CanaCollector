package com.example.cc.canacollector.Model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.UUID;

/**
 * Created by Breno on 10/29/2015.
 */
@ParseClassName("Mosto")
public class Mosto extends ParseObject {

    public void setUser (ParseUser user) { put("user", user);}

    public double getCaldo() {
        return getDouble("qtde_caldo");
    }

    public void setCaldo(Double quantidade) {
        put("qtde_caldo", quantidade);
    }

    public double getCana() {
        return getDouble("qtde_cana");
    }

    public void setCana(Double quantidade) {
        put("qtde_cana", quantidade);
    }

    public double getBrix() {
        return getDouble("brix");
    }

    public void setBrix(Double brix) {
        put("brix", brix);
    }

    public String getTalhaoProveniente() {
        return getString("talhao_proveniente");
    }

    public void setTalhaoProveniente (Talhao talhao) {
        put("talhao_proveniente", talhao);
    }

    public void setUuidString() {
        UUID uuid = UUID.randomUUID();
        put("uuid", uuid.toString());
    }

    public String getUuidString() {
        return getString("uuid");
    }

    public static ParseQuery<Mosto> getQuery() {
        return ParseQuery.getQuery(Mosto.class);
    }
}
