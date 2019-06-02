package es.claucookie.pasbuk.model.dto;

import android.os.Parcelable;
import android.os.Parcel;
import org.json.JSONObject;
import es.claucookie.pasbuk.model.dto.base.PasbukDTOBundle;
import es.claucookie.pasbuk.model.dao.*;

/**
 * Extended DTO PassBooksDTO
 * This DTO CAN be extended (will not be overwritten by generator)
 * @see es.claucookie.pasbuk.model.dto.base.PasbukDTOBundle.BasePassBooksDTO
 * @author Service Generator
 */
public final class PassBooksDTO extends PasbukDTOBundle.BasePassBooksDTO {
    /**
     * Creates DTO with default values
     */
    public PassBooksDTO() {
      super();
    }

    /**
     * Creates DTO from Parcel Data
     */
    public PassBooksDTO(Parcel in) {
      super(in);
    }

	/**
	 * Static Parcelable serializer/deserializer
	 */
	public static final Parcelable.Creator<PassBooksDTO> CREATOR = new Parcelable.Creator<PassBooksDTO>() {
        public PassBooksDTO createFromParcel(Parcel in) {
            return new PassBooksDTO(in);
        }
        public PassBooksDTO[] newArray(int size) {
            return new PassBooksDTO[size];
        }
    };
}
