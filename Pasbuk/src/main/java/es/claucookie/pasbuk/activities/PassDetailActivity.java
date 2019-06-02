package es.claucookie.pasbuk.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.getbase.floatingactionbutton.FloatingActionButton;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.OptionsItem;
import org.androidannotations.annotations.OptionsMenu;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import es.claucookie.pasbuk.Consts;
import es.claucookie.pasbuk.R;
import es.claucookie.pasbuk.adapters.PassbookContentPagerAdapter;
import es.claucookie.pasbuk.helpers.PreferencesHelper;
import es.claucookie.pasbuk.logic.PassbookLogic;
import es.claucookie.pasbuk.model.dto.FieldDictionaryDTO;
import es.claucookie.pasbuk.model.dto.PassBookDTO;
import es.claucookie.pasbuk.views.HeaderItemView;
import me.relex.circleindicator.CircleIndicator;

/**
 * Created by claucookie on 28/03/15.
 */
@OptionsMenu(R.menu.menu_pass_detail)
@EActivity(R.layout.activity_pass_detail)
public class PassDetailActivity extends BaseActivity {

    @Extra
    PassBookDTO passBookDTO;

    @ViewById
    View rootView;
    @ViewById
    Toolbar toolBar;
    @ViewById
    ImageView stripImage;
    @ViewById
    ImageView logoImage;
    @ViewById
    TextView organizationName;
    @ViewById
    ViewPager contentPager;
    @ViewById
    CircleIndicator pagerIndicator;
    @ViewById
    FloatingActionButton actionButton;
    @ViewById
    LinearLayout headerFieldsLayout;

    private PassbookContentPagerAdapter pagerAdapter;
    private int currentPage = 0;

    @AfterViews
    void initViews() {
        initToolBar();
        fillWithData();
    }


    @Override
    public void onDestroy() {
        PassbookLogic.getInstance().unregisterReceiver(this);
        super.onDestroy();
    }

    private void initToolBar() {
        setSupportActionBar(toolBar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setHomeButtonEnabled(true);
            toolBar.setNavigationOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (!isFinishing()) {
                        onBackPressed();
                    }
                }
            });
            getSupportActionBar().setTitle("");
        }
    }

    private void fillWithData() {
        if (passBookDTO != null) {
            // Fill upper part of the screen
            fillUpperInfo();
            fillContentInfo();
        }
    }

    private void fillUpperInfo() {
        // Strip (header image)
        if (passBookDTO.getStripImage() != null && !passBookDTO.getStripImage().isEmpty()) {
            ImageLoader.getInstance().displayImage(Consts.FILE_SCHEME + passBookDTO.getStripImage(), stripImage);
        } else if (passBookDTO.getBackgroundImage() != null && !passBookDTO.getBackgroundImage().isEmpty()) {
            ImageLoader.getInstance().displayImage(Consts.FILE_SCHEME + passBookDTO.getBackgroundImage(), stripImage);
        } else {
            if (passBookDTO.isAEventTicket()) {
                ImageLoader.getInstance().displayImage(Consts.DRAWABLE_SCHEME + R.drawable.event_placeholder, stripImage);
            } else if (passBookDTO.isACoupon()) {
                ImageLoader.getInstance().displayImage(Consts.DRAWABLE_SCHEME + R.drawable.coupon_placeholder, stripImage);
            } else if (passBookDTO.isABoardingPass()) {
                ImageLoader.getInstance().displayImage(Consts.DRAWABLE_SCHEME + R.drawable.travel_placeholder, stripImage);
            } else if (passBookDTO.isAStoreCard()) {
                ImageLoader.getInstance().displayImage(Consts.DRAWABLE_SCHEME + R.drawable.store_placeholder, stripImage);
            } else {
                ImageLoader.getInstance().displayImage(Consts.DRAWABLE_SCHEME + R.drawable.generic_placeholder, stripImage);
            }
        }
        // Pass background color
        rootView.setBackgroundColor(passBookDTO.getParsedBgColor());

        // Set logo and name
        if (passBookDTO.getLogoImage() != null && !passBookDTO.getLogoImage().isEmpty()) {
            ImageLoader.getInstance().loadImage(Consts.FILE_SCHEME + passBookDTO.getLogoImage(), new SimpleImageLoadingListener() {
                @Override
                public void onLoadingComplete(String imageUri, View view, Bitmap loadedImage) {
                    logoImage.setImageBitmap(loadedImage);
                    // Tweak for Murillo wedding pass
                    if (passBookDTO.getPassTypeIdentifier().equalsIgnoreCase("pass.com.alvaromurillo.mywedding")) {
                        logoImage.setColorFilter(Color.WHITE);
                    }
                }
            });
            logoImage.setVisibility(View.VISIBLE);
        } else {
            logoImage.setVisibility(View.GONE);
        }
        if (passBookDTO.getLogoText() != null && !passBookDTO.getLogoText().isEmpty()) {
            organizationName.setText(passBookDTO.getLogoText());
            organizationName.setVisibility(View.VISIBLE);
        } else {
            organizationName.setVisibility(View.GONE);
        }

        // Set upper right info
        if (passBookDTO.isAStoreCard() || passBookDTO.isACoupon() || passBookDTO.isGeneric()) {
            // Extract primary fields for store cards and discounts
            List<FieldDictionaryDTO> fields = passBookDTO.getPrimaryFields();

            fillHeaderFields(fields);

        } else if (passBookDTO.isABoardingPass() || passBookDTO.isAEventTicket()) {
            // Extract primary fields for store cards and discounts
            List<FieldDictionaryDTO> fields = passBookDTO.getHeaderFields();

            fillHeaderFields(fields);
        }

        // Set info button properties
        actionButton.setIcon(R.drawable.image_info);
    }

    private void fillHeaderFields(List<FieldDictionaryDTO> fields) {
        headerFieldsLayout.removeAllViews();

        for (FieldDictionaryDTO field : fields) {
            HeaderItemView fieldView = new HeaderItemView(this);
            fieldView.bind(field);
            headerFieldsLayout.addView(fieldView);
        }
    }

    private void fillContentInfo() {
        // Init viewpager and adapter
        pagerAdapter = new PassbookContentPagerAdapter(getSupportFragmentManager(), passBookDTO);
        contentPager.setAdapter(pagerAdapter);
        pagerIndicator.setViewPager(contentPager);
        pagerIndicator.setOnPageChangeListener(new ViewPager.SimpleOnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                currentPage = position;
                switch (position) {
                    case 0:
                        actionButton.setIcon(R.drawable.image_info);
                        break;
                    case 1:
                        actionButton.setIcon(R.drawable.ticket_image);
                        break;
                    case 2:
                        actionButton.setIcon(R.drawable.ic_undo_black);
                        break;
                }
            }
        });
        contentPager.setCurrentItem(currentPage);
    }

    @Click
    void actionButtonClicked() {
        switch (contentPager.getCurrentItem()) {
            case 0:
                contentPager.setCurrentItem(1);
                break;
            case 1:
                contentPager.setCurrentItem(0);
                break;
            case 2:
                contentPager.setCurrentItem(1);
                break;
        }
    }

    @OptionsItem({R.id.delete_pass})
    void deletePassClicked() {

        AlertDialog.Builder dialog = new AlertDialog.Builder(this)
                .setMessage(getString(R.string.delete_pass_message));
        dialog.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                if (PreferencesHelper.getInstance().deletePass(PassDetailActivity.this, passBookDTO)) {
                    setResult(RESULT_OK);
                    finish();
                }
            }
        });
        dialog.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        dialog.show();
    }

    public void updatePassUI(PassBookDTO passBookDTO) {
        this.passBookDTO = passBookDTO;
        fillWithData();
    }

}
