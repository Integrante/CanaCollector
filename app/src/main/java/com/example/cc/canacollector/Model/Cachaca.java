package com.example.cc.canacollector.Model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.UUID;

/**
 * Created by Breno on 11/1/2015.
 */
@ParseClassName("Cachaca")
public class Cachaca extends ParseObject{

    public void setUser (ParseUser user) { put("user", user);}

    public double getQuantidade() {
        return getDouble("quantidade");
    }

    public void setQuantidade(Double quantidade) {
        put("quantidade", quantidade);
    }

    public double getGL() {
        return getDouble("GL");
    }

    public void setGL(Double GL) {
        put("brix", GL);
    }

    public double getAcidez() {
        return getDouble("acidez");
    }

    public void setAcidez (Double acidez) {
        put("acidez", acidez);
    }

    public String getDestinoVinhoto() {
        return getString("destino_vinhoto");
    }

    public void setDestinoVinhoto(String destino) {
        put("destino_vinhoto", destino);
    }

    public void setTonel(Tonel tonel) {
        put("tonel_destino", tonel);
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
