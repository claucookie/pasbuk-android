package es.claucookie.pasbuk.activities;

import android.graphics.Bitmap;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.zxing.WriterException;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.Extra;
import org.androidannotations.annotations.ViewById;

import es.claucookie.pasbuk.R;
import es.claucookie.pasbuk.helpers.BarcodeEncoder;
import es.claucookie.pasbuk.model.dto.PassBookDTO;

/**
 * Created by claucookie on 29/03/15.
 */
@EActivity(R.layout.activity_barcode_detail)
public class BarcodeDetailActivity extends BaseActivity {

    @Extra
    PassBookDTO passBookDTO;

    @ViewById
    ImageView barcodeImage;

    @AfterViews
    void initViews() {
        if (passBookDTO != null) {
            showBarcode();
        } else {
            Toast.makeText(this, R.string.pass_does_not_exist_alert, Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    private void showBarcode() {
        generateAndShowQR();
    }

    private void generateAndShowQR() {
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
}
