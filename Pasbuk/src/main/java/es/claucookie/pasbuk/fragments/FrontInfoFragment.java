package es.claucookie.pasbuk.fragments;


import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.FragmentArg;
import org.androidannotations.annotations.ViewById;

import java.util.List;

import es.claucookie.pasbuk.Consts;
import es.claucookie.pasbuk.R;
import es.claucookie.pasbuk.activities.BarcodeDetailActivity_;
import es.claucookie.pasbuk.helpers.BarcodeEncoder;
import es.claucookie.pasbuk.helpers.LogHelper;
import es.claucookie.pasbuk.logic.PassbookLogic;
import es.claucookie.pasbuk.model.dto.FieldDictionaryDTO;
import es.claucookie.pasbuk.model.dto.PassBookDTO;
import es.claucookie.pasbuk.views.AuxiliaryFieldView;
import es.claucookie.pasbuk.views.AuxiliaryFieldView_;

/**
 * Created by claucookie on 28/03/15.
 */
@EFragment(R.layout.fragment_front_info)
public class FrontInfoFragment extends Fragment implements PassbookLogic.OnProcessPassbooksListener {

    private static final String TAG = FrontInfoFragment.class.getName();
    @FragmentArg
    PassBookDTO passBookDTO;

    @ViewById
    RelativeLayout boardingHeaderLayout;
    @ViewById
    LinearLayout auxiliaryFieldsLayout;
    @ViewById
    LinearLayout primaryFieldsLayout;
    @ViewById
    LinearLayout secondaryFieldsLayout;
    @ViewById
    CardView barcodeLayout;
    @ViewById
    TextView boardingSrcName;
    @ViewById
    TextView boardingSrcCode;
    @ViewById
    TextView boardingDstName;
    @ViewById
    TextView boardingDstCode;
    @ViewById
    ImageView transportTypeImage;
    @ViewById
    ImageView barcodeImage;
    @ViewById
    TextView barcodeMessage;

    private int color1;
    private int color2;

    @AfterViews
    void initViews() {
        if (isAdded() && passBookDTO != null) {
            color1 = passBookDTO.getParsedTitleColor();
            color2 = passBookDTO.getParsedValueColor();
            // Primary fields
            if (passBookDTO.isABoardingPass()) {
                fillBoardingLayout();
                boardingHeaderLayout.setVisibility(View.VISIBLE);
            } else if (passBookDTO.isAEventTicket()) {
                fillPrimaryFields();
                boardingHeaderLayout.setVisibility(View.GONE);
            } else {
                primaryFieldsLayout.setVisibility(View.GONE);
                boardingHeaderLayout.setVisibility(View.GONE);
            }
            // Auxiliary fields
            fillAuxiliaryFields();
            // Secondary fields
            fillSecondaryFields();
            // Secondary fields
            fillBarcodeLayout();
        }
    }

    private void fillBoardingLayout() {
        // Colors
        boardingSrcName.setTextColor(color1);
        boardingSrcCode.setTextColor(color2);
        boardingDstName.setTextColor(color1);
        boardingDstCode.setTextColor(color2);
        transportTypeImage.setColorFilter(color1);
        // Tinting poi on the left
        Drawable poiDrawable = boardingSrcName.getCompoundDrawables()[0];
        ColorFilter colorFilter = new LightingColorFilter(color1, color1);
        poiDrawable.setColorFilter(colorFilter);

        // Fill depart - arrive info
        String transitImageUri = Consts.ASSETS_URL + passBookDTO.getBoardingPass().getTransitType().toLowerCase() + ".png";
        ImageLoader.getInstance().displayImage(transitImageUri, transportTypeImage);
        List<FieldDictionaryDTO> fields = passBookDTO.getPrimaryFields();

        if (fields != null) {
            if (fields.size() > 0) {
                boardingSrcName.setText(fields.get(0).getLabel());
                boardingSrcCode.setText(passBookDTO.getFirstFieldValue(fields));
            }
            if (fields.size() > 1) {
                boardingDstName.setText(fields.get(1).getLabel());
                boardingDstCode.setText(fields.get(1).getValue());
            }
        }

    }

    private void fillPrimaryFields() {
        List<FieldDictionaryDTO> fields = passBookDTO.getPrimaryFields();

        fillFieldsLayout(fields, primaryFieldsLayout, color1, color2);

    }

    private void fillAuxiliaryFields() {
        List<FieldDictionaryDTO> fields = passBookDTO.getAuxiliaryFields();

        fillFieldsLayout(fields, auxiliaryFieldsLayout, color1, color2);

    }

    private void fillSecondaryFields() {
        List<FieldDictionaryDTO> fields = passBookDTO.getSecondaryFields();

        fillFieldsLayout(fields, secondaryFieldsLayout, color1, color2);

    }

    private void fillFieldsLayout(List<FieldDictionaryDTO> fields, LinearLayout layoutView, int titleColor, int valueColor) {

        if (fields != null && fields.size() > 0) {
            layoutView.setVisibility(View.VISIBLE);

            int fieldsSize = fields.size();
            int counter = 0;
            layoutView.setWeightSum(fieldsSize);

            // Fill linear layout with key values and align them properly
            for (FieldDictionaryDTO field : fields) {
                // Adjust layout params
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT, 1f);
                int gravity = Gravity.LEFT;
                if (counter > 0 && counter >= fieldsSize / 2) {
                    gravity = params.gravity = Gravity.RIGHT;
                }

                // Create and fild view
                AuxiliaryFieldView fieldView = AuxiliaryFieldView_.build(getActivity());
                fieldView.bind(field, titleColor, valueColor, gravity);
                fieldView.setLayoutParams(params);

                layoutView.addView(fieldView);
                counter++;
            }
        } else {
            layoutView.setVisibility(View.GONE);
        }
    }

    private void fillBarcodeLayout() {
        // Display barcode image
        generateAndShowBarcode();
        // Fill barcode message
        if (passBookDTO.getBarcode() != null && !TextUtils.isEmpty(passBookDTO.getBarcode().getAltText())) {
            barcodeMessage.setText(passBookDTO.getBarcode().getAltText());
            barcodeMessage.setVisibility(View.VISIBLE);
        } else {
            barcodeMessage.setVisibility(View.GONE);
        }
    }

    private void generateAndShowBarcode() {
        int barcodeSize = getResources().getDimensionPixelSize(R.dimen.barcode_height);
        BarcodeEncoder barCodeEncoder = new BarcodeEncoder();
        Bitmap qrBitmap = null;
        try {
            qrBitmap = barCodeEncoder.encodeBitmap(passBookDTO.getBarcode().getMessage(), passBookDTO.getBarcodeFormat(), barcodeSize, barcodeSize);
        } catch (WriterException e) {
            e.printStackTrace();
        }
        barcodeImage.setImageBitmap(qrBitmap);
    }

    @Click
    void barcodeImageClicked() {
        BarcodeDetailActivity_.intent(getActivity()).passBookDTO(passBookDTO).flags(Intent.FLAG_ACTIVITY_BROUGHT_TO_FRONT).start();
    }

    @Override
    public void onPassbookContentParsed(PassBookDTO passBookDTO) {
        this.passBookDTO = passBookDTO;

        // Update barcode layout
        fillBarcodeLayout();
    }

    @Override
    public void onPassbookFileNotAvailable() {
        if (isAdded()) {
            LogHelper.logE(TAG, "Passsbook file has some invalid info");
        }
    }

    @Override
    public void onPassbookFileNotValid() {
        if (isAdded()) {
            LogHelper.logE(TAG, "Passsbook file has some invalid info");
        }
    }
}
