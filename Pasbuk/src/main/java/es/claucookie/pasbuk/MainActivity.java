package es.claucookie.pasbuk;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.mobivery.android.helpers.ListEventListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;
import java.util.Date;

import es.claucookie.pasbuk.activities.BaseActivity;
import es.claucookie.pasbuk.activities.DownloadPassbookActivity_;
import es.claucookie.pasbuk.activities.SettingsActivity_;
import es.claucookie.pasbuk.adapters.RecyclerViewBaseAdapter;
import es.claucookie.pasbuk.fragments.AllPassesFragment;
import es.claucookie.pasbuk.fragments.AllPassesFragment_;
import es.claucookie.pasbuk.fragments.ArchivePassesFragment;
import es.claucookie.pasbuk.fragments.ArchivePassesFragment_;
import es.claucookie.pasbuk.fragments.CompanyPassesFragment;
import es.claucookie.pasbuk.fragments.CompanyPassesFragment_;
import es.claucookie.pasbuk.helpers.PreferencesHelper;
import es.claucookie.pasbuk.logic.PassbookLogic;
import es.claucookie.pasbuk.model.dto.PassBookCompaniesDTO;
import es.claucookie.pasbuk.model.dto.PassBookCompanyDTO;
import es.claucookie.pasbuk.model.dto.PassBookDTO;
import es.claucookie.pasbuk.model.dto.PassBooksDTO;
import es.claucookie.pasbuk.views.CompanyRowView;

@EActivity(R.layout.activity_main)
public class MainActivity extends BaseActivity implements ListEventListener<PassBookCompanyDTO> {

    @ViewById
    Toolbar toolBar;
    @ViewById
    RecyclerView menuItemsList;
    @ViewById
    LinearLayout leftDrawer;
    @ViewById
    DrawerLayout drawerLayout;
    @ViewById
    FrameLayout contentFrame;
    @ViewById
    View settingsButton;
    @ViewById
    View archiveButton;

    private ActionBarDrawerToggle drawerToggle;
    private Fragment currentFragment;
    private boolean showArchiveButton = false;

    @AfterViews
    void initViews() {
        initToolBar();
        initNavigationDrawer();
        checkArchiveButton();
        initLeftDrawer();
        initContent();
    }

    /**
     * Init methods
     */

    private void initLeftDrawer() {
        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        menuItemsList.setHasFixedSize(true);

        // Now with Recycler view needs a LinearLayoutManager
        // Init recycler view to avoid onmeasure errors
        // http://stackoverflow.com/questions/27416834/app-crashing-when-trying-to-use-recyclerview-on-android-5-0
        RecyclerView.LayoutManager recyclerViewLayoutManager = new LinearLayoutManager(this);
        menuItemsList.setLayoutManager(recyclerViewLayoutManager);


        PassBooksDTO allPassesDTO = PreferencesHelper.getInstance().getSavedPasses(this);
        // If needed avoid showing archived passes and companies with no archived passes
        ArrayList<PassBookDTO> currentPasses = getPassesWithoutArchivedOnesIfNeeded((ArrayList<PassBookDTO>) allPassesDTO.getPasses());
        allPassesDTO.setPasses(currentPasses);
        PassBookCompaniesDTO companiesDTO = PreferencesHelper.getInstance().getCurrentCompanies(currentPasses);

        // Return corresponding passes companies and another company to group them all.
        int allPassesCount = allPassesDTO.getPasses() != null ? allPassesDTO.getPasses().size() : 0;
        PassBookCompanyDTO companyForAllPasses = new PassBookCompanyDTO();
        companyForAllPasses.setOrganizationName(getString(R.string.all_companies));
        companyForAllPasses.setPassbooksCounter(allPassesCount);
        companyForAllPasses.setTeamIdentifier("");
        // Load companies menu content
        if (companiesDTO != null && companiesDTO.getCompanies() != null) {
            companiesDTO.getCompanies().add(0, companyForAllPasses);
            if (companiesDTO.getCompanies().size() > 0) {
                RecyclerViewBaseAdapter<PassBookCompanyDTO, CompanyRowView> recyclerViewAdapter = new RecyclerViewBaseAdapter<PassBookCompanyDTO, CompanyRowView>(PassBookCompanyDTO.class, CompanyRowView.class, R.layout.view_company_row, companiesDTO.getCompanies());
                recyclerViewAdapter.setListEventListener(this);
                menuItemsList.setAdapter(recyclerViewAdapter);
            }
        } else {
            companiesDTO = new PassBookCompaniesDTO();
            companiesDTO.setCompanies(new ArrayList<PassBookCompanyDTO>());
            companiesDTO.getCompanies().add(0, companyForAllPasses);
            if (companiesDTO.getCompanies().size() > 0) {
                RecyclerViewBaseAdapter<PassBookCompanyDTO, CompanyRowView> recyclerViewAdapter = new RecyclerViewBaseAdapter<PassBookCompanyDTO, CompanyRowView>(PassBookCompanyDTO.class, CompanyRowView.class, R.layout.view_company_row, companiesDTO.getCompanies());
                recyclerViewAdapter.setListEventListener(this);
                menuItemsList.setAdapter(recyclerViewAdapter);
            }
        }
    }

    private void initToolBar() {
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
        }
    }

    private void initNavigationDrawer() {
        drawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolBar, R.string.app_name, R.string.app_name);
        drawerToggle.syncState();                     // Finally we set the drawer toggle sync State
    }

    private void initContent() {
        loadAllPassesFragment();
    }

    /**
     * Check and load methods
     */

    private void loadCompanyPassesFragment(PassBookCompanyDTO company) {
        currentFragment = CompanyPassesFragment_.builder().currentSelectedCompany(company).build();
        replaceCurrentFragment();
    }

    private void loadAllPassesFragment() {
        currentFragment = AllPassesFragment_.builder().build();
        replaceCurrentFragment();
    }

    private void loadArchivedPassesFragment() {
        currentFragment = ArchivePassesFragment_.builder().build();
        replaceCurrentFragment();
    }

    private void replaceCurrentFragment() {
        getSupportFragmentManager().beginTransaction().replace(contentFrame.getId(), currentFragment).commit();
    }

    private void checkArchiveButton() {
        // Check archive button
        showArchiveButton = PreferencesHelper.getInstance().getPastDateActive(this);
        updateArchiveButton(showArchiveButton);

    }

    private void updateArchiveButton(boolean showArchiveButton) {
        archiveButton.setVisibility(showArchiveButton ? View.VISIBLE : View.GONE);
    }

    private ArrayList<PassBookDTO> getPassesWithoutArchivedOnesIfNeeded(ArrayList<PassBookDTO> passes) {
        ArrayList<PassBookDTO> currentPasses = passes;

        // Archive functionality is active
        if (PreferencesHelper.getInstance().getPastDateActive(this)) {
            currentPasses = new ArrayList<>();
            long currentDatetime = (new Date()).getTime();
            for (PassBookDTO pass : passes) {
                long passRelevantDatetime = pass.getRelevantDatetime();
                // If the pass date has not passed
                if (currentDatetime - passRelevantDatetime < Consts.A_DAY_IN_MILLIS) {
                    currentPasses.add(pass);
                }
            }
        }
        return currentPasses;
    }

    /**
     * Show / hide methods
     */

    @UiThread
    void showToast(int stringId) {
        Toast.makeText(this, stringId, Toast.LENGTH_LONG).show();
    }

    /**
     * Click methods
     */

    @Click
    void settingsButtonClicked() {
        drawerLayout.closeDrawer(Gravity.LEFT);
        SettingsActivity_.intent(MainActivity.this).startForResult(Consts.SETTINGS_INTENT_REQUEST);
    }

    @Click
    void archiveButtonClicked() {
        drawerLayout.closeDrawer(Gravity.LEFT);
        loadArchivedPassesFragment();
    }


    /**
     * Listeners & callbacks
     */

    @Override
    public void onListEvent(int i, final PassBookCompanyDTO passBookCompanyDTO, View view) {
        drawerLayout.closeDrawer(Gravity.LEFT);
        // Load fragment
        if (passBookCompanyDTO != null && !TextUtils.isEmpty(passBookCompanyDTO.getTeamIdentifier())) {
            loadCompanyPassesFragment(passBookCompanyDTO);
        } else {
            loadAllPassesFragment();
        }
    }

    public void updateMenuList() {
        initLeftDrawer();
    }

    public void updateToolbarTitle(String organizationName) {
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(organizationName);
        }
    }

    @OnActivityResult(Consts.SETTINGS_INTENT_REQUEST)
    void onResult(int resultCode) {
        if (resultCode == Activity.RESULT_OK) {
            checkArchiveButton();
            // Load fragment
            if (currentFragment instanceof AllPassesFragment) {
                ((AllPassesFragment) currentFragment).updatePasses();
            } else if (currentFragment instanceof CompanyPassesFragment) {
                ((CompanyPassesFragment) currentFragment).updatePasses();
            } else if (currentFragment instanceof ArchivePassesFragment) {
                if (!showArchiveButton) {
                    loadAllPassesFragment();
                } else {
                    ((ArchivePassesFragment) currentFragment).updatePasses();
                }
            }
            updateMenuList();
        }
    }

    @OnActivityResult(Consts.OPEN_FILE_REQUEST_CODE)
    void onResult(int resultCode, Intent resultData) {
        if (resultCode == Activity.RESULT_OK) {
            Uri dataUri = resultData != null ? resultData.getData() : null;
            if (dataUri != null) {
                DownloadPassbookActivity_.intent(this).pkpassUriExtra(dataUri).start();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case Consts.MY_PERMISSIONS_REQUEST_STORAGE: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {

                    PassbookLogic.getInstance().performFileSearch(this);
                }
                break;
            }
        }
    }
}
