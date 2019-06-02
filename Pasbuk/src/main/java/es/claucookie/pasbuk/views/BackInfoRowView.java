package es.claucookie.pasbuk.views;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import es.claucookie.pasbuk.R;
import es.claucookie.pasbuk.model.dto.FieldDictionaryDTO;

/**
 * Created by claucookie on 29/03/15.
 */
public class BackInfoRowView extends BaseViewHolder<FieldDictionaryDTO> {

    public static int color1 = 0;
    public static int color2 = 0;
    private TextView fieldTitle;
    private TextView fieldSubtitle;

    public BackInfoRowView(Context context, View view) {
        super(context, view);
        fieldTitle = (TextView) view.findViewById(R.id.field_title);
        fieldSubtitle = (TextView) view.findViewById(R.id.field_subtitle);
    }

    @Override
    public void bind(FieldDictionaryDTO field) {
        if (field != null) {
            String fieldValue = field.getValue();

            // Check if the field is a date
            if (field.isATimestamp()) {
                fieldValue = field.getFormattedDate(field.getValue());
            }

            // Check if it is money ammount
            if (field.hasCurrencyInIt()) {
                fieldValue = field.getFormattedCurrency();
            }

            fieldTitle.setText(field.getLabel());
            fieldSubtitle.setText(fieldValue);

            if (color1 != 0) {
                fieldTitle.setTextColor(color1);
            }
            if (color2 != 0) {
                fieldSubtitle.setTextColor(color2);
                fieldSubtitle.setLinkTextColor(color2);
            }
        }
    }
}
