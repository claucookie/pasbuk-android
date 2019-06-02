package es.claucookie.pasbuk.model.dto;

import android.os.Parcelable;
import android.os.Parcel;
import org.json.JSONObject;
import es.claucookie.pasbuk.model.dto.base.PasbukDTOBundle;
import es.claucookie.pasbuk.model.dao.*;

/**
 * Extended DTO BarCodeDTO
 * This DTO CAN be extended (will not be overwritten by generator)
 * @see es.claucookie.pasbuk.model.dto.base.PasbukDTOBundle.BaseBarCodeDTO
 * @author Service Generator
 */
public final class BarCodeDTO extends PasbukDTOBundle.BaseBarCodeDTO {
    
    private static final String QR_FORMAT = "PKBarcodeFormatQR";
    private static final String PDF_FORMAT = "PKBarcodeFormatPDF417";
    private static final String AZTEC_FORMAT = "PKBarcodeFormatAztec";
    /**
     * Creates DTO with default values
     */
    public BarCodeDTO() {
      super();
    }

    /**
     * Creates DTO from Parcel Data
     */
    public BarCodeDTO(Parcel in) {
      super(in);
    }

	/**
	 * Static Parcelable serializer/deserializer
	 */
	public static final Parcelable.Creator<BarCodeDTO> CREATOR = new Parcelable.Creator<BarCodeDTO>() {
        public BarCodeDTO createFromParcel(Parcel in) {
            return new BarCodeDTO(in);
        }
        public BarCodeDTO[] newArray(int size) {
            return new BarCodeDTO[size];
        }
    };
    
    public boolean itsFormatIsQR() {
        return getFormat() != null && getFormat().equals(QR_FORMAT);
    }

    public boolean itsFormatIsPDF() {
        return getFormat() != null && getFormat().equals(PDF_FORMAT);
    }

    public boolean itsFormatIsAZTEC() {
        return getFormat() != null && getFormat().equals(AZTEC_FORMAT);
    }
}
