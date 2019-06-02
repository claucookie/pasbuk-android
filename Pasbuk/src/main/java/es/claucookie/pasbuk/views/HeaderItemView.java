package es.claucookie.pasbuk.views;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import es.claucookie.pasbuk.R;
import es.claucookie.pasbuk.model.dto.FieldDictionaryDTO;

/**
 * Created by claucookie on 17/05/15.
 */
public class HeaderItemView extends LinearLayout {

    private TextView headerFieldTitle;
    private TextView headerFieldSubtitle;

    public HeaderItemView(Context context) {
        super(context);
        View view = View.inflate(context, R.layout.view_header_item, this);
        setOrientation(VERTICAL);
        setPadding(
                0,
                0,
                context.getResources().getDimensionPixelSize(R.dimen.default_item_spacing),
                0
        );
        headerFieldTitle = view.findViewById(R.id.header_field_title);
        headerFieldSubtitle = view.findViewById(R.id.header_field_subtitle);
    }

    public void bind(FieldDictionaryDTO fieldDictionaryDTO) {
        String headerLabel = fieldDictionaryDTO.getLabel();
        String headerValue = fieldDictionaryDTO.getValue();

        headerFieldTitle.setText(headerLabel != null ? headerLabel : "");
        if (fieldDictionaryDTO.hasCurrencyInIt()) {
            headerValue = fieldDictionaryDTO.getFormattedCurrency();
        }
        // Check if the field is a date
        if (fieldDictionaryDTO.isATimestamp()) {
            headerValue = fieldDictionaryDTO.getFormattedDate(fieldDictionaryDTO.getValue());
        }
        headerFieldSubtitle.setText(headerValue);
    }


}
