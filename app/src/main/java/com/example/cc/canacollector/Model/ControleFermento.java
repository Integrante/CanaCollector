package com.example.cc.canacollector.Model;

import com.parse.ParseClassName;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.UUID;

/**
 * Created by Breno on 11/9/2015.
 */
@ParseClassName("ControleFermento")
public class ControleFermento extends ParseObject {
    public void setAlambique (Alambique alambique) { put("alambique", alambique);}

    public double getTemperatura () { return getDouble("temperatura");}

    public void setTemperatura (Double temp) { put("temperatura", temp);}

    public double getPH () { return getDouble("ph");}

    public void setPH (Double pH) { put("ph", pH);}

    public void setDorna(Dorna dorna) {
        put("dorna", dorna);
    }

    public void setUuidString() {
        UUID uuid = UUID.randomUUID();
        put("uuid", uuid.toString());
    }

    public String getUuidString() {
        return getString("uuid");
    }

    public static ParseQuery<ControleFermento> getQuery() {
        return ParseQuery.getQuery(ControleFermento.class);
    }
}
