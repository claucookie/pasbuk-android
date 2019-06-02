package es.claucookie.pasbuk.fragments;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.Typeface;
import android.graphics.drawable.Drawable;
import android.provider.CalendarContract;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.SwitchCompat;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.crashlytics.android.Crashlytics;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.Calendar;

import es.claucookie.pasbuk.R;
import es.claucookie.pasbuk.activities.PassDetailActivity;
import es.claucookie.pasbuk.helpers.ConnectionHelper;
import es.claucookie.pasbuk.helpers.LogHelper;
import es.claucookie.pasbuk.helpers.PreferencesHelper;
import es.claucookie.pasbuk.logic.PassbookLogic;
import es.claucookie.pasbuk.model.dto.PassBookDTO;
import es.claucookie.pasbuk.model.dto.PassBookLocationDTO;

/**
 * Created by claucookie on 12/04/15.
 */
@EFragment(R.layout.fragment_pass_settings)
public class PassSettingsFragment extends Fragment implements OnMapReadyCallback, SwipeRefreshLayout.OnRefreshListener, PassbookLogic.OnProcessPassbooksListener {

    private static final String TAG = PassSettingsFragment.class.getName();

    @FragmentArg
    PassBookDTO passBookDTO;

    @ViewById
    SwipeRefreshLayout swipeToRefreshLayout;
    @ViewById
    TextView updatedPassTitle;
    @ViewById
    TextView updatedPassSubtitle;
    @ViewById
    ImageView mapForegroundView;
    @ViewById
    RelativeLayout mapLayout;
    @ViewById
    Button addEventBtn;
    @ViewById
    SwitchCompat pushNotifSwitch;

    private SupportMapFragment mapFragment;
    private GoogleMap googleMap;
    private int color1 = 0;
    private int color2 = 0;
    private int bgColor = 0;
    private int addColor = 0;
    private int addColor2 = 0;
    private int switchTrackColor = 0;

    @AfterViews
    void initViews() {
        if (isAdded() && passBookDTO != null) {
            color1 = passBookDTO.getParsedTitleColor();
            color2 = passBookDTO.getParsedValueColor();
            bgColor = passBookDTO.getParsedBgColor();
            addColor = getResources().getColor(R.color.filter_add_color);
            addColor2 = getResources().getColor(R.color.filter_add_color2);
            switchTrackColor = getResources().getColor(R.color.switch_track_color);
            initSwipeToRefreshLayout();
            initSettingsView();
            initMap();
        }
    }

    private void initSettingsView() {
        updatedPassTitle.setTextColor(color1);
        updatedPassSubtitle.setTextColor(color2);
        if (passBookDTO.getUpdateDate() != null) {
            updatedPassSubtitle.setText(passBookDTO.getFormattedUpdateDate());
        }
        mapForegroundView.setColorFilter(bgColor);

        // Tinting update icon on the left
        Drawable updateDrawable = updatedPassSubtitle.getCompoundDrawables()[0];
        final ColorFilter colorFilter2 = new LightingColorFilter(color2, color2);
        updateDrawable.setColorFilter(colorFilter2);

        // Tinting event calendar button
        Drawable backgroundEventDrawable = addEventBtn.getBackground();
        backgroundEventDrawable.setColorFilter(colorFilter2);
        addEventBtn.setTextColor(bgColor);

        // Init switch
//        Typeface typeface = FontFactory.getInstance().getTypeface(getActivity(), "Roboto-Light");
//        pushNotifSwitch.setTypeface(typeface);
        pushNotifSwitch.setTextColor(color2);
        final ColorFilter colorFilterEnabled = new LightingColorFilter(color2, addColor);
        final ColorFilter colorFilterTrackEnabled = new LightingColorFilter(color2, addColor2);
        final ColorFilter colorFilterDisabled = new LightingColorFilter(bgColor, addColor);
        final ColorFilter colorFilterTrackDisabled = new LightingColorFilter(switchTrackColor, addColor);

        pushNotifSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    pushNotifSwitch.getTrackDrawable().setColorFilter(colorFilterTrackEnabled);
                    pushNotifSwitch.getThumbDrawable().setColorFilter(colorFilterEnabled);
                } else {
                    pushNotifSwitch.getTrackDrawable().setColorFilter(colorFilterTrackDisabled);
                    pushNotifSwitch.getThumbDrawable().setColorFilter(colorFilterDisabled);
                }
            }
        });
        pushNotifSwitch.setChecked(true);
    }

    private void initSwipeToRefreshLayout() {
        swipeToRefreshLayout.setColorSchemeColors(passBookDTO.getParsedTitleColor());
        swipeToRefreshLayout.setOnRefreshListener(this);
    }

    public void initMap() {
        if (passBookDTO.getLocations() != null && passBookDTO.getLocations().size() > 0) {
            mapLayout.setVisibility(View.VISIBLE);
            if (googleMap == null && isAdded()) {
                if (GooglePlayServicesUtil.isGooglePlayServicesAvailable(getActivity()) == ConnectionResult.SUCCESS) {
                    mapFragment = (SupportMapFragment) getChildFragmentManager()
                            .findFragmentById(R.id.map_fragment);
                    if (mapFragment != null) {
                        mapFragment.getMapAsync(this);
                    }
                }
            }
        } else {
            mapLayout.setVisibility(View.INVISIBLE);
        }
    }


    @UiThread
    void stopLoading(boolean success) {
        swipeToRefreshLayout.setRefreshing(false);
        if (!success) {
            if (isAdded()) {
                Toast.makeText(getActivity(), R.string.pass_not_updated_text, Toast.LENGTH_LONG).show();
            }
        }
    }


    @Background
    void checkServerReachable() {
        if (ConnectionHelper.serverExists(passBookDTO.getUpdatePassUri())) {
            PassbookLogic.getInstance().downloadUpdatedPassbook(getActivity(), passBookDTO, this);
        } else {
            stopLoading(false);
        }
    }

    /**
     * Click methods
     */

    @Click
    void addEventBtnClicked() {
        Calendar beginTime = Calendar.getInstance();
        long relevantDateInMillis = passBookDTO.getRelevantDatetime();
        beginTime.setTimeInMillis(relevantDateInMillis);
        Calendar endTime = Calendar.getInstance();
        endTime.setTimeInMillis(relevantDateInMillis + 3600 * 1000); // One hour later
        String titleSuffix = passBookDTO.getHeaderFields() != null ? passBookDTO.getFirstFieldValue(passBookDTO.getHeaderFields()) : "";
        String titlePreffix = passBookDTO.getHeaderFields() != null ? passBookDTO.getFirstFieldLabel(passBookDTO.getHeaderFields()) : passBookDTO.getDescription();
        try {
            Intent intent = new Intent(Intent.ACTION_INSERT)
                    .setData(CalendarContract.Events.CONTENT_URI)
                    .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                    .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                    .putExtra(CalendarContract.Events.TITLE, titlePreffix + " " + titleSuffix)
                    .putExtra(CalendarContract.Events.EVENT_COLOR, bgColor)
                    .putExtra(CalendarContract.Events.DESCRIPTION, passBookDTO.getOrganizationName());
            startActivity(intent);
        } catch (ActivityNotFoundException ex) {
            try {
                Intent intent = new Intent(Intent.ACTION_EDIT)
                        .putExtra(CalendarContract.EXTRA_EVENT_BEGIN_TIME, beginTime.getTimeInMillis())
                        .putExtra(CalendarContract.EXTRA_EVENT_END_TIME, endTime.getTimeInMillis())
                        .putExtra(CalendarContract.Events.TITLE, titlePreffix + " " + titleSuffix)
                        .putExtra(CalendarContract.Events.EVENT_COLOR, bgColor)
                        .putExtra(CalendarContract.Events.DESCRIPTION, passBookDTO.getOrganizationName());
                intent.setType("vnd.android.cursor.item/event");
                startActivity(intent);
            } catch (ActivityNotFoundException ex2) {
                Crashlytics.logException(ex2);
            }
        }
    }

    /**
     * Listeners
     */

    @Override
    public void onMapReady(GoogleMap googleMap) {

        if (googleMap != null) {
            this.googleMap = googleMap;
            if (passBookDTO != null && passBookDTO.getLocations() != null && passBookDTO.getLocations().size() > 0) {
                PassBookLocationDTO location = passBookDTO.getLocations().get(0);
                LatLng eventLatLng = new LatLng(location.getLatitude(), location.getLongitude());
                // Move camera
                googleMap.moveCamera(CameraUpdateFactory.newLatLng(eventLatLng));
                // Clear map
                googleMap.clear();
                // Create marker
                googleMap.addMarker(new MarkerOptions()
                        .position(eventLatLng)
                        .icon(BitmapDescriptorFactory.fromResource(R.drawable.map_poi))
                        .title(location.getRelevantText() != null ? location.getRelevantText() : passBookDTO.getOrganizationName()));

            }

        }
    }


    /**
     * Listeners
     */

    @Override
    public void onRefresh() {
        if (passBookDTO.getWebServiceURL() != null) {
            checkServerReachable();
        } else {
            stopLoading(false);
        }
    }

    @Override
    public void onPassbookContentParsed(PassBookDTO passBookDTO) {
        if (isAdded()) {
            LogHelper.logD(TAG, "Passbook received!!!!");
            PreferencesHelper.getInstance().savePassBook(getActivity(), passBookDTO);
            stopLoading(true);
            ((PassDetailActivity) getActivity()).updatePassUI(passBookDTO);
        }
    }

    @Override
    public void onPassbookFileNotAvailable() {
        if (isAdded()) {
            LogHelper.logE(TAG, "Passsbook file not available anymore");
            showToast(R.string.pass_file_not_available_anymore);
            stopLoading(false);
        }
    }

    @Override
    public void onPassbookFileNotValid() {
        if (isAdded()) {
            LogHelper.logE(TAG, "Passsbook file has some invalid info");
            showToast(R.string.pass_file_not_available_anymore);
            stopLoading(false);
        }
    }

    @UiThread
    void showToast(int stringId) {
        if (isAdded()) {
            Toast.makeText(getActivity(), stringId, Toast.LENGTH_LONG).show();
        }
    }
}
