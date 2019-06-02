package es.claucookie.pasbuk.model.dto;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

import java.text.SimpleDateFormat;
import java.util.Currency;
import java.util.Locale;

import es.claucookie.pasbuk.helpers.FormatDateHelper;
import es.claucookie.pasbuk.model.dto.base.PasbukDTOBundle;

/**
 * Extended DTO FieldDictionaryDTO
 * This DTO CAN be extended (will not be overwritten by generator)
 *
 * @author Service Generator
 * @see es.claucookie.pasbuk.model.dto.base.PasbukDTOBundle.BaseFieldDictionaryDTO
 */
public final class FieldDictionaryDTO extends PasbukDTOBundle.BaseFieldDictionaryDTO {
    /**
     * Creates DTO with default values
     */
    public FieldDictionaryDTO() {
        super();
    }

    /**
     * Creates DTO from Parcel Data
     */
    public FieldDictionaryDTO(Parcel in) {
        super(in);
    }

    /**
     * Static Parcelable serializer/deserializer
     */
    public static final Parcelable.Creator<FieldDictionaryDTO> CREATOR = new Parcelable.Creator<FieldDictionaryDTO>() {
        public FieldDictionaryDTO createFromParcel(Parcel in) {
            return new FieldDictionaryDTO(in);
        }

        public FieldDictionaryDTO[] newArray(int size) {
            return new FieldDictionaryDTO[size];
        }
    };

    /**
     * getMessage() = Format string for the alert text that is displayed when the pass is updated.
     * The format string must contain the escape %@, which is replaced with the field’s new value. For example, Gate changed to %@.*
     */
    public String getFormatedChangeMessage(String fieldValue) {
        String androidFormatChangeMessage = "";
        if (getChangeMessage() != null) {
            androidFormatChangeMessage = getChangeMessage().replace("%@", "%s");
            androidFormatChangeMessage = String.format(Locale.US, androidFormatChangeMessage, fieldValue);
        }
        return androidFormatChangeMessage;
    }

    public String getFormattedDate(String dateString) {
        String formattedDate = "";
        SimpleDateFormat inFormat = new SimpleDateFormat(FormatDateHelper.RELEVANT_DATE_FORMAT, Locale.US);
        SimpleDateFormat outFormat = new SimpleDateFormat(PassBookDTO.RELEVANT_DATE_HUMAN_FORMAT2, Locale.US);

        formattedDate = FormatDateHelper.getReadableDate(dateString, inFormat, outFormat);

        return formattedDate;
    }

    public String getFormattedDate(String dateString, String customFormat) {
        String formattedDate = "";
        SimpleDateFormat inFormat = new SimpleDateFormat(FormatDateHelper.RELEVANT_DATE_FORMAT, Locale.US);
        SimpleDateFormat outFormat = new SimpleDateFormat(customFormat, Locale.US);

        formattedDate = FormatDateHelper.getReadableDate(dateString, inFormat, outFormat);

        return formattedDate;
    }

    public boolean isATimestamp() {
        return (getDateStyle() != null || getTimeStyle() != null) && getKey().toLowerCase().contains("date");
    }

    public boolean hasCurrencyInIt() {
        return getCurrencyCode() != null;
    }

    public String getFormattedCurrency() {
        String currencyValue = getValue();
        if (hasCurrencyInIt()) {
            Currency currency = Currency.getInstance(getCurrencyCode());
            if (currency != null) {

                currencyValue = String.format(Locale.US, "%s%s", getValue(), currency.getSymbol());
            }
        }

        return currencyValue;
    }
}
