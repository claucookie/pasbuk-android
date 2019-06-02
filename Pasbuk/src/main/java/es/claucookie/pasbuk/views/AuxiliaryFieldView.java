package es.claucookie.pasbuk.views;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;
import android.widget.TextView;


import org.androidannotations.annotations.EViewGroup;
import org.androidannotations.annotations.ViewById;

import java.io.UnsupportedEncodingException;

import es.claucookie.pasbuk.R;
import es.claucookie.pasbuk.model.dto.FieldDictionaryDTO;

/**
 * Created by claucookie on 29/03/15.
 */
@EViewGroup(R.layout.view_auxiliary_field)
public class AuxiliaryFieldView extends LinearLayout {

    @ViewById
    TextView fieldTitle;
    @ViewById
    TextView fieldSubtitle;

    public AuxiliaryFieldView(Context context) {
        super(context);
    }

    public AuxiliaryFieldView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public AuxiliaryFieldView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void bind(FieldDictionaryDTO field, int color1, int color2, int gravity) {
        fieldTitle.setGravity(gravity);
        fieldSubtitle.setGravity(gravity);
        fieldTitle.setText(field.getLabel());

        String fieldValue = field.getValue();
        // Check if the field is a date
        if (field.isATimestamp()) {
            fieldValue = field.getFormattedDate(field.getValue());
        }
        // Check if it is money ammount
        if (field.hasCurrencyInIt()) {
            fieldValue = field.getFormattedCurrency();
        }
        fieldSubtitle.setText(fieldValue);
        fieldTitle.setTextColor(color1);
        fieldSubtitle.setTextColor(color2);
    }
}
