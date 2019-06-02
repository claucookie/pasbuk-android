package es.claucookie.pasbuk.views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BlurMaskFilter;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;

import java.util.List;

import es.claucookie.pasbuk.Consts;
import es.claucookie.pasbuk.R;
import es.claucookie.pasbuk.model.dto.FieldDictionaryDTO;
import es.claucookie.pasbuk.model.dto.PassBookDTO;

/**
 * Created by claucookie on 20/03/15.
 */
public class PassBookRowView extends BaseViewHolder<PassBookDTO> {

    public TextView organizationName;
    private TextView relevantDate;
    private View eventTicketMark;
    public ImageView logoImage;
    private Context context;
    private LinearLayout headerFieldsLayout;


    private int color1 = 0;
    private int color2 = 0;

    public PassBookRowView(Context context, View view) {
        super(context, view);
        this.context = context;
        organizationName = (TextView) view.findViewById(R.id.organization_name);
        relevantDate = (TextView) view.findViewById(R.id.relevant_date);
        headerFieldsLayout = (LinearLayout) view.findViewById(R.id.header_fields_layout);
        eventTicketMark = view.findViewById(R.id.event_ticket_mark);
        logoImage = (ImageView) view.findViewById(R.id.logo_image);
    }

    @Override
    public void bind(final PassBookDTO pass) {

        color1 = pass.getParsedTitleColor();
        color2 = pass.getParsedValueColor();

        // Set logo
        if (pass.getLogoImage() != null && !pass.getLogoImage().isEmpty()) {
            logoImage.setVisibility(View.VISIBLE);
            ImageLoader.getInstance().displayImage(Consts.FILE_SCHEME + pass.getLogoImage(), logoImage);
        } else {
            logoImage.setVisibility(View.GONE);
        }
        // Set background
        setBackgroundShapeAndColor((CardView) rootView, pass.getParsedBgColor());

        // Set colors
        organizationName.setTextColor(color2);
        relevantDate.setTextColor(color2);

        // Set organization name
        if (pass.getLogoText() != null && !pass.getLogoText().trim().isEmpty()) {
            organizationName.setText(pass.getLogoText());
            organizationName.setVisibility(View.VISIBLE);
            relevantDate.setVisibility(View.VISIBLE);
        } else {
            organizationName.setVisibility(View.GONE);
            relevantDate.setVisibility(View.GONE);
        }
        // Set date
        if (pass.getRelevantDate() != null && !pass.getRelevantDate().isEmpty()) {
            relevantDate.setText(pass.getFormattedRelevantDate(context));
        } else {
            relevantDate.setVisibility(View.INVISIBLE);
        }

        setUpperRightInfo(pass);


        // Setting click event
        rootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListEventListener().onListEvent(0, pass, v);
            }
        });

        // Show/hide articial event ticket mark
        if (pass.isAEventTicket()) {
            eventTicketMark.setVisibility(View.VISIBLE);
        } else {
            eventTicketMark.setVisibility(View.GONE);
        }
    }

    private void setUpperRightInfo(PassBookDTO pass) {
        List<FieldDictionaryDTO> headerFields = pass.getHeaderFields();

        fillHeaderFields(headerFields);
    }

    private void fillHeaderFields(List<FieldDictionaryDTO> fields) {
        headerFieldsLayout.removeAllViews();

        for (FieldDictionaryDTO field : fields) {
            PassBookRowHeaderItemView fieldView = new PassBookRowHeaderItemView(context);
            fieldView.setColors(color1, color2);
            fieldView.bind(field);
            headerFieldsLayout.addView(fieldView);
        }
    }

    private void setBackgroundShapeAndColor(CardView v, int backgroundColor) {
        v.setCardBackgroundColor(backgroundColor);
    }

}
