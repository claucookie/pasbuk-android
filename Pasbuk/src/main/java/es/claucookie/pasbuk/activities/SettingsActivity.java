package es.claucookie.pasbuk.activities;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;


import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;

import es.claucookie.pasbuk.R;
import es.claucookie.pasbuk.helpers.IntentsHelper;
import es.claucookie.pasbuk.helpers.PreferencesHelper;

/**
 * Created by claucookie on 07/04/15.
 */
@EActivity(R.layout.activity_settings)
public class SettingsActivity extends BaseActivity {

    @ViewById
    Toolbar toolBar;
    @ViewById
    TextView versionCodeText;
    @ViewById
    View contactView;
    @ViewById
    View rateView;
    @ViewById
    Spinner dateFormatSpinner;
    @ViewById
    SwitchCompat pastEventsSwitch;

    private String[] dateFormats;
    private String savedDateFormat;
    private boolean savedPastEventsActive;

    @AfterViews
    void initViews() {
        initPastEventsSwitch();
        initDatesSpinner();
        initToolBar();
        initVersionCode();
    }

    private void initToolBar() {
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            toolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onBackPressed();
                }
            });
            getSupportActionBar().setTitle(getString(R.string.settings));
        }
    }

    private void initVersionCode() {
        PackageInfo pInfo = null;
        try {
            pInfo = getPackageManager().getPackageInfo(getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        String version = pInfo != null ? pInfo.versionName : "";
        versionCodeText.setText(version);
    }


    private void initPastEventsSwitch() {
        // Get previous saved value
        savedPastEventsActive = PreferencesHelper.getInstance().getPastDateActive(this);
        updateSwitchUI();
    }

    private void updateSwitchUI() {
        // Init switch
        pastEventsSwitch.setChecked(savedPastEventsActive);
    }


    private void initDatesSpinner() {
        // Get previous date format
        savedDateFormat = PreferencesHelper.getInstance().getDateFormat(this);

        // Init spinner
        dateFormats = getResources().getStringArray(R.array.date_formats_array);

        // Set selected saved format
        int selectedFormatPosition = 0;
        for (int i = 0; i < dateFormats.length; i++) {
            if (savedDateFormat.equals(dateFormats[i])) {
                selectedFormatPosition = i;
                break;
            }
        }
        updateSpinnerUI(selectedFormatPosition);
    }

    private void updateSpinnerUI(int selectedFormatPosition) {
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.date_formatted_array, R.layout.spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_item);
        // Apply the adapter to the spinner
        dateFormatSpinner.setAdapter(adapter);
        dateFormatSpinner.setSelection(selectedFormatPosition);
    }

    @Click
    void contactViewClicked() {
        String[] recipients = new String[1];
        recipients[0] = "claucookielabs@gmail.com";
        String subject = "Pasbuk feedback";
        IntentsHelper.launchEmailIntent(this, recipients, subject);
    }

    @Click
    void rateViewClicked() {
        String pasbukUrl = "https://play.google.com/store/apps/details?id=es.claucookie.pasbuk";
        IntentsHelper.launchExternalUrlWeb(this, pasbukUrl);
    }

    @Override
    public void onBackPressed() {
        boolean somethingChanged = false;
        int selectedDatePosition = dateFormatSpinner.getSelectedItemPosition();
        if (dateFormats.length > selectedDatePosition) {
            String selectedDateFormat = dateFormats[selectedDatePosition];
            // Save this on preferences
            if (!savedDateFormat.equals(selectedDateFormat)) {
                PreferencesHelper.getInstance().saveDateFormat(this, selectedDateFormat);
                somethingChanged = true;
            }
        }
        if (savedPastEventsActive != pastEventsSwitch.isChecked()) {
            PreferencesHelper.getInstance().savePastDateActive(this, pastEventsSwitch.isChecked());
            somethingChanged = true;
        }

        if (somethingChanged) {
            setResult(Activity.RESULT_OK);
        } else {
            setResult(Activity.RESULT_CANCELED);
        }
        finish();
    }

}
