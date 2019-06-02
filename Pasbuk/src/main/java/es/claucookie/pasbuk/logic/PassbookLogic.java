package es.claucookie.pasbuk.logic;

import android.app.Activity;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.database.Cursor;
import android.net.Uri;
import android.os.Build;

import com.crashlytics.android.Crashlytics;
import com.sromku.simple.storage.SimpleStorage;
import com.sromku.simple.storage.Storage;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Locale;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import es.claucookie.pasbuk.Consts;
import es.claucookie.pasbuk.R;
import es.claucookie.pasbuk.helpers.LogHelper;
import es.claucookie.pasbuk.model.dao.PassBookDAO;
import es.claucookie.pasbuk.model.dto.PassBookDTO;

/**
 * Created by claucookie on 09/03/15.
 */
public class PassbookLogic {

    private static final String TAG = PassbookLogic.class.getName();
    private static final String IMAGES_DIR = "pasbukImages";
    private static PassbookLogic instance = new PassbookLogic();
    /**
     * Listener to notify from passbook events: success, error, etc
     */
    public OnProcessPassbooksListener listener;
    private boolean receiverRegistered = false;
    private ArrayList<Long> downloadIDs = new ArrayList<>();
    private String downloadCompleteIntentName = DownloadManager.ACTION_DOWNLOAD_COMPLETE;
    private IntentFilter downloadCompleteIntentFilter = new IntentFilter(downloadCompleteIntentName);
    /**
     * Download manager listener
     */
    private BroadcastReceiver downloadCompleteReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            if (context != null) {
                long id = intent.getLongExtra(DownloadManager.EXTRA_DOWNLOAD_ID, 0L);
                if (!downloadIDs.contains(Long.valueOf(id))) {
                    LogHelper.logE(TAG, "Ingnoring unrelated download " + id);
                    return;
                }

                // Get cursor to query downloaded data by id
                DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
                DownloadManager.Query query = new DownloadManager.Query();
                query.setFilterById(id);
                Cursor cursor = downloadManager.query(query);

                // it shouldn't be empty, but just in case
                if (!cursor.moveToFirst()) {
                    LogHelper.logE(TAG, "Empty row 68");
                    return;
                }

                // Get downloaded content uri
                int uriIndex = cursor.getColumnIndex(DownloadManager.COLUMN_LOCAL_URI);
                String downloadedPackageUriString = cursor.getString(uriIndex);
                handleIntentData(context, Uri.parse(downloadedPackageUriString), listener);

                // Reset data
                unregisterReceiver(context);
                downloadIDs.remove(Long.valueOf(id));
            }
        }
    };

    /**
     * Constructor and singleton instance
     */
    public PassbookLogic() {
    }

    public static PassbookLogic getInstance() {
        return instance;
    }

    public void handleIntentData(Context context, Uri uri, OnProcessPassbooksListener passbooksListener) {

        this.listener = passbooksListener;

        if (context != null && uri != null) {
            final String scheme = uri.getScheme();

            // Url got from web browser
            if (scheme.contains("http")) {
                // Register download receiver
                downloadFile(context, uri);

                // File got from downloads
            } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
                try {
                    InputStream attachment = context.getContentResolver().openInputStream(uri);
                    obtainPassbookInfo(context, attachment);
                } catch (FileNotFoundException e) {
                    passbookFileNotAvailableListener();
                    LogHelper.logE(TAG, e.getMessage());
                } catch (SecurityException e) {
                    Crashlytics.log("URI does not contain a valid access token." + uri.toString());
                    LogHelper.logE(TAG, e.getMessage());
                }

                // File got from storage
            } else {
                File file = new File(uri.getPath());
                FileInputStream fis = null;
                try {
                    fis = new FileInputStream(file);
                    obtainPassbookInfo(context, fis);
                } catch (FileNotFoundException e) {
                    LogHelper.logE(TAG, e.getMessage());
                }
            }
        }
    }

    public void unregisterReceiver(Context context) {
        if (receiverRegistered && downloadCompleteReceiver != null) {
            try {
                receiverRegistered = false;
                context.unregisterReceiver(downloadCompleteReceiver);
            } catch (IllegalArgumentException ex) {
                Crashlytics.log(1000, "Catch PassbookLogic.java", ex.getMessage());
            }
        }
    }

    public void registerReceiver(Context context) {
        if (!receiverRegistered && context != null) {
            context.registerReceiver(downloadCompleteReceiver, downloadCompleteIntentFilter);
            receiverRegistered = true;
        }
    }

    private void downloadFile(Context context, Uri uri) {

        registerReceiver(context);

        try {
            DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
            DownloadManager.Request request = new DownloadManager.Request(Uri.parse(uri.toString()));

            // Download notification settings
            request.setTitle(uri.getLastPathSegment());
            request.setVisibleInDownloadsUi(true);
            request.setDescription(context.getString(R.string.downloading_file));
            request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);

            // enqueue this request
            long downloadId = downloadManager.enqueue(request);
            downloadIDs.add(downloadId);
        } catch (IllegalArgumentException e) {
            Crashlytics.log(e.getMessage());
        }
    }

    public void downloadUpdatedPassbook(Context context, PassBookDTO passbook, OnProcessPassbooksListener passbooksListener) {

        this.listener = passbooksListener;
        registerReceiver(context);

        String uriString = passbook.getUpdatePassUri();
        DownloadManager downloadManager = (DownloadManager) context.getSystemService(Context.DOWNLOAD_SERVICE);
        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(uriString));
        request.addRequestHeader("Authorization", "ApplePass " + passbook.getAuthenticationToken());

        // Download notification settings
        request.setVisibleInDownloadsUi(false);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE);

        // enqueue this request
        long downloadId = downloadManager.enqueue(request);
        downloadIDs.add(downloadId);
    }

    private JSONObject unzipInputStream(Context context, InputStream in) {
        JSONObject pass = new JSONObject();
        byte[] logoBytes = null;
        byte[] backgroundBytes = null;
        byte[] stripBytes = null;
        byte[] thumbnailBytes = null;
        try {
            ZipInputStream zis = new ZipInputStream(in);
            ZipEntry entry;
            boolean unzipped = false;
            // Read folder files
            while ((entry = zis.getNextEntry()) != null) {
                String filename = entry.getName();
                if (filename.equals(PassBookDTO.PASSBOOK_JSON_FILENAME)) {
                    StringBuilder s = new StringBuilder();
                    int read = 0;
                    byte[] buffer = new byte[1024];
                    while ((read = zis.read(buffer, 0, 1024)) >= 0)
                        s.append(new String(buffer, 0, read));

                    String passInfo = s.toString();
                    // Trying to correct some format errors like ",}" "},]"
                    passInfo = passInfo.replaceAll("\\,\\s*\\}", " }");
                    passInfo = passInfo.replaceAll("\\},\\s*\\]", "} ]");
                    pass = new JSONObject(passInfo);

                } else if (logoBytes == null && filename.equals(PassBookDTO.LOGO_IMAGE_FILENAME)) {
                    // Get logo 1x just if logo bytes is not fetted yet, other wise, take 2x logo
                    logoBytes = getBytesFromFileInsizeZip(zis, filename);

                } else if (filename.equals(PassBookDTO.LOGO_IMAGE_FILENAME2X)) {
                    logoBytes = getBytesFromFileInsizeZip(zis, filename);

                } else if (filename.equals(PassBookDTO.BACKGROUND_IMAGE_FILENAME)) {
                    backgroundBytes = getBytesFromFileInsizeZip(zis, filename);

                } else if (filename.equals(PassBookDTO.THUMBNAIL_IMAGE_FILENAME)) {
                    thumbnailBytes = getBytesFromFileInsizeZip(zis, filename);

                } else if (filename.equals(PassBookDTO.STRIP_IMAGE_FILENAME)) {
                    stripBytes = getBytesFromFileInsizeZip(zis, filename);
                }
                unzipped = true;
            }
            in.close();
            if (!unzipped) {
                pass = null;
            } else {
                pass = saveImagesToInternalStorage(context, pass, logoBytes, backgroundBytes, stripBytes, thumbnailBytes);
            }
        } catch (Exception e) {
            LogHelper.logE(TAG, e.getMessage());
            passbookFileNotValidListener();
        }
        return pass;
    }

    private byte[] getBytesFromFileInsizeZip(ZipInputStream zis, String filename) {
        byte[] fileBytes = null;
        if (!filename.isEmpty()) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int count;
            try {
                while ((count = zis.read(buffer)) != -1) {
                    baos.write(buffer, 0, count);
                }
            } catch (IOException e) {
                LogHelper.logE(TAG, e.getMessage());
            }
            fileBytes = baos.toByteArray();
        }
        return fileBytes;
    }

    private JSONObject saveImagesToInternalStorage(Context context, JSONObject pass, byte[] logoBytes, byte[] backgroundBytes, byte[] stripBytes, byte[] thumbnailBytes) {
        PassBookDTO passbookDTO = null;
        try {
            passbookDTO = PassBookDAO.getInstance().create(pass);
        } catch (JSONException e) {
            passbookFileNotValidListener();
            LogHelper.logE(TAG, e.getMessage());
        }
        if (passbookDTO != null && context != null) {
            String logoFilename = String.format(Locale.US, "%s-%s", passbookDTO.getSerialNumber(), PassBookDTO.LOGO_IMAGE_FILENAME.replace("@2x", ""));
            String backgroundFilename = String.format(Locale.US, "%s-%s", passbookDTO.getSerialNumber(), PassBookDTO.BACKGROUND_IMAGE_FILENAME.replace("@2x", ""));
            String stripFilename = String.format(Locale.US, "%s-%s", passbookDTO.getSerialNumber(), PassBookDTO.STRIP_IMAGE_FILENAME.replace("@2x", ""));
            String thumbnailFilename = String.format(Locale.US, "%s-%s", passbookDTO.getSerialNumber(), PassBookDTO.THUMBNAIL_IMAGE_FILENAME.replace("@2x", ""));


            if (logoBytes != null) {
                passbookDTO.setLogoImage(createImageFileAndGetPath(context, IMAGES_DIR, logoFilename, logoBytes));
            }

            if (backgroundBytes != null) {
                passbookDTO.setBackgroundImage(createImageFileAndGetPath(context, IMAGES_DIR, backgroundFilename, backgroundBytes));
            }

            if (stripBytes != null) {
                passbookDTO.setStripImage(createImageFileAndGetPath(context, IMAGES_DIR, stripFilename, stripBytes));
            }

            if (thumbnailBytes != null) {
                passbookDTO.setThumbnailImage(createImageFileAndGetPath(context, IMAGES_DIR, thumbnailFilename, thumbnailBytes));
            }

        }
        try {
            pass = PassBookDAO.getInstance().serialize(passbookDTO);
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return pass;
    }

    private String createImageFileAndGetPath(Context context, String imagesDir, String imageName, byte[] imageBytes) {
        String path = "";

        // Create directory to save images
        Storage storage = SimpleStorage.getInternalStorage(context);
        if (!storage.isDirectoryExists(imagesDir)) {
            storage.createDirectory(imagesDir);
        }

        if (imageBytes != null) {
            // Save logo
            storage.createFile(imagesDir, imageName, imageBytes);
            File file = storage.getFile(imagesDir, imageName);
            path = file.getAbsolutePath();
        }
        return path;
    }

    private void obtainPassbookInfo(Context context, InputStream in) {
        try {
            final JSONObject passJSON = unzipInputStream(context, in);
            if (passJSON != null) {
                final PassBookDTO passbookDTO = PassBookDAO.getInstance().create(passJSON);
                passPassbookDataToListener(passbookDTO);
            } else {
                passbookFileNotValidListener();
            }
        } catch (JSONException e) {
            LogHelper.logE(TAG, e.getMessage());
            passbookFileNotValidListener();
        }
    }

    public void performFileSearch(Activity activity) {

        if (activity != null) {

            if (Build.MANUFACTURER.equalsIgnoreCase("samsung")) {
                Intent intent = new Intent("com.sec.android.app.myfiles.PICK_DATA");
                intent.putExtra("CONTENT_TYPE", "*/*");
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                activity.startActivityForResult(intent, Consts.OPEN_FILE_REQUEST_CODE);
            } else {
                String type = "*/*";
                Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
                intent.setType(type);
                // Check if there is any activity to handle the intent
                if (checkFileSearchAvailable(activity)) {
                    activity.startActivityForResult(intent, Consts.OPEN_FILE_REQUEST_CODE);
                }
            }
        }
    }

    public boolean checkFileSearchAvailable(Activity activity) {
        Intent intent = new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS);
        return intent.resolveActivity(activity.getPackageManager()) != null;
    }

    public void sortPassesByRelevantDate(ArrayList<PassBookDTO> passes) {
        // Dates most recents first
        Collections.sort(passes, new Comparator<PassBookDTO>() {
            @Override
            public int compare(PassBookDTO pass1, PassBookDTO pass2) {
                long relevantDate1 = pass1.getLongTime();
                long relevantDate2 = pass2.getLongTime();
                return Long.valueOf(relevantDate2).compareTo(relevantDate1);
            }
        });
    }

    public void sortPassesByUpdateDate(ArrayList<PassBookDTO> passes) {
        // Recent dates first
        Collections.sort(passes, new Comparator<PassBookDTO>() {
            @Override
            public int compare(PassBookDTO pass1, PassBookDTO pass2) {
                return pass2.getUpdateDate().compareTo(pass1.getUpdateDate());
            }
        });
    }

    /**
     * Listeners
     */


    private void passbookFileNotValidListener() {
        if (listener != null) {
            listener.onPassbookFileNotValid();
        }
    }

    private void passbookFileNotAvailableListener() {
        if (listener != null) {
            listener.onPassbookFileNotAvailable();
        }
    }

    private void passPassbookDataToListener(PassBookDTO passbookDTO) {
        if (listener != null) {
            listener.onPassbookContentParsed(passbookDTO);
        }
    }


    public interface OnProcessPassbooksListener {
        void onPassbookContentParsed(PassBookDTO passBookDTO);

        void onPassbookFileNotAvailable();

        void onPassbookFileNotValid();
    }


}
