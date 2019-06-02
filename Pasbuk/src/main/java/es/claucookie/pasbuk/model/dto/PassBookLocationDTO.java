package es.claucookie.pasbuk.model.dto;

import android.os.Parcelable;
import android.os.Parcel;
import org.json.JSONObject;
import es.claucookie.pasbuk.model.dto.base.PasbukDTOBundle;
import es.claucookie.pasbuk.model.dao.*;

/**
 * Extended DTO PassBookLocationDTO
 * This DTO CAN be extended (will not be overwritten by generator)
 * @see es.claucookie.pasbuk.model.dto.base.PasbukDTOBundle.BasePassBookLocationDTO
 * @author Service Generator
 */
public final class PassBookLocationDTO extends PasbukDTOBundle.BasePassBookLocationDTO {
    /**
     * Creates DTO with default values
     */
    public PassBookLocationDTO() {
      super();
    }

    /**
     * Creates DTO from Parcel Data
     */
    public PassBookLocationDTO(Parcel in) {
      super(in);
    }

	/**
	 * Static Parcelable serializer/deserializer
	 */
	public static final Parcelable.Creator<PassBookLocationDTO> CREATOR = new Parcelable.Creator<PassBookLocationDTO>() {
        public PassBookLocationDTO createFromParcel(Parcel in) {
            return new PassBookLocationDTO(in);
        }
        public PassBookLocationDTO[] newArray(int size) {
            return new PassBookLocationDTO[size];
        }
    };
}
