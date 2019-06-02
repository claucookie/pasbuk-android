package es.claucookie.pasbuk.activities;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.util.DisplayMetrics;
import android.view.animation.AccelerateInterpolator;
import android.widget.ImageView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import es.claucookie.pasbuk.MainActivity_;
import es.claucookie.pasbuk.R;
import es.claucookie.pasbuk.helpers.LogHelper;
import es.claucookie.pasbuk.helpers.PreferencesHelper;
import es.claucookie.pasbuk.logic.PassbookLogic;
import es.claucookie.pasbuk.model.dto.PassBookDTO;

/**
 * Created by claucookie on 07/03/15.
 */
@EActivity(R.layout.activity_download_passbook)
public class DownloadPassbookActivity extends BaseActivity implements PassbookLogic.OnProcessPassbooksListener {

    private static final String TAG = DownloadPassbookActivity.class.getName();
    @Extra
    Uri pkpassUriExtra;
    @ViewById
    ImageView ticketImage;
    private ObjectAnimator animY = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        if (pkpassUriExtra != null) {
            handleIntentAndDownloadData();
        } else {
            // PKPASS file intent data
            Intent intent = getIntent();
            handleIntentData(intent);
        }
    }

    @Background
    public void handleIntentAndDownloadData() {
        PassbookLogic.getInstance().handleIntentData(this, pkpassUriExtra, this);
    }

    @Background
    public void handleIntentData(Intent intent) {
        PassbookLogic.getInstance().handleIntentData(this, intent.getData(), this);
    }

    @AfterViews
    void initViews() {
        initActionBar();
        startDownloadAnimation();
    }

    private void startDownloadAnimation() {
        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);
        animY = ObjectAnimator.ofFloat(ticketImage, "translationY", -(dm.heightPixels), 0f);
        animY.setDuration(2000);
        animY.setInterpolator(new AccelerateInterpolator());
        animY.setRepeatCount(ValueAnimator.INFINITE);
        animY.start();
    }

    private void initActionBar() {
        ActionBar bar = getSupportActionBar();
        if (bar != null) {
            bar.setTitle(getString(R.string.downloading_file));
        }
    }


    @Override
    @UiThread
    public void onPassbookContentParsed(PassBookDTO passBookDTO) {
        LogHelper.logD(TAG, "Passbook received!!!!");
        PreferencesHelper.getInstance().savePassBook(this, passBookDTO);
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                cancelAnim();
                // Actions to do after 2 seconds to let the user see the animation lol
                MainActivity_.intent(DownloadPassbookActivity.this).flags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP).start();
                finish();
            }
        }, 2000);
    }

    @Override
    public void onPassbookFileNotAvailable() {
        cancelAnim();
        LogHelper.logE(TAG, "Passsbook file not available anymore");
        showToast(R.string.pass_file_not_available_anymore);
        finish();
    }

    @Override
    public void onPassbookFileNotValid() {
        cancelAnim();
        LogHelper.logE(TAG, "Passsbook file has invalid data");
        showToast(R.string.pass_file_has_invalid_data);
        finish();
    }

    @UiThread
    void cancelAnim() {
        if (animY != null) {
            animY.cancel();
        }
    }

    @UiThread
    void showToast(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_LONG).show();
    }
}
