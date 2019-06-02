package es.claucookie.pasbuk.fragments;

import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Background;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.UiThread;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import es.claucookie.pasbuk.R;
import es.claucookie.pasbuk.activities.PassDetailActivity;
import es.claucookie.pasbuk.adapters.RecyclerViewBaseAdapter;
import es.claucookie.pasbuk.helpers.ConnectionHelper;
import es.claucookie.pasbuk.helpers.LogHelper;
import es.claucookie.pasbuk.helpers.PreferencesHelper;
import es.claucookie.pasbuk.logic.PassbookLogic;
import es.claucookie.pasbuk.model.dto.FieldDictionaryDTO;
import es.claucookie.pasbuk.model.dto.PassBookDTO;
import es.claucookie.pasbuk.views.BackInfoRowView;

/**
 * Created by claucookie on 29/03/15.
 */
@EFragment(R.layout.fragment_back_info)
public class BackInfoFragment extends Fragment implements SwipeRefreshLayout.OnRefreshListener, PassbookLogic.OnProcessPassbooksListener {

    private static final String TAG = BackInfoFragment.class.getName();
    @FragmentArg
    PassBookDTO passBookDTO;

    @ViewById
    RecyclerView backinfoList;
    @ViewById
    SwipeRefreshLayout swipeToRefreshLayout;

    private RecyclerViewBaseAdapter<FieldDictionaryDTO, BackInfoRowView> recyclerViewAdapter;
    private RecyclerView.LayoutManager recyclerViewLayoutManager;
    private int color1 = 0;
    private int color2 = 0;

    @AfterViews
    void initViews() {
        if (isAdded() && passBookDTO != null) {
            color1 = passBookDTO.getParsedTitleColor();
            color2 = passBookDTO.getParsedValueColor();
            initSwipeToRefreshLayout();
            initList();
        }
    }

    private void initSwipeToRefreshLayout() {
        swipeToRefreshLayout.setColorSchemeColors(color1);
        swipeToRefreshLayout.setOnRefreshListener(this);
    }

    private void initList() {
        // Use this setting to improve performance if you know that changes
        // in content do not change the layout size of the RecyclerView
        backinfoList.setHasFixedSize(true);

        // Now with Recycler view needs a LinearLayoutManager
        recyclerViewLayoutManager = new LinearLayoutManager(getActivity());
        backinfoList.setLayoutManager(recyclerViewLayoutManager);

        List<FieldDictionaryDTO> fields = passBookDTO.getBackFields();
        BackInfoRowView.color1 = color1;
        BackInfoRowView.color2 = color2;
        backinfoList.setBackgroundColor(passBookDTO.getParsedBgColor());
        // Create and set Recycler adapter
        recyclerViewAdapter = new RecyclerViewBaseAdapter<FieldDictionaryDTO, BackInfoRowView>(FieldDictionaryDTO.class, BackInfoRowView.class, R.layout.view_back_info_row, fields);
        backinfoList.setAdapter(recyclerViewAdapter);
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