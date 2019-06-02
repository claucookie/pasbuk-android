package es.claucookie.pasbuk.model.dto;

import android.os.Parcelable;
import android.os.Parcel;
import org.json.JSONObject;
import es.claucookie.pasbuk.model.dto.base.PasbukDTOBundle;
import es.claucookie.pasbuk.model.dao.*;

/**
 * Extended DTO PassBookCompaniesDTO
 * This DTO CAN be extended (will not be overwritten by generator)
 * @see es.claucookie.pasbuk.model.dto.base.PasbukDTOBundle.BasePassBookCompaniesDTO
 * @author Service Generator
 */
public final class PassBookCompaniesDTO extends PasbukDTOBundle.BasePassBookCompaniesDTO {
    /**
     * Creates DTO with default values
     */
    public PassBookCompaniesDTO() {
      super();
    }

    /**
     * Creates DTO from Parcel Data
     */
    public PassBookCompaniesDTO(Parcel in) {
      super(in);
    }

	/**
	 * Static Parcelable serializer/deserializer
	 */
	public static final Parcelable.Creator<PassBookCompaniesDTO> CREATOR = new Parcelable.Creator<PassBookCompaniesDTO>() {
        public PassBookCompaniesDTO createFromParcel(Parcel in) {
            return new PassBookCompaniesDTO(in);
        }
        public PassBookCompaniesDTO[] newArray(int size) {
            return new PassBookCompaniesDTO[size];
        }
    };
}
