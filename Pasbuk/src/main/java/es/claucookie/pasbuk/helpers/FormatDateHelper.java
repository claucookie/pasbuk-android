package es.claucookie.pasbuk.helpers;

import com.crashlytics.android.Crashlytics;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import io.fabric.sdk.android.Fabric;

/**
 * Created by claucookie on 18/08/15.
 */
public class FormatDateHelper {
    public static final String RELEVANT_DATE_FORMAT = "yyyy-MM-dd'T'HH:mmZZZ";
    public static final String RELEVANT_DATE_FORMAT2 = "yyyy-MM-dd'T'HH:mm:ss'Z'"; //"2015-06-30T15:30:00'+0100'"
    public static final String RELEVANT_DATE_FORMAT3 = "yyyy-MM-dd'T'HH:mm:ssz"; //"2015-06-30T15:30:00+00:00"
    public static final String RELEVANT_DATE_FORMAT4 = "yyyy-MM-dd'T'HH:mmz"; // 2016-07-25T17:00+02:00


    public static String getReadableDate(String date, SimpleDateFormat inFormat, SimpleDateFormat outFormat) {
        String formattedDate = "";
        Date dateTime = null;
        try {
            dateTime = inFormat.parse(date);
            formattedDate = outFormat.format(dateTime);
        } catch (ParseException e) {
            inFormat = new SimpleDateFormat(RELEVANT_DATE_FORMAT2, Locale.US);
            try {
                dateTime = inFormat.parse(date);
                formattedDate = outFormat.format(dateTime);
            } catch (ParseException e2) {
                inFormat = new SimpleDateFormat(RELEVANT_DATE_FORMAT3, Locale.US);
                try {
                    dateTime = inFormat.parse(date);
                    formattedDate = outFormat.format(dateTime);
                } catch (ParseException e3) {
                    inFormat = new SimpleDateFormat(RELEVANT_DATE_FORMAT4, Locale.US);
                    try {
                        dateTime = inFormat.parse(date);
                        formattedDate = outFormat.format(dateTime);
                    } catch (ParseException e4) {
                        LogHelper.logE("FormatDateHelper:getReadableDate", e3.getMessage());
                        Crashlytics.log(1000, "DateFormat", e3.getMessage());
                    }
                }
            }
        }
        return formattedDate;
    }

    public static long getLongDateTime(String date) {
        long time = (new Date()).getTime();
        SimpleDateFormat format = new SimpleDateFormat(RELEVANT_DATE_FORMAT, Locale.US);
        Date dateTime = null;
        try {
            dateTime = date != null ? format.parse(date) : new Date();
            time = dateTime.getTime();
        } catch (ParseException e) {
            format = new SimpleDateFormat(RELEVANT_DATE_FORMAT2, Locale.US);
            try {
                dateTime = format.parse(date);
                time = dateTime.getTime();
            } catch (ParseException e2) {
                format = new SimpleDateFormat(RELEVANT_DATE_FORMAT3, Locale.US);
                try {
                    dateTime = format.parse(date);
                    time = dateTime.getTime();
                } catch (ParseException e3) {
                    format = new SimpleDateFormat(RELEVANT_DATE_FORMAT4, Locale.US);
                    try {
                        dateTime = format.parse(date);
                        time = dateTime.getTime();
                    } catch (ParseException e4) {
                        LogHelper.logE("FormatDateHelper:getLongDateTime", e3.getMessage());
                        Crashlytics.log(1000, "DateFormat", e3.getMessage());
                    }
                }
            }
        }
        return time;
    }
}
