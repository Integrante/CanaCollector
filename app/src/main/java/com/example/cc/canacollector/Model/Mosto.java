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

    public double getQuantidade() {
        return getDouble("quantidade");
    }

    public void setQuantidade(Double quantidade) {
        put("quantidade", quantidade);
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

    public void setTalhaoProveniente (String talhaoID) {
        put("talhao_proveniente", talhaoID);
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
