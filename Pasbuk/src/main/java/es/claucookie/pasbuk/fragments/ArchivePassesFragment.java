package es.claucookie.pasbuk.fragments;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Toast;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.getbase.floatingactionbutton.FloatingActionsMenu;
import com.mobivery.android.helpers.ListEventListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.OnActivityResult;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.ArrayList;

import es.claucookie.pasbuk.Consts;
import es.claucookie.pasbuk.MainActivity;
import es.claucookie.pasbuk.R;
import es.claucookie.pasbuk.activities.PassDetailActivity_;
import es.claucookie.pasbuk.adapters.RecyclerViewBaseAdapter;
import es.claucookie.pasbuk.helpers.PermissionsHelper;
import es.claucookie.pasbuk.helpers.PreferencesHelper;
import es.claucookie.pasbuk.logic.PassbookLogic;
import es.claucookie.pasbuk.model.dto.PassBookDTO;
import es.claucookie.pasbuk.views.PassBookRowView;

/**
 * Created by claucookie on 09/05/15.
 */
@EFragment(R.layout.fragment_all_passes)
public class ArchivePassesFragment extends Fragment implements ListEventListener<PassBookDTO> {


    private static final String TAG = ArchivePassesFragment.class.getName();

    @ViewById
    RecyclerView passesList;
    @ViewById
    View emptyView;
    @ViewById
    FloatingActionsMenu floatingActionsMenu;
    @ViewById
    FloatingActionButton floatingActionDocuments;

    private RecyclerViewBaseAdapter<PassBookDTO, PassBookRowView> recyclerViewAdapter;
    private ArrayList<PassBookDTO> passes;
    private boolean hidden = false;
    private int floatCy = 0;

    RecyclerView.OnScrollListener scrollListener = new RecyclerView.OnScrollListener() {
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

            if (dy > 0 && !hidden) {
                hideFloatingButton();
            } else if (dy < 0 && hidden) {
                showFloatingButton();
            }

        }
    };

    @AfterViews
    void initViews() {
        if (isAdded()) {
            setupPassesList();
            initPassesList();
            initFabPosition();
            checkSavedOrderPreferences();
        }
    }

    /**
     * Init methods
     */

    private void setupPassesList() {
        // IMPORTANT: this setting must be done at the begining
        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        passesList.setHasFixedSize(true);

        // Now with Recycler view needs a LinearLayoutManager
        passesList.setLayoutManager(new LinearLayoutManager(getActivity()));
        // Added scroll listener
        passesList.setOnScrollListener(scrollListener);
    }

    private void initFabPosition() {
        floatingActionsMenu.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                if (floatCy == 0) {
                    floatCy = floatingActionsMenu.getTop();
                    floatingActionsMenu.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            }
        });
    }


    public void initPassesList() {
        updateTitle();
        passes = (ArrayList<PassBookDTO>) PreferencesHelper.getInstance().getSavedPasses(getActivity()).getPasses();
        passes = PreferencesHelper.getInstance().getArchivedPasses(getActivity(), passes);
    }

    /**
     * Check and load methods
     */

    private void updateTitle() {
        if (isAdded()) {
            ((MainActivity) getActivity()).updateToolbarTitle(getString(R.string.archive));
        }
    }

    private void checkSavedOrderPreferences() {

        String sortType = PreferencesHelper.getInstance().getSortType(getActivity());
        if (sortType.equals(PreferencesHelper.Sort.RELEVANT_DATE.name())) {
            sortByRelevantDate();
        } else if (sortType.equals(PreferencesHelper.Sort.UPDATE_DATE.name())) {
            sortByDateAdded();
        } else {
            sortByNone();
        }
    }

    private void reloadPasses() {
        if (passes != null && passes.size() > 0) {
            emptyView.setVisibility(View.GONE);
            passesList.setVisibility(View.VISIBLE);
            // Create and set Recycler adapter
            if (recyclerViewAdapter == null) {
                recyclerViewAdapter = new RecyclerViewBaseAdapter<PassBookDTO, PassBookRowView>(PassBookDTO.class, PassBookRowView.class, R.layout.view_passbook_row, passes);
                recyclerViewAdapter.setListEventListener(this);
                passesList.setAdapter(recyclerViewAdapter);
            } else {
                recyclerViewAdapter.setItems(passes);
            }

            if (hidden) {
                showFloatingButton();
            }
        } else {
            emptyView.setVisibility(View.VISIBLE);
            passesList.setVisibility(View.GONE);
        }
    }

    public void updatePasses() {
        // Reset passes in case previous state was Archived = true and it gets changed to false
        initPassesList();
        checkSavedOrderPreferences();
    }

    void sortByNone() {
        if (passes == null) {
            passes = (ArrayList<PassBookDTO>) PreferencesHelper.getInstance().getSavedPasses(getActivity()).getPasses();
        }
        reloadPasses();
        PreferencesHelper.getInstance().saveSelectedSort(getActivity(), PreferencesHelper.Sort.NONE);
    }


    /**
     * Show hide methods
     */

    private void showFloatingButton() {
        hidden = false;
        // Show
        ObjectAnimator mover = ObjectAnimator.ofFloat(floatingActionsMenu, "translationY", 0);
        mover.setDuration(500);
        mover.setInterpolator(new AccelerateDecelerateInterpolator());
        mover.start();
    }

    private void hideFloatingButton() {
        floatingActionsMenu.collapse();
        hidden = true;
        // Hide
        ObjectAnimator mover = ObjectAnimator.ofFloat(floatingActionsMenu, "translationY", floatCy);
        mover.setDuration(500);
        mover.setInterpolator(new AccelerateDecelerateInterpolator());
        mover.start();
    }


    /**
     * Click methods
     */

    @Click
    void floatingActionDocuments() {
        if (PermissionsHelper.isStoragePermissionGranted(getActivity())) {
            PassbookLogic.getInstance().performFileSearch(getActivity());
            floatingActionsMenu.collapse();
        } else {
            PermissionsHelper.checkStoragePermissions(getActivity());
        }
    }

    @OptionsItem(R.id.sort_by_date_added)
    void sortByDateAdded() {
        if (passes == null) {
            passes = (ArrayList<PassBookDTO>) PreferencesHelper.getInstance().getSavedPasses(getActivity()).getPasses();
        }
        PassbookLogic.getInstance().sortPassesByUpdateDate(passes);
        reloadPasses();
        PreferencesHelper.getInstance().saveSelectedSort(getActivity(), PreferencesHelper.Sort.UPDATE_DATE);
    }

    @OptionsItem(R.id.sort_by_relevant_date)
    void sortByRelevantDate() {
        if (passes == null) {
            passes = (ArrayList<PassBookDTO>) PreferencesHelper.getInstance().getSavedPasses(getActivity()).getPasses();
        }
        PassbookLogic.getInstance().sortPassesByRelevantDate(passes);
        reloadPasses();
        PreferencesHelper.getInstance().saveSelectedSort(getActivity(), PreferencesHelper.Sort.RELEVANT_DATE);
    }

    /**
     * Listeners and callbacks
     */

    @Override
    public void onListEvent(int i, PassBookDTO passBookDTO, View view) {
        PassDetailActivity_.intent(this).passBookDTO(passBookDTO).startForResult(Consts.DELETE_PASS_REQUEST_CODE);
    }

    @OnActivityResult(Consts.DELETE_PASS_REQUEST_CODE)
    protected void onResult(int resultCode) {
        if (resultCode == Activity.RESULT_OK) {
            Toast.makeText(getActivity(), R.string.deleted_pass_alert, Toast.LENGTH_LONG).show();
            // Refresh passes
            initPassesList();
            // Check if sorting was clicked last time
            checkSavedOrderPreferences();
            // Update left menu
            if (isAdded()) {
                ((MainActivity) getActivity()).updateMenuList();
            }
        }
    }
}
