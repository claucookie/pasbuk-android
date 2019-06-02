package es.claucookie.pasbuk.dialogs;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.View;
import android.view.Window;
import android.widget.RelativeLayout;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.EFragment;
import org.androidannotations.annotations.ViewById;

import es.claucookie.pasbuk.R;

/**
 * Created by claucookie on 27/03/15.
 */
@EFragment(R.layout.fragment_tutorial_dialog)
public class TutorialDialogFragment extends DialogFragment{

    @ViewById
    RelativeLayout backgroundLayout;

    Drawing d = null;
    RelativeLayout.LayoutParams rlp = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(DialogFragment.STYLE_NORMAL, android.R.style.Theme_Translucent_NoTitleBar);
    }

    @AfterViews
    void initViews() {

        // Create view to draw the holes
        d = new Drawing((Context)getActivity());
        rlp = new RelativeLayout.LayoutParams(
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.MATCH_PARENT);
        rlp.addRule(RelativeLayout.CENTER_IN_PARENT);
        d.setLayoutParams(rlp);

        backgroundLayout.addView(d);
    }

    public class Drawing extends View {
        Paint[] pDraw = null;

        public Drawing(Context ct) {
            super(ct);

            // Generate array of holes
            pDraw = new Paint[2];

            for (int i = 0; i<pDraw.length; i++) {
                pDraw[i] = new Paint();
                pDraw[i].setARGB(255, 0, 0, 0);
                pDraw[i].setStyle(Paint.Style.FILL);
            }

            // Set all transfer modes
            pDraw[0].setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
            pDraw[1].setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
        }

        @Override
        public void onDraw(Canvas canv) {
            // Background colour for canvas
            canv.drawColor(Color.argb(200, 0, 0, 0));

            float w, h;
            w = getMeasuredWidth();
            h = getMeasuredHeight();

            // Loop, draws 4 circles per row, in 4 rows on top of bitmap
            // Drawn in the order of pDraw (alphabetical)
            for(int i = 0; i<2; i++) {
                    canv.drawCircle(200*(i+1), 200*(i+1), 100, pDraw[i]);
            }
        }
    }
}
