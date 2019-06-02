package es.claucookie.pasbuk.model.dto;

import android.content.Context;
import android.graphics.Color;
import android.os.Parcel;
import android.os.Parcelable;

import com.crashlytics.android.Crashlytics;
import com.google.zxing.BarcodeFormat;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.Charset;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import es.claucookie.pasbuk.helpers.FormatDateHelper;
import es.claucookie.pasbuk.helpers.LogHelper;
import es.claucookie.pasbuk.helpers.PreferencesHelper;
import es.claucookie.pasbuk.model.dto.base.PasbukDTOBundle;


/**
 * Extended DTO PassBookDTO
 * This DTO CAN be extended (will not be overwritten by generator)
 *
 * @author Service Generator
 * @see es.claucookie.pasbuk.model.dto.base.PasbukDTOBundle.BasePassBookDTO
 */
public final class PassBookDTO extends PasbukDTOBundle.BasePassBookDTO {

    public static final String PASSBOOK_JSON_FILENAME = "pass.json";
    public static final String LOGO_IMAGE_FILENAME = "logo.png";
    public static final String LOGO_IMAGE_FILENAME2X = "logo@2x.png";
    public static final String BACKGROUND_IMAGE_FILENAME = "background.png";
    public static final String STRIP_IMAGE_FILENAME = "strip.png";
    public static final String THUMBNAIL_IMAGE_FILENAME = "thumbnail.png";
    private static final float COLOR_BACKGROUND_DARKER_FACTOR = -0.61f;
    private static final float COLOR_LABEL_DARKER_FACTOR = -0.4f;
    private static final float COLOR_BACKGROUND_LIGHTER_FACTOR = 0.10f;
    private static final int COLOR_EQUAL_FACTOR = 0;
    private static final String BLACK_RGB_COLOR = "rgb(0,0,0)";
    private static final String DEFAULT_BG_HEX_COLOR = "#333333";
    private static final String DEFAULT_TEXT_RGB_COLOR = "rgb(255, 255, 255)";
    public static final String RELEVANT_DATE_HUMAN_FORMAT = "dd MMM yyyy";
    public static final String RELEVANT_DATE_HUMAN_FORMAT2 = "dd MMM yyyy HH:mm"; //http://docs.oracle.com/javase/tutorial/i18n/format/simpleDateFormat.html
    private static final String PDF417_API_URL = "http://generator.onbarcode.com/pdf417.aspx?DATA=%s&PROCESS-TILDE=false&ECL=2&ROW-COUNT=10&COLUMN-COUNT=5&TRUNCATED=false&UOM=0&X=2&X-Y-RATIO=0.3333333&LEFT-MARGIN=0&RIGHT-MARGIN=0&TOP-MARGIN=0&BOTTOM-MARGIN=0&RESOLUTION=72&ROTATE=0&IMAGE-FORMAT=jpg";
    private static final String AZTEC_API_URL = "http://www.pqscan.com/online-demo/barcode-creator/BarcodeWebHandler.ashx?BarType=Aztec&Data=%s&Width=340&Height=340";
    public static final String UPDATE_PASS_URL = "%s/v1/passes/%s/%s";

    /**
     * Creates DTO with default values
     */
    public PassBookDTO() {
        super();
    }

    /**
     * Creates DTO from Parcel Data
     */
    public PassBookDTO(Parcel in) {
        super(in);
    }

    /**
     * Static Parcelable serializer/deserializer
     */
    public static final Parcelable.Creator<PassBookDTO> CREATOR = new Parcelable.Creator<PassBookDTO>() {
        public PassBookDTO createFromParcel(Parcel in) {
            return new PassBookDTO(in);
        }

        public PassBookDTO[] newArray(int size) {
            return new PassBookDTO[size];
        }
    };

    public boolean isABoardingPass() {
        return getBoardingPass() != null;
    }

    public boolean isACoupon() {
        return getCoupon() != null;
    }

    public boolean isAEventTicket() {
        return getEventTicket() != null;
    }

    public boolean isAStoreCard() {
        return getStoreCard() != null;
    }

    public boolean isGeneric() {
        return getGeneric() != null;
    }

    /**
     * Other properties methods
     */

    public boolean existsInside(ArrayList<PassBookDTO> passes) {
        boolean exists = false;
        for (PassBookDTO pass : passes) {
            if (getSerialNumber() != null && getSerialNumber().equals(pass.getSerialNumber())) {
                exists = true;
            }
        }
        return exists;
    }

    public List<FieldDictionaryDTO> getHeaderFields() {

        List<FieldDictionaryDTO> headerFields = new ArrayList<FieldDictionaryDTO>();
        if (isABoardingPass()) {
            headerFields = getBoardingPass().getHeaderFields();

        } else if (isACoupon()) {
            headerFields = getCoupon().getHeaderFields();

        } else if (isAEventTicket()) {
            headerFields = getEventTicket().getHeaderFields();

        } else if (isAStoreCard()) {
            headerFields = getStoreCard().getHeaderFields();

        } else if (isGeneric()) {
            headerFields = getGeneric().getHeaderFields();
        }
        return headerFields;
    }

    public List<FieldDictionaryDTO> getPrimaryFields() {

        List<FieldDictionaryDTO> headerFields = new ArrayList<FieldDictionaryDTO>();
        if (isABoardingPass()) {
            headerFields = getBoardingPass().getPrimaryFields();

        } else if (isACoupon()) {
            headerFields = getCoupon().getPrimaryFields();

        } else if (isAEventTicket()) {
            headerFields = getEventTicket().getPrimaryFields();

        } else if (isAStoreCard()) {
            headerFields = getStoreCard().getPrimaryFields();

        } else if (isGeneric()) {
            headerFields = getGeneric().getPrimaryFields();
        }
        return headerFields;
    }

    public List<FieldDictionaryDTO> getAuxiliaryFields() {

        List<FieldDictionaryDTO> fields = new ArrayList<FieldDictionaryDTO>();
        if (isABoardingPass()) {
            fields = getBoardingPass().getAuxiliaryFields();

        } else if (isACoupon()) {
            fields = getCoupon().getAuxiliaryFields();

        } else if (isAEventTicket()) {
            fields = getEventTicket().getAuxiliaryFields();

        } else if (isAStoreCard()) {
            fields = getStoreCard().getAuxiliaryFields();

        } else if (isGeneric()) {
            fields = getGeneric().getAuxiliaryFields();
        }
        return fields;
    }

    public List<FieldDictionaryDTO> getSecondaryFields() {

        List<FieldDictionaryDTO> fields = new ArrayList<FieldDictionaryDTO>();
        if (isABoardingPass()) {
            fields = getBoardingPass().getSecondaryFields();

        } else if (isACoupon()) {
            fields = getCoupon().getSecondaryFields();

        } else if (isAEventTicket()) {
            fields = getEventTicket().getSecondaryFields();

        } else if (isAStoreCard()) {
            fields = getStoreCard().getSecondaryFields();

        } else if (isGeneric()) {
            fields = getGeneric().getSecondaryFields();
        }
        return fields;
    }

    public List<FieldDictionaryDTO> getBackFields() {

        List<FieldDictionaryDTO> fields = new ArrayList<FieldDictionaryDTO>();
        if (isABoardingPass()) {
            fields = getBoardingPass().getBackFields();

        } else if (isACoupon()) {
            fields = getCoupon().getBackFields();

        } else if (isAEventTicket()) {
            fields = getEventTicket().getBackFields();

        } else if (isAStoreCard()) {
            fields = getStoreCard().getBackFields();

        } else if (isGeneric()) {
            fields = getGeneric().getBackFields();
        }
        return fields;
    }

    public String getFirstFieldLabel(List<FieldDictionaryDTO> headerFields) {
        String label = "";
        if (headerFields != null && headerFields.size() > 0) {
            label = headerFields.get(0).getLabel();
        }

        return label;
    }

    public String getFirstFieldValue(List<FieldDictionaryDTO> headerFields) {
        String value = "";
        if (headerFields != null && headerFields.size() > 0) {
            FieldDictionaryDTO headerField = headerFields.get(0);
            value = headerField.getValue();
            if (headerField.hasCurrencyInIt()) {
                value = headerField.getFormattedCurrency();
            }
        }

        return value;
    }

    public String getFormattedRelevantDate(Context context) {
        SimpleDateFormat format = new SimpleDateFormat(FormatDateHelper.RELEVANT_DATE_FORMAT, Locale.US);
        String humanFormat = PreferencesHelper.getInstance().getDateFormat(context);
        SimpleDateFormat format2 = new SimpleDateFormat(humanFormat, Locale.US);

        return FormatDateHelper.getReadableDate(getRelevantDate(), format, format2);
    }

    public long getRelevantDatetime() {
        return FormatDateHelper.getLongDateTime(getRelevantDate());
    }

    public String getFormattedUpdateDate() {
        String formattedDate = "";
        SimpleDateFormat format2 = new SimpleDateFormat(RELEVANT_DATE_HUMAN_FORMAT2, Locale.US);
        Date dateTime = new Date(getUpdateDate());
        formattedDate = format2.format(dateTime);
        return formattedDate;
    }

    public long getLongTime() {
        long dateTime = 0;
        if (getRelevantDate() != null) {
            SimpleDateFormat format = new SimpleDateFormat(FormatDateHelper.RELEVANT_DATE_FORMAT, Locale.US);
            try {
                Date date = format.parse(getRelevantDate());
                dateTime = date.getTime();
            } catch (ParseException e) {
                LogHelper.logE("PassBookDTO", e.getMessage());
            }
        }
        return dateTime;
    }


    /**
     * Appearance methods
     */

    public int getParsedBgColorPressed() {
        return parseAndGetDarkerColor(getBackgroundColor(), COLOR_BACKGROUND_DARKER_FACTOR, false);
    }

    public int getParsedBgColor() {
        return parseAndGetDarkerColor(getBackgroundColor(), COLOR_BACKGROUND_LIGHTER_FACTOR, false);
    }

    public int getParsedTitleColor() {
        String color = getLabelColor() != null ? getLabelColor() : DEFAULT_TEXT_RGB_COLOR;
        return parseAndGetDarkerColor(color, COLOR_EQUAL_FACTOR, true);
    }

    public int getParsedValueColor() {
        // If foreground color is not provided, use white color
        String color = getForegroundColor() != null ? getForegroundColor() : DEFAULT_TEXT_RGB_COLOR;
        return parseAndGetDarkerColor(color, COLOR_EQUAL_FACTOR, true);
    }

    private int parseAndGetDarkerColor(String colorString, float colorFactor, boolean label) {
        int color = Color.parseColor(DEFAULT_BG_HEX_COLOR);
        if (colorString != null && !colorString.isEmpty()) {
            // Added pattern to cover rgb(X,X,X) cases and rgba(X,X,X,X)
            Pattern patternRgb = Pattern.compile("rgb *\\( *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
            Pattern patternRgba = Pattern.compile("rgba *\\( *([0-9]+), *([0-9]+), *([0-9]+), *([0-9]+) *\\)");
            Matcher matcherRgb = patternRgb.matcher(colorString);
            Matcher matcherRgba = patternRgba.matcher(colorString);
            Matcher generalMatcher = null;
            if (matcherRgb.matches()) {
                generalMatcher = matcherRgb;
            } else if (matcherRgba.matches()) {
                generalMatcher = matcherRgba;
            }

            if (generalMatcher != null) {
                int r = Integer.valueOf(generalMatcher.group(1));
                int g = Integer.valueOf(generalMatcher.group(2));
                int b = Integer.valueOf(generalMatcher.group(3));

                float[] hsv = new float[3];
                Color.RGBToHSV(r, g, b, hsv);
                if (!label) {
                    // If background is not black, then apply a little change
                    if (!(hsv[0] == 0 && hsv[1] == 0 && hsv[2] == 0)) {
                        hsv[2] = hsv[2] + colorFactor;
                    }
                } else {
                    // If background is too dark, then the label will not be seen
                    // make the label lighter if V value is under 35
                    if (hsv[2] < 0.4f && hsv[1] > 0.7f) {
                        hsv[1] = 0.4f; // saturation
                        hsv[2] = 1.0f; // value
                    } else {
                        hsv[2] = hsv[2] + colorFactor;
                    }
                }
                color = Color.HSVToColor(hsv);

            } else { // Hex color?
                try {
                    color = Color.parseColor(colorString);
                } catch (IllegalArgumentException e) {
                    Crashlytics.log("Unknown hsv color " + colorString);
                    color = Color.parseColor(DEFAULT_BG_HEX_COLOR);
                }
            }
        }

        return color;
    }

    public String getBarcodeUrl() {
        String url = "";
        if (getBarcode() != null && getBarcode().itsFormatIsPDF()) {
            String value = getBarcode().getMessage();
            try {
                value = URLEncoder.encode(value, getBarcode().getMessageEncoding()).replaceAll("\\+", "%20");
            } catch (UnsupportedEncodingException e) {
                LogHelper.logE("PassBookDTO", "Barcode message encoding failed !!!");
                Crashlytics.log(999, "BarcodeDownloadFailed", "Message: "+ getBarcode().getMessage() + " --- Result: "+value);
            }
            url = String.format(Locale.US, PDF417_API_URL, value);

        } else if (getBarcode() != null && getBarcode().itsFormatIsAZTEC()) {
            String value = getBarcode().getMessage();
            try {
                value = URLEncoder.encode(value, getBarcode().getMessageEncoding()).replaceAll("\\+", "%20");
            } catch (UnsupportedEncodingException e) {
                LogHelper.logE("PassBookDTO", "Barcode message encoding failed !!!");
                Crashlytics.log(999, "BarcodeDownloadFailed", "Message: "+ getBarcode().getMessage() + " --- Result: "+value);
            }
            url = String.format(Locale.US, AZTEC_API_URL, value);
        }
        return url;
    }


    public String getUpdatePassUri() {
        return String.format(Locale.US, PassBookDTO.UPDATE_PASS_URL, getWebServiceURL(), getPassTypeIdentifier(), getSerialNumber());
    }


    public BarcodeFormat getBarcodeFormat() {
        if (getBarcode().itsFormatIsQR()) {
            return BarcodeFormat.QR_CODE;
        } else if (getBarcode().itsFormatIsAZTEC()) {
            return BarcodeFormat.AZTEC;
        } else {
            return BarcodeFormat.PDF_417;
        }
    }
}
