package com.example.cc.canacollector.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.example.cc.canacollector.Model.Alambique;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

/**
 * Created by Breno on 1/12/2016.
 */
public class AppUtils {
    public static boolean isOnline(Context context) {
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnectedOrConnecting();
    }

    public static Alambique getAlambique() {
        Alambique alambique = new Alambique();
        ParseObject alambiqueResult;

        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.include("alambique");

        try {
            ParseUser user = query.getFirst();
            alambiqueResult = user.getParseObject("alambique");
            alambique = (Alambique) alambiqueResult;
            Log.d("Alambique em uso", alambique.getName());
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return alambique;
    }
}
