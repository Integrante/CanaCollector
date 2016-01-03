package com.example.cc.canacollector.helper;

import android.app.Activity;
import android.content.Context;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import com.example.cc.canacollector.Model.Cachaca;
import com.example.cc.canacollector.Model.ControleFermento;
import com.example.cc.canacollector.Model.Dorna;
import com.example.cc.canacollector.Model.Mosto;
import com.example.cc.canacollector.Model.Talhao;
import com.example.cc.canacollector.Model.Tonel;
import com.example.cc.canacollector.app.AppConfig;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParseObject;
import com.parse.ParsePush;
import com.parse.SaveCallback;

/**
 * Created by Breno on 1/3/2016.
 */
public class ParseUtils {
    private static String TAG = ParseUtils.class.getSimpleName();

    public static void verifyParseConfiguration(Context context) {
        if (TextUtils.isEmpty(AppConfig.PARSE_APPLICATION_ID) || TextUtils.isEmpty(AppConfig.PARSE_CLIENT_KEY)) {
            Toast.makeText(context, "Please configure your Parse Application ID and Client Key in AppConfig.java", Toast.LENGTH_LONG).show();
            ((Activity) context).finish();
        }
    }

    public static void registerParse(Context context) {
        // Enable Local Datastore.
       // Parse.enableLocalDatastore(this);

        //Register objects in Parse as well
        ParseObject.registerSubclass(Mosto.class);
        ParseObject.registerSubclass(Talhao.class);
        ParseObject.registerSubclass(Tonel.class);
        ParseObject.registerSubclass(Cachaca.class);
        ParseObject.registerSubclass(Dorna.class);
        ParseObject.registerSubclass(ControleFermento.class);

        // initializing parse library
        Parse.initialize(context, AppConfig.PARSE_APPLICATION_ID, AppConfig.PARSE_CLIENT_KEY);
        ParseInstallation.getCurrentInstallation().saveInBackground();

        ParsePush.subscribeInBackground(AppConfig.PARSE_CHANNEL, new SaveCallback() {
            @Override
            public void done(ParseException e) {
                Log.e(TAG, "Successfully subscribed to Parse!");
            }
        });
    }
}
