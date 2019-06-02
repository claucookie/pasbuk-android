package es.claucookie.pasbuk.model.dto;

import android.os.Parcelable;
import android.os.Parcel;
import org.json.JSONObject;
import es.claucookie.pasbuk.model.dto.base.PasbukDTOBundle;
import es.claucookie.pasbuk.model.dao.*;

/**
 * Extended DTO PassDTO
 * This DTO CAN be extended (will not be overwritten by generator)
 * @see es.claucookie.pasbuk.model.dto.base.PasbukDTOBundle.BasePassDTO
 * @author Service Generator
 */
public final class PassDTO extends PasbukDTOBundle.BasePassDTO {
    
    // Type of transit. Must be one of the following values: PKTransitTypeAir, PKTransitTypeBoat, PKTransitTypeBus, PKTransitTypeGeneric, PKTransitTypeTrain.
    private static final String TRANSIT_AIR = "PKTransitTypeAir";
    private static final String TRANSIT_BOAT = "PKTransitTypeBoat";
    private static final String TRANSIT_BUS = "PKTransitTypeBus";
    private static final String TRANSIT_TRAIN = "PKTransitTypeTrain";
    private static final String TRANSIT_GENERIC = "PKTransitTypeGeneric";
    /**
     * Creates DTO with default values
     */
    public PassDTO() {
      super();
    }

    /**
     * Creates DTO from Parcel Data
     */
    public PassDTO(Parcel in) {
      super(in);
    }

	/**
	 * Static Parcelable serializer/deserializer
	 */
	public static final Parcelable.Creator<PassDTO> CREATOR = new Parcelable.Creator<PassDTO>() {
        public PassDTO createFromParcel(Parcel in) {
            return new PassDTO(in);
        }
        public PassDTO[] newArray(int size) {
            return new PassDTO[size];
        }
    };
    
    public boolean itsTransitIsByAir() {
        return getTransitType().equals(TRANSIT_AIR);
    }

    public boolean itsTransitIsByBoat() {
        return getTransitType().equals(TRANSIT_BOAT);
    }

    public boolean itsTransitIsByBus() {
        return getTransitType().equals(TRANSIT_BUS);
    }

    public boolean itsTransitIsByTrain() {
        return getTransitType().equals(TRANSIT_TRAIN);
    }

    public boolean itsTransitIsGeneric() {
        return getTransitType().equals(TRANSIT_GENERIC);
    }


    public String getTransitType() {
        return super.getTransitType() != null ? super.getTransitType() : TRANSIT_GENERIC;
    }
}
