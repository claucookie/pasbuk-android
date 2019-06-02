package es.claucookie.pasbuk;

/**
 * Created by claucookie on 28/03/15.
 */
public class Consts {
    public static final String FILE_SCHEME = "file://";
    public static final String DRAWABLE_SCHEME = "drawable://";
    public static final String ASSETS_SCHEME = "assets://";
    public static final String ASSETS_URL = ASSETS_SCHEME + "images/";
    public static final int TIME_OUT_IN_MILLIS = 10000;
    public static final int A_DAY_IN_MILLIS = 24 * 3600 * 1000;


    public static final int DELETE_PASS_REQUEST_CODE= 111;
    public static final int OPEN_FILE_REQUEST_CODE = 112;
    public static final int SETTINGS_INTENT_REQUEST = 113;

    public static final int MY_PERMISSIONS_REQUEST_STORAGE = 2;
    public static final int MY_PERMISSIONS_REQUEST_LOCATION = 3;
}
