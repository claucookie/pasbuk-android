package es.claucookie.pasbuk.views;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import es.claucookie.pasbuk.R;
import es.claucookie.pasbuk.helpers.PreferencesHelper;
import es.claucookie.pasbuk.model.dto.FieldDictionaryDTO;

/**
 * Created by claucookie on 17/05/15.
 */
public class PassBookRowHeaderItemView extends LinearLayout {

    private TextView headerFieldLabel;
    private TextView headerFieldValue;


    public PassBookRowHeaderItemView(Context context) {
        super(context);
        View view = View.inflate(context, R.layout.view_pass_book_row_header_item, this);
        setOrientation(VERTICAL);
        setPadding(
                0,
                context.getResources().getDimensionPixelSize(R.dimen.fab_actions_spacing),
                context.getResources().getDimensionPixelSize(R.dimen.fab_actions_spacing),
                0
        );
        headerFieldLabel = view.findViewById(R.id.header_field_label);
        headerFieldValue = view.findViewById(R.id.header_field_value);
    }

    public void bind(FieldDictionaryDTO fieldDictionaryDTO) {
        String headerLabel = fieldDictionaryDTO.getLabel();
        String headerValue = fieldDictionaryDTO.getValue();

        if (headerLabel != null && headerValue != null
                && headerLabel.isEmpty() && headerValue.isEmpty()) {
            headerFieldLabel.setVisibility(View.GONE);
            headerFieldValue.setVisibility(View.GONE);
        } else {

            if (fieldDictionaryDTO.hasCurrencyInIt()) {
                headerValue = fieldDictionaryDTO.getFormattedCurrency();
            }
            if (fieldDictionaryDTO.isATimestamp()) {
                String humanFormat = PreferencesHelper.getInstance().getDateFormat(getContext());
                headerValue = fieldDictionaryDTO.getFormattedDate(headerValue, humanFormat);
            }
            headerFieldLabel.setText(headerLabel);
            headerFieldValue.setText(headerValue);
            headerFieldLabel.setVisibility(View.VISIBLE);
            headerFieldValue.setVisibility(View.VISIBLE);

        }
    }

    public void setColors(int color1, int color2) {
        headerFieldLabel.setTextColor(color1);
        headerFieldValue.setTextColor(color2);
    }
}
