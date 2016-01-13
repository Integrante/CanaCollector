package com.example.cc.canacollector.helper;

import android.content.Context;
import android.net.ConnectivityManager;
import android.util.Log;

import com.example.cc.canacollector.Model.Alambique;
import com.parse.DeleteCallback;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.ArrayList;
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

    public static void getAlambiqueFromParse() {


        ParseQuery<ParseUser> query = ParseUser.getQuery();
        query.whereEqualTo("username", ParseUser.getCurrentUser().getUsername());
        query.include("alambique");

        query.getFirstInBackground(new GetCallback<ParseUser>() {
            @Override
            public void done(final ParseUser object, ParseException e) {
                if (e != null) {
                    Log.d("AppUtils", "Nao foi possivel recuperar o alambique do usuario");
                    return;
                }
                else {
                    final String user_alambique = "alambique";

                    final List<ParseObject> alambiqueResult = new ArrayList<ParseObject>();
                    //object.getParseObject("alambique");
                    alambiqueResult.add(object.getParseObject("alambique"));
                    final List<ParseObject> alambique = new ArrayList<>();
                    alambique.add(alambiqueResult.get(0));



                    // Release any objects previously pinned for this query.
                    ParseUser.unpinAllInBackground(user_alambique, alambiqueResult, new DeleteCallback() {
                        @Override
                        public void done(ParseException e) {
                            if (e != null) {
                                // There was some error.
                                return;
                            }
                            Log.w("App Utils", "Ta aqui");
                            // Add the latest results for this query to the cache.
                            ParseObject.pinAllInBackground(user_alambique,alambique );
                        }
                    });

                }
            }
        });
    }

    public static Alambique getAlambique() {
        Alambique alambique = new Alambique();

        ParseQuery<ParseObject> query = ParseQuery.getQuery("Alambique");
        query.fromLocalDatastore();

        try {
            alambique = (Alambique)query.getFirst();
        } catch (ParseException e) {
            Log.e("AppUtils", "Erro ao executar getAlambique");
        }



//        try {
//            ParseUser user = query.getFirst();
//            alambiqueResult = user.getParseObject("alambique");
//            alambique = (Alambique) alambiqueResult;
//            Log.d("Alambique em uso", alambique.getName());
//        } catch (ParseException e) {
//            e.printStackTrace();
//        }
        return alambique;
    }

}

