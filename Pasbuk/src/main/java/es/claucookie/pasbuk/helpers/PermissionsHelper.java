package es.claucookie.pasbuk.helpers;

import android.Manifest;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import es.claucookie.pasbuk.BuildConfig;
import es.claucookie.pasbuk.Consts;
import es.claucookie.pasbuk.R;

/**
 * Created by claucookie on 17/04/16.
 */
public class PermissionsHelper {

    public static void checkStoragePermissions(Activity activity) {
        if (activity != null && !isStoragePermissionGranted(activity)) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {

                showStorageDialog(activity);
            } else {
                ActivityCompat.requestPermissions(
                        activity,
                        new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                        Consts.MY_PERMISSIONS_REQUEST_STORAGE);
            }
        }
    }

    public static void checkLocationPermissions(Activity activity) {
        if (activity != null && !isLocationPermissionGranted(activity)) {
            // Should we show an explanation?
            if (ActivityCompat.shouldShowRequestPermissionRationale(
                    activity,
                    Manifest.permission.ACCESS_FINE_LOCATION)) {

                showLocationDialog(activity);
            } else {
                ActivityCompat.requestPermissions(
                        activity,
                        new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                        Consts.MY_PERMISSIONS_REQUEST_LOCATION);
            }
        }
    }

    public static boolean isStoragePermissionGranted(Activity activity) {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean isLocationPermissionGranted(Activity activity) {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private static void showStorageDialog(final Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            showPermissionsDialog(activity, R.string.storage_permission_text);
        }
    }
    private static void showLocationDialog(final Activity activity) {
        if (activity != null && !activity.isFinishing()) {
            showPermissionsDialog(activity, R.string.location_permission_text);
        }
    }

    private static void showPermissionsDialog(final Activity activity, int resStringId) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(activity)
                .setMessage(activity.getString(resStringId));
        dialog.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                openAppSettings(activity);
            }
        });
        dialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    private static void openAppSettings(Activity activity) {
        if (activity != null) {
            Intent intent = new Intent();
            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
            Uri uri = Uri.fromParts("package", BuildConfig.APPLICATION_ID, null);
            intent.setData(uri);
            activity.startActivity(intent);
        }
    }
}
