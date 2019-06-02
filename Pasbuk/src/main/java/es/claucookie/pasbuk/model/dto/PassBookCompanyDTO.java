package es.claucookie.pasbuk.model.dto;

import android.os.Parcelable;
import android.os.Parcel;
import org.json.JSONObject;

import java.util.ArrayList;

import es.claucookie.pasbuk.model.dto.base.PasbukDTOBundle;
import es.claucookie.pasbuk.model.dao.*;

/**
 * Extended DTO PassBookCompanyDTO
 * This DTO CAN be extended (will not be overwritten by generator)
 * @see es.claucookie.pasbuk.model.dto.base.PasbukDTOBundle.BasePassBookCompanyDTO
 * @author Service Generator
 */
public final class PassBookCompanyDTO extends PasbukDTOBundle.BasePassBookCompanyDTO {

    public boolean wasSelected = false;
    /**
     * Creates DTO with default values
     */
    public PassBookCompanyDTO() {
      super();
    }

    /**
     * Creates DTO from Parcel Data
     */
    public PassBookCompanyDTO(Parcel in) {
      super(in);
    }

	/**
	 * Static Parcelable serializer/deserializer
	 */
	public static final Parcelable.Creator<PassBookCompanyDTO> CREATOR = new Parcelable.Creator<PassBookCompanyDTO>() {
        public PassBookCompanyDTO createFromParcel(Parcel in) {
            return new PassBookCompanyDTO(in);
        }
        public PassBookCompanyDTO[] newArray(int size) {
            return new PassBookCompanyDTO[size];
        }
    };

    public boolean existsInside(ArrayList<PassBookCompanyDTO> companies) {
        boolean exists = false;
        if (companies != null && getTeamIdentifier() != null) {
            for (PassBookCompanyDTO company : companies) {
                if (company.getTeamIdentifier() != null && getTeamIdentifier().equals(company.getTeamIdentifier())) {
                    exists = true;
                }
            }
        }
        return exists;
    }

    public ArrayList<PassBookCompanyDTO> removeFrom(ArrayList<PassBookCompanyDTO> companies) {
        if (companies != null) {
            for (int i=0; i<companies.size(); i++) {
                if (getTeamIdentifier().equals(companies.get(i).getTeamIdentifier())) {
                    companies.remove(i);
                    break;
                }
            }
        }
        return companies;
    }
}
