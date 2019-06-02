package es.claucookie.pasbuk.helpers;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;

/**
 * Created by claucookie on 08/04/15.
 */
public class IntentsHelper {

    public static void launchEmailIntent(Activity activity, String[] emailRecipients, String subject) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.putExtra(Intent.EXTRA_EMAIL, emailRecipients);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, "");
        emailIntent.setType("message/rfc822");
        activity.startActivity(emailIntent);
    }

    public static void launchExternalUrlWeb(final Activity activity, final String url) {
        if (activity != null) {
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            activity.startActivity(i);

        }
    }
}
