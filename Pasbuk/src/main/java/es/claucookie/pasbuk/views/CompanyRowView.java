package es.claucookie.pasbuk.views;

import android.content.Context;
import android.view.View;
import android.widget.TextView;


import es.claucookie.pasbuk.R;
import es.claucookie.pasbuk.model.dto.PassBookCompanyDTO;

/**
 * Created by claucookie on 31/03/15.
 */
public class CompanyRowView extends BaseViewHolder<PassBookCompanyDTO> {

    private TextView companyCounterText;
    private TextView companyNameText;
    private PassBookCompanyDTO company;

    public CompanyRowView(Context context, View view) {
        super(context, view);
        companyCounterText = (TextView) view.findViewById(R.id.company_counter_text);
        companyNameText = (TextView) view.findViewById(R.id.company_name_text);
    }

    @Override
    public void bind(final PassBookCompanyDTO object) {
        this.company = object;
        if (object != null) {
            if (companyNameText != null) companyNameText.setText(object.getOrganizationName());
            if (companyCounterText != null)
                companyCounterText.setText(String.valueOf(object.getPassbooksCounter()));
        }

        // Setting click event
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListEventListener().onListEvent(0, object, v);
            }
        });

    }
}
