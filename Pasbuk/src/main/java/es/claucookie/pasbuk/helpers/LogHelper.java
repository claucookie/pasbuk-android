package es.claucookie.pasbuk.helpers;

import android.app.Activity;
import android.util.Log;

import es.claucookie.pasbuk.BuildConfig;

/**
 * Created by claucookie on 09/03/15.
 */
public class LogHelper {
    
    public static void logD(String tag, String message) {
        if (BuildConfig.DEBUG) {
            Log.d(tag, message);    
        }
    }

    public static void logD(Class activityClass, String message) {
        if (BuildConfig.DEBUG) {
            Log.d(activityClass.getName(), message);
        }
    }

    public static void logE(String tag, String message) {
        if (BuildConfig.DEBUG && message != null) {
            Log.e(tag, message);
        }
    }
}
