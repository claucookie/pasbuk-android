package es.claucookie.pasbuk.model.dto.base;

import java.util.List;
import java.util.ArrayList;
import android.os.Parcel;
import android.os.Parcelable;
import java.io.File;
import es.claucookie.pasbuk.model.dto.*;

/**
 * All the DTOs in a single file
 * This file will be overwritten with each generation so place the code in the inherited classes
 * @author Service Generator
 *
 * Generated Class - DO NOT MODIFY
 */
public class PasbukDTOBundle {
	/**
	 * DTO defining class PassBooksDTO
	 */
	public static class BasePassBooksDTO implements Parcelable {
		public BasePassBooksDTO(Parcel in) {
			readFromParcel(in);
		}
	
		public BasePassBooksDTO() {
		}
	
		// Field name on service:passes
		private List<PassBookDTO> passes; 
		
		// Setters y Getters
		
		/**
		 * Setter de la propiedad passes , 
		 * Field name on service:passes
		 * @param passes valor a establecer en el set
		 */
		public void setPasses(List<PassBookDTO> passes){
			this.passes=passes;
		}
		/**
		 * Getter de la propiedad passes , 
		 * Field name on service:passes
		 * @returns Valor de la propiedad passes
		 */
		public List<PassBookDTO> getPasses(){
			return passes;
		}

        // Equality stuff

	@Override
	public boolean equals(final Object object) {
		if (this == object) return true;
        	if (!(object instanceof BasePassBooksDTO)) return false;
		final BasePassBooksDTO other = (BasePassBooksDTO) object;
        	boolean equality = true;
		if (getPasses()==null) {
			if(other.getPasses()!=null) {
				return false;
			}
		} else {
			equality = equality && getPasses().equals(other.getPasses());
		}
		return equality;
	}

	@Override
	public int hashCode() {
		int result = 0;
		if (getPasses()!=null) {
        	    result = result ^ getPasses().hashCode();
		}
		return result;
	}

    	// Parcelable stuff
		
		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {

			if(passes!=null){
                dest.writeByte((byte)1);
			    dest.writeTypedList(passes);
            }
			else{
			    dest.writeByte((byte)0);
			}
		}
		
		public void readFromParcel(Parcel in) {

            if(in.readByte()==1)
            {
			passes = new ArrayList<PassBookDTO>();
			in.readTypedList(passes, PassBookDTO.CREATOR);
			}
		}
		
		public static final Parcelable.Creator<BasePassBooksDTO> CREATOR =
			new Parcelable.Creator<BasePassBooksDTO>() {
				public BasePassBooksDTO createFromParcel(Parcel in) {
					return new BasePassBooksDTO(in);
				}
				public BasePassBooksDTO[] newArray(int size) {
					return new BasePassBooksDTO[size];
				}
			};
			
	}
	/**
	 * DTO defining class PassBookDTO
	 */
	public static class BasePassBookDTO implements Parcelable {
		public BasePassBookDTO(Parcel in) {
			readFromParcel(in);
		}
	
		public BasePassBookDTO() {
		}
	
		// Field name on service:formatVersion
		private Integer formatVersion; 
		// Field name on service:serialNumber
		private String serialNumber; 
		// Field name on service:passTypeIdentifier
		private String passTypeIdentifier; 
		// Field name on service:teamIdentifier
		private String teamIdentifier; 
		// Field name on service:authenticationToken
		private String authenticationToken; 
		// Field name on service:webServiceURL
		private String webServiceURL; 
		// Field name on service:organizationName
		private String organizationName; 
		// Field name on service:description
		private String description; 
		// Field name on service:barcode
		private BarCodeDTO barcode; 
		// Field name on service:locations
		private List<PassBookLocationDTO> locations; 
		// Field name on service:maxDistance
		private Integer maxDistance; 
		// Field name on service:relevantDate
		private String relevantDate; 
		// Field name on service:updateDate
		private Long updateDate; 
		// Field name on service:backgroundColor
		private String backgroundColor; 
		// Field name on service:foregroundColor
		private String foregroundColor; 
		// Field name on service:labelColor
		private String labelColor; 
		// Field name on service:logoText
		private String logoText; 
		// Field name on service:boardingPass
		private PassDTO boardingPass; 
		// Field name on service:coupon
		private PassDTO coupon; 
		// Field name on service:eventTicket
		private PassDTO eventTicket; 
		// Field name on service:generic
		private PassDTO generic; 
		// Field name on service:storeCard
		private PassDTO storeCard; 
		// Field name on service:logoImage
		private String logoImage; 
		// Field name on service:backgroundImage
		private String backgroundImage; 
		// Field name on service:stripImage
		private String stripImage; 
		// Field name on service:thumbnailImage
		private String thumbnailImage; 
		// Field name on service:passbookFile
		private String passbookFile; 
		
		// Setters y Getters
		
		/**
		 * Setter de la propiedad formatVersion , 
		 * Field name on service:formatVersion
		 * @param formatVersion valor a establecer en el set
		 */
		public void setFormatVersion(Integer formatVersion){
			this.formatVersion=formatVersion;
		}
		/**
		 * Getter de la propiedad formatVersion , 
		 * Field name on service:formatVersion
		 * @returns Valor de la propiedad formatVersion
		 */
		public Integer getFormatVersion(){
			return formatVersion;
		}
		/**
		 * Setter de la propiedad serialNumber , 
		 * Field name on service:serialNumber
		 * @param serialNumber valor a establecer en el set
		 */
		public void setSerialNumber(String serialNumber){
			this.serialNumber=serialNumber;
		}
		/**
		 * Getter de la propiedad serialNumber , 
		 * Field name on service:serialNumber
		 * @returns Valor de la propiedad serialNumber
		 */
		public String getSerialNumber(){
			return serialNumber;
		}
		/**
		 * Setter de la propiedad passTypeIdentifier , 
		 * Field name on service:passTypeIdentifier
		 * @param passTypeIdentifier valor a establecer en el set
		 */
		public void setPassTypeIdentifier(String passTypeIdentifier){
			this.passTypeIdentifier=passTypeIdentifier;
		}
		/**
		 * Getter de la propiedad passTypeIdentifier , 
		 * Field name on service:passTypeIdentifier
		 * @returns Valor de la propiedad passTypeIdentifier
		 */
		public String getPassTypeIdentifier(){
			return passTypeIdentifier;
		}
		/**
		 * Setter de la propiedad teamIdentifier , 
		 * Field name on service:teamIdentifier
		 * @param teamIdentifier valor a establecer en el set
		 */
		public void setTeamIdentifier(String teamIdentifier){
			this.teamIdentifier=teamIdentifier;
		}
		/**
		 * Getter de la propiedad teamIdentifier , 
		 * Field name on service:teamIdentifier
		 * @returns Valor de la propiedad teamIdentifier
		 */
		public String getTeamIdentifier(){
			return teamIdentifier;
		}
		/**
		 * Setter de la propiedad authenticationToken , 
		 * Field name on service:authenticationToken
		 * @param authenticationToken valor a establecer en el set
		 */
		public void setAuthenticationToken(String authenticationToken){
			this.authenticationToken=authenticationToken;
		}
		/**
		 * Getter de la propiedad authenticationToken , 
		 * Field name on service:authenticationToken
		 * @returns Valor de la propiedad authenticationToken
		 */
		public String getAuthenticationToken(){
			return authenticationToken;
		}
		/**
		 * Setter de la propiedad webServiceURL , 
		 * Field name on service:webServiceURL
		 * @param webServiceURL valor a establecer en el set
		 */
		public void setWebServiceURL(String webServiceURL){
			this.webServiceURL=webServiceURL;
		}
		/**
		 * Getter de la propiedad webServiceURL , 
		 * Field name on service:webServiceURL
		 * @returns Valor de la propiedad webServiceURL
		 */
		public String getWebServiceURL(){
			return webServiceURL;
		}
		/**
		 * Setter de la propiedad organizationName , 
		 * Field name on service:organizationName
		 * @param organizationName valor a establecer en el set
		 */
		public void setOrganizationName(String organizationName){
			this.organizationName=organizationName;
		}
		/**
		 * Getter de la propiedad organizationName , 
		 * Field name on service:organizationName
		 * @returns Valor de la propiedad organizationName
		 */
		public String getOrganizationName(){
			return organizationName;
		}
		/**
		 * Setter de la propiedad description , 
		 * Field name on service:description
		 * @param description valor a establecer en el set
		 */
		public void setDescription(String description){
			this.description=description;
		}
		/**
		 * Getter de la propiedad description , 
		 * Field name on service:description
		 * @returns Valor de la propiedad description
		 */
		public String getDescription(){
			return description;
		}
		/**
		 * Setter de la propiedad barcode , 
		 * Field name on service:barcode
		 * @param barcode valor a establecer en el set
		 */
		public void setBarcode(BarCodeDTO barcode){
			this.barcode=barcode;
		}
		/**
		 * Getter de la propiedad barcode , 
		 * Field name on service:barcode
		 * @returns Valor de la propiedad barcode
		 */
		public BarCodeDTO getBarcode(){
			return barcode;
		}
		/**
		 * Setter de la propiedad locations , 
		 * Field name on service:locations
		 * @param locations valor a establecer en el set
		 */
		public void setLocations(List<PassBookLocationDTO> locations){
			this.locations=locations;
		}
		/**
		 * Getter de la propiedad locations , 
		 * Field name on service:locations
		 * @returns Valor de la propiedad locations
		 */
		public List<PassBookLocationDTO> getLocations(){
			return locations;
		}
		/**
		 * Setter de la propiedad maxDistance , 
		 * Field name on service:maxDistance
		 * @param maxDistance valor a establecer en el set
		 */
		public void setMaxDistance(Integer maxDistance){
			this.maxDistance=maxDistance;
		}
		/**
		 * Getter de la propiedad maxDistance , 
		 * Field name on service:maxDistance
		 * @returns Valor de la propiedad maxDistance
		 */
		public Integer getMaxDistance(){
			return maxDistance;
		}
		/**
		 * Setter de la propiedad relevantDate , 
		 * Field name on service:relevantDate
		 * @param relevantDate valor a establecer en el set
		 */
		public void setRelevantDate(String relevantDate){
			this.relevantDate=relevantDate;
		}
		/**
		 * Getter de la propiedad relevantDate , 
		 * Field name on service:relevantDate
		 * @returns Valor de la propiedad relevantDate
		 */
		public String getRelevantDate(){
			return relevantDate;
		}
		/**
		 * Setter de la propiedad updateDate , 
		 * Field name on service:updateDate
		 * @param updateDate valor a establecer en el set
		 */
		public void setUpdateDate(Long updateDate){
			this.updateDate=updateDate;
		}
		/**
		 * Getter de la propiedad updateDate , 
		 * Field name on service:updateDate
		 * @returns Valor de la propiedad updateDate
		 */
		public Long getUpdateDate(){
			return updateDate;
		}
		/**
		 * Setter de la propiedad backgroundColor , 
		 * Field name on service:backgroundColor
		 * @param backgroundColor valor a establecer en el set
		 */
		public void setBackgroundColor(String backgroundColor){
			this.backgroundColor=backgroundColor;
		}
		/**
		 * Getter de la propiedad backgroundColor , 
		 * Field name on service:backgroundColor
		 * @returns Valor de la propiedad backgroundColor
		 */
		public String getBackgroundColor(){
			return backgroundColor;
		}
		/**
		 * Setter de la propiedad foregroundColor , 
		 * Field name on service:foregroundColor
		 * @param foregroundColor valor a establecer en el set
		 */
		public void setForegroundColor(String foregroundColor){
			this.foregroundColor=foregroundColor;
		}
		/**
		 * Getter de la propiedad foregroundColor , 
		 * Field name on service:foregroundColor
		 * @returns Valor de la propiedad foregroundColor
		 */
		public String getForegroundColor(){
			return foregroundColor;
		}
		/**
		 * Setter de la propiedad labelColor , 
		 * Field name on service:labelColor
		 * @param labelColor valor a establecer en el set
		 */
		public void setLabelColor(String labelColor){
			this.labelColor=labelColor;
		}
		/**
		 * Getter de la propiedad labelColor , 
		 * Field name on service:labelColor
		 * @returns Valor de la propiedad labelColor
		 */
		public String getLabelColor(){
			return labelColor;
		}
		/**
		 * Setter de la propiedad logoText , 
		 * Field name on service:logoText
		 * @param logoText valor a establecer en el set
		 */
		public void setLogoText(String logoText){
			this.logoText=logoText;
		}
		/**
		 * Getter de la propiedad logoText , 
		 * Field name on service:logoText
		 * @returns Valor de la propiedad logoText
		 */
		public String getLogoText(){
			return logoText;
		}
		/**
		 * Setter de la propiedad boardingPass , 
		 * Field name on service:boardingPass
		 * @param boardingPass valor a establecer en el set
		 */
		public void setBoardingPass(PassDTO boardingPass){
			this.boardingPass=boardingPass;
		}
		/**
		 * Getter de la propiedad boardingPass , 
		 * Field name on service:boardingPass
		 * @returns Valor de la propiedad boardingPass
		 */
		public PassDTO getBoardingPass(){
			return boardingPass;
		}
		/**
		 * Setter de la propiedad coupon , 
		 * Field name on service:coupon
		 * @param coupon valor a establecer en el set
		 */
		public void setCoupon(PassDTO coupon){
			this.coupon=coupon;
		}
		/**
		 * Getter de la propiedad coupon , 
		 * Field name on service:coupon
		 * @returns Valor de la propiedad coupon
		 */
		public PassDTO getCoupon(){
			return coupon;
		}
		/**
		 * Setter de la propiedad eventTicket , 
		 * Field name on service:eventTicket
		 * @param eventTicket valor a establecer en el set
		 */
		public void setEventTicket(PassDTO eventTicket){
			this.eventTicket=eventTicket;
		}
		/**
		 * Getter de la propiedad eventTicket , 
		 * Field name on service:eventTicket
		 * @returns Valor de la propiedad eventTicket
		 */
		public PassDTO getEventTicket(){
			return eventTicket;
		}
		/**
		 * Setter de la propiedad generic , 
		 * Field name on service:generic
		 * @param generic valor a establecer en el set
		 */
		public void setGeneric(PassDTO generic){
			this.generic=generic;
		}
		/**
		 * Getter de la propiedad generic , 
		 * Field name on service:generic
		 * @returns Valor de la propiedad generic
		 */
		public PassDTO getGeneric(){
			return generic;
		}
		/**
		 * Setter de la propiedad storeCard , 
		 * Field name on service:storeCard
		 * @param storeCard valor a establecer en el set
		 */
		public void setStoreCard(PassDTO storeCard){
			this.storeCard=storeCard;
		}
		/**
		 * Getter de la propiedad storeCard , 
		 * Field name on service:storeCard
		 * @returns Valor de la propiedad storeCard
		 */
		public PassDTO getStoreCard(){
			return storeCard;
		}
		/**
		 * Setter de la propiedad logoImage , 
		 * Field name on service:logoImage
		 * @param logoImage valor a establecer en el set
		 */
		public void setLogoImage(String logoImage){
			this.logoImage=logoImage;
		}
		/**
		 * Getter de la propiedad logoImage , 
		 * Field name on service:logoImage
		 * @returns Valor de la propiedad logoImage
		 */
		public String getLogoImage(){
			return logoImage;
		}
		/**
		 * Setter de la propiedad backgroundImage , 
		 * Field name on service:backgroundImage
		 * @param backgroundImage valor a establecer en el set
		 */
		public void setBackgroundImage(String backgroundImage){
			this.backgroundImage=backgroundImage;
		}
		/**
		 * Getter de la propiedad backgroundImage , 
		 * Field name on service:backgroundImage
		 * @returns Valor de la propiedad backgroundImage
		 */
		public String getBackgroundImage(){
			return backgroundImage;
		}
		/**
		 * Setter de la propiedad stripImage , 
		 * Field name on service:stripImage
		 * @param stripImage valor a establecer en el set
		 */
		public void setStripImage(String stripImage){
			this.stripImage=stripImage;
		}
		/**
		 * Getter de la propiedad stripImage , 
		 * Field name on service:stripImage
		 * @returns Valor de la propiedad stripImage
		 */
		public String getStripImage(){
			return stripImage;
		}
		/**
		 * Setter de la propiedad thumbnailImage , 
		 * Field name on service:thumbnailImage
		 * @param thumbnailImage valor a establecer en el set
		 */
		public void setThumbnailImage(String thumbnailImage){
			this.thumbnailImage=thumbnailImage;
		}
		/**
		 * Getter de la propiedad thumbnailImage , 
		 * Field name on service:thumbnailImage
		 * @returns Valor de la propiedad thumbnailImage
		 */
		public String getThumbnailImage(){
			return thumbnailImage;
		}
		/**
		 * Setter de la propiedad passbookFile , 
		 * Field name on service:passbookFile
		 * @param passbookFile valor a establecer en el set
		 */
		public void setPassbookFile(String passbookFile){
			this.passbookFile=passbookFile;
		}
		/**
		 * Getter de la propiedad passbookFile , 
		 * Field name on service:passbookFile
		 * @returns Valor de la propiedad passbookFile
		 */
		public String getPassbookFile(){
			return passbookFile;
		}

        // Equality stuff

	@Override
	public boolean equals(final Object object) {
		if (this == object) return true;
        	if (!(object instanceof BasePassBookDTO)) return false;
		final BasePassBookDTO other = (BasePassBookDTO) object;
        	boolean equality = true;
		if (getFormatVersion()==null) {
			if(other.getFormatVersion()!=null) {
				return false;
			}
		} else {
			equality = equality && getFormatVersion().equals(other.getFormatVersion());
		}
		if (getSerialNumber()==null) {
			if(other.getSerialNumber()!=null) {
				return false;
			}
		} else {
			equality = equality && getSerialNumber().equals(other.getSerialNumber());
		}
		if (getPassTypeIdentifier()==null) {
			if(other.getPassTypeIdentifier()!=null) {
				return false;
			}
		} else {
			equality = equality && getPassTypeIdentifier().equals(other.getPassTypeIdentifier());
		}
		if (getTeamIdentifier()==null) {
			if(other.getTeamIdentifier()!=null) {
				return false;
			}
		} else {
			equality = equality && getTeamIdentifier().equals(other.getTeamIdentifier());
		}
		if (getAuthenticationToken()==null) {
			if(other.getAuthenticationToken()!=null) {
				return false;
			}
		} else {
			equality = equality && getAuthenticationToken().equals(other.getAuthenticationToken());
		}
		if (getWebServiceURL()==null) {
			if(other.getWebServiceURL()!=null) {
				return false;
			}
		} else {
			equality = equality && getWebServiceURL().equals(other.getWebServiceURL());
		}
		if (getOrganizationName()==null) {
			if(other.getOrganizationName()!=null) {
				return false;
			}
		} else {
			equality = equality && getOrganizationName().equals(other.getOrganizationName());
		}
		if (getDescription()==null) {
			if(other.getDescription()!=null) {
				return false;
			}
		} else {
			equality = equality && getDescription().equals(other.getDescription());
		}
		if (getBarcode()==null) {
			if(other.getBarcode()!=null) {
				return false;
			}
		} else {
			equality = equality && getBarcode().equals(other.getBarcode());
		}
		if (getLocations()==null) {
			if(other.getLocations()!=null) {
				return false;
			}
		} else {
			equality = equality && getLocations().equals(other.getLocations());
		}
		if (getMaxDistance()==null) {
			if(other.getMaxDistance()!=null) {
				return false;
			}
		} else {
			equality = equality && getMaxDistance().equals(other.getMaxDistance());
		}
		if (getRelevantDate()==null) {
			if(other.getRelevantDate()!=null) {
				return false;
			}
		} else {
			equality = equality && getRelevantDate().equals(other.getRelevantDate());
		}
		if (getUpdateDate()==null) {
			if(other.getUpdateDate()!=null) {
				return false;
			}
		} else {
			equality = equality && getUpdateDate().equals(other.getUpdateDate());
		}
		if (getBackgroundColor()==null) {
			if(other.getBackgroundColor()!=null) {
				return false;
			}
		} else {
			equality = equality && getBackgroundColor().equals(other.getBackgroundColor());
		}
		if (getForegroundColor()==null) {
			if(other.getForegroundColor()!=null) {
				return false;
			}
		} else {
			equality = equality && getForegroundColor().equals(other.getForegroundColor());
		}
		if (getLabelColor()==null) {
			if(other.getLabelColor()!=null) {
				return false;
			}
		} else {
			equality = equality && getLabelColor().equals(other.getLabelColor());
		}
		if (getLogoText()==null) {
			if(other.getLogoText()!=null) {
				return false;
			}
		} else {
			equality = equality && getLogoText().equals(other.getLogoText());
		}
		if (getBoardingPass()==null) {
			if(other.getBoardingPass()!=null) {
				return false;
			}
		} else {
			equality = equality && getBoardingPass().equals(other.getBoardingPass());
		}
		if (getCoupon()==null) {
			if(other.getCoupon()!=null) {
				return false;
			}
		} else {
			equality = equality && getCoupon().equals(other.getCoupon());
		}
		if (getEventTicket()==null) {
			if(other.getEventTicket()!=null) {
				return false;
			}
		} else {
			equality = equality && getEventTicket().equals(other.getEventTicket());
		}
		if (getGeneric()==null) {
			if(other.getGeneric()!=null) {
				return false;
			}
		} else {
			equality = equality && getGeneric().equals(other.getGeneric());
		}
		if (getStoreCard()==null) {
			if(other.getStoreCard()!=null) {
				return false;
			}
		} else {
			equality = equality && getStoreCard().equals(other.getStoreCard());
		}
		if (getLogoImage()==null) {
			if(other.getLogoImage()!=null) {
				return false;
			}
		} else {
			equality = equality && getLogoImage().equals(other.getLogoImage());
		}
		if (getBackgroundImage()==null) {
			if(other.getBackgroundImage()!=null) {
				return false;
			}
		} else {
			equality = equality && getBackgroundImage().equals(other.getBackgroundImage());
		}
		if (getStripImage()==null) {
			if(other.getStripImage()!=null) {
				return false;
			}
		} else {
			equality = equality && getStripImage().equals(other.getStripImage());
		}
		if (getThumbnailImage()==null) {
			if(other.getThumbnailImage()!=null) {
				return false;
			}
		} else {
			equality = equality && getThumbnailImage().equals(other.getThumbnailImage());
		}
		if (getPassbookFile()==null) {
			if(other.getPassbookFile()!=null) {
				return false;
			}
		} else {
			equality = equality && getPassbookFile().equals(other.getPassbookFile());
		}
		return equality;
	}

	@Override
	public int hashCode() {
		int result = 0;
		if (getFormatVersion()!=null) {
        	    result = result ^ getFormatVersion().hashCode();
		}
		if (getSerialNumber()!=null) {
        	    result = result ^ getSerialNumber().hashCode();
		}
		if (getPassTypeIdentifier()!=null) {
        	    result = result ^ getPassTypeIdentifier().hashCode();
		}
		if (getTeamIdentifier()!=null) {
        	    result = result ^ getTeamIdentifier().hashCode();
		}
		if (getAuthenticationToken()!=null) {
        	    result = result ^ getAuthenticationToken().hashCode();
		}
		if (getWebServiceURL()!=null) {
        	    result = result ^ getWebServiceURL().hashCode();
		}
		if (getOrganizationName()!=null) {
        	    result = result ^ getOrganizationName().hashCode();
		}
		if (getDescription()!=null) {
        	    result = result ^ getDescription().hashCode();
		}
		if (getBarcode()!=null) {
        	    result = result ^ getBarcode().hashCode();
		}
		if (getLocations()!=null) {
        	    result = result ^ getLocations().hashCode();
		}
		if (getMaxDistance()!=null) {
        	    result = result ^ getMaxDistance().hashCode();
		}
		if (getRelevantDate()!=null) {
        	    result = result ^ getRelevantDate().hashCode();
		}
		if (getUpdateDate()!=null) {
        	    result = result ^ getUpdateDate().hashCode();
		}
		if (getBackgroundColor()!=null) {
        	    result = result ^ getBackgroundColor().hashCode();
		}
		if (getForegroundColor()!=null) {
        	    result = result ^ getForegroundColor().hashCode();
		}
		if (getLabelColor()!=null) {
        	    result = result ^ getLabelColor().hashCode();
		}
		if (getLogoText()!=null) {
        	    result = result ^ getLogoText().hashCode();
		}
		if (getBoardingPass()!=null) {
        	    result = result ^ getBoardingPass().hashCode();
		}
		if (getCoupon()!=null) {
        	    result = result ^ getCoupon().hashCode();
		}
		if (getEventTicket()!=null) {
        	    result = result ^ getEventTicket().hashCode();
		}
		if (getGeneric()!=null) {
        	    result = result ^ getGeneric().hashCode();
		}
		if (getStoreCard()!=null) {
        	    result = result ^ getStoreCard().hashCode();
		}
		if (getLogoImage()!=null) {
        	    result = result ^ getLogoImage().hashCode();
		}
		if (getBackgroundImage()!=null) {
        	    result = result ^ getBackgroundImage().hashCode();
		}
		if (getStripImage()!=null) {
        	    result = result ^ getStripImage().hashCode();
		}
		if (getThumbnailImage()!=null) {
        	    result = result ^ getThumbnailImage().hashCode();
		}
		if (getPassbookFile()!=null) {
        	    result = result ^ getPassbookFile().hashCode();
		}
		return result;
	}

    	// Parcelable stuff
		
		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			if(formatVersion!=null){
                dest.writeByte((byte)1);
    			dest.writeInt(formatVersion);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(serialNumber!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(serialNumber);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(passTypeIdentifier!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(passTypeIdentifier);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(teamIdentifier!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(teamIdentifier);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(authenticationToken!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(authenticationToken);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(webServiceURL!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(webServiceURL);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(organizationName!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(organizationName);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(description!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(description);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(maxDistance!=null){
                dest.writeByte((byte)1);
    			dest.writeInt(maxDistance);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(relevantDate!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(relevantDate);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(updateDate!=null){
                dest.writeByte((byte)1);
			    dest.writeLong(updateDate);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(backgroundColor!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(backgroundColor);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(foregroundColor!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(foregroundColor);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(labelColor!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(labelColor);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(logoText!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(logoText);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(logoImage!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(logoImage);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(backgroundImage!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(backgroundImage);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(stripImage!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(stripImage);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(thumbnailImage!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(thumbnailImage);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(passbookFile!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(passbookFile);
			}
			else{
			    dest.writeByte((byte)0);
			}

			if(barcode!=null){
                dest.writeByte((byte)1);
			    dest.writeParcelable(barcode, flags);
            }
			else{
			    dest.writeByte((byte)0);
			}
			if(boardingPass!=null){
                dest.writeByte((byte)1);
			    dest.writeParcelable(boardingPass, flags);
            }
			else{
			    dest.writeByte((byte)0);
			}
			if(coupon!=null){
                dest.writeByte((byte)1);
			    dest.writeParcelable(coupon, flags);
            }
			else{
			    dest.writeByte((byte)0);
			}
			if(eventTicket!=null){
                dest.writeByte((byte)1);
			    dest.writeParcelable(eventTicket, flags);
            }
			else{
			    dest.writeByte((byte)0);
			}
			if(generic!=null){
                dest.writeByte((byte)1);
			    dest.writeParcelable(generic, flags);
            }
			else{
			    dest.writeByte((byte)0);
			}
			if(storeCard!=null){
                dest.writeByte((byte)1);
			    dest.writeParcelable(storeCard, flags);
            }
			else{
			    dest.writeByte((byte)0);
			}
			if(locations!=null){
                dest.writeByte((byte)1);
			    dest.writeTypedList(locations);
            }
			else{
			    dest.writeByte((byte)0);
			}
		}
		
		public void readFromParcel(Parcel in) {

	
	        if(in.readByte()==1){
			formatVersion = in.readInt();}
	
	        if(in.readByte()==1){
			serialNumber = in.readString();}
	
	        if(in.readByte()==1){
			passTypeIdentifier = in.readString();}
	
	        if(in.readByte()==1){
			teamIdentifier = in.readString();}
	
	        if(in.readByte()==1){
			authenticationToken = in.readString();}
	
	        if(in.readByte()==1){
			webServiceURL = in.readString();}
	
	        if(in.readByte()==1){
			organizationName = in.readString();}
	
	        if(in.readByte()==1){
			description = in.readString();}
	
	        if(in.readByte()==1){
			maxDistance = in.readInt();}
	
	        if(in.readByte()==1){
			relevantDate = in.readString();}
	
	        if(in.readByte()==1){
			updateDate = in.readLong();}
	
	        if(in.readByte()==1){
			backgroundColor = in.readString();}
	
	        if(in.readByte()==1){
			foregroundColor = in.readString();}
	
	        if(in.readByte()==1){
			labelColor = in.readString();}
	
	        if(in.readByte()==1){
			logoText = in.readString();}
	
	        if(in.readByte()==1){
			logoImage = in.readString();}
	
	        if(in.readByte()==1){
			backgroundImage = in.readString();}
	
	        if(in.readByte()==1){
			stripImage = in.readString();}
	
	        if(in.readByte()==1){
			thumbnailImage = in.readString();}
	
	        if(in.readByte()==1){
			passbookFile = in.readString();}
            if(in.readByte()==1)
            {
			barcode = in.readParcelable(BarCodeDTO.class.getClassLoader());
			}
            if(in.readByte()==1)
            {
			boardingPass = in.readParcelable(PassDTO.class.getClassLoader());
			}
            if(in.readByte()==1)
            {
			coupon = in.readParcelable(PassDTO.class.getClassLoader());
			}
            if(in.readByte()==1)
            {
			eventTicket = in.readParcelable(PassDTO.class.getClassLoader());
			}
            if(in.readByte()==1)
            {
			generic = in.readParcelable(PassDTO.class.getClassLoader());
			}
            if(in.readByte()==1)
            {
			storeCard = in.readParcelable(PassDTO.class.getClassLoader());
			}
            if(in.readByte()==1)
            {
			locations = new ArrayList<PassBookLocationDTO>();
			in.readTypedList(locations, PassBookLocationDTO.CREATOR);
			}
		}
		
		public static final Parcelable.Creator<BasePassBookDTO> CREATOR =
			new Parcelable.Creator<BasePassBookDTO>() {
				public BasePassBookDTO createFromParcel(Parcel in) {
					return new BasePassBookDTO(in);
				}
				public BasePassBookDTO[] newArray(int size) {
					return new BasePassBookDTO[size];
				}
			};
			
	}
	/**
	 * DTO defining class PassBookLocationDTO
	 */
	public static class BasePassBookLocationDTO implements Parcelable {
		public BasePassBookLocationDTO(Parcel in) {
			readFromParcel(in);
		}
	
		public BasePassBookLocationDTO() {
		}
	
		// Field name on service:latitude
		private Double latitude; 
		// Field name on service:longitude
		private Double longitude; 
		// Field name on service:relevantText
		private String relevantText; 
		
		// Setters y Getters
		
		/**
		 * Setter de la propiedad latitude , 
		 * Field name on service:latitude
		 * @param latitude valor a establecer en el set
		 */
		public void setLatitude(Double latitude){
			this.latitude=latitude;
		}
		/**
		 * Getter de la propiedad latitude , 
		 * Field name on service:latitude
		 * @returns Valor de la propiedad latitude
		 */
		public Double getLatitude(){
			return latitude;
		}
		/**
		 * Setter de la propiedad longitude , 
		 * Field name on service:longitude
		 * @param longitude valor a establecer en el set
		 */
		public void setLongitude(Double longitude){
			this.longitude=longitude;
		}
		/**
		 * Getter de la propiedad longitude , 
		 * Field name on service:longitude
		 * @returns Valor de la propiedad longitude
		 */
		public Double getLongitude(){
			return longitude;
		}
		/**
		 * Setter de la propiedad relevantText , 
		 * Field name on service:relevantText
		 * @param relevantText valor a establecer en el set
		 */
		public void setRelevantText(String relevantText){
			this.relevantText=relevantText;
		}
		/**
		 * Getter de la propiedad relevantText , 
		 * Field name on service:relevantText
		 * @returns Valor de la propiedad relevantText
		 */
		public String getRelevantText(){
			return relevantText;
		}

        // Equality stuff

	@Override
	public boolean equals(final Object object) {
		if (this == object) return true;
        	if (!(object instanceof BasePassBookLocationDTO)) return false;
		final BasePassBookLocationDTO other = (BasePassBookLocationDTO) object;
        	boolean equality = true;
		if (getLatitude()==null) {
			if(other.getLatitude()!=null) {
				return false;
			}
		} else {
			equality = equality && getLatitude().equals(other.getLatitude());
		}
		if (getLongitude()==null) {
			if(other.getLongitude()!=null) {
				return false;
			}
		} else {
			equality = equality && getLongitude().equals(other.getLongitude());
		}
		if (getRelevantText()==null) {
			if(other.getRelevantText()!=null) {
				return false;
			}
		} else {
			equality = equality && getRelevantText().equals(other.getRelevantText());
		}
		return equality;
	}

	@Override
	public int hashCode() {
		int result = 0;
		if (getLatitude()!=null) {
        	    result = result ^ getLatitude().hashCode();
		}
		if (getLongitude()!=null) {
        	    result = result ^ getLongitude().hashCode();
		}
		if (getRelevantText()!=null) {
        	    result = result ^ getRelevantText().hashCode();
		}
		return result;
	}

    	// Parcelable stuff
		
		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			if(latitude!=null){
                dest.writeByte((byte)1);
                dest.writeDouble(latitude);
            }
			else{
			    dest.writeByte((byte)0);
			}
			if(longitude!=null){
                dest.writeByte((byte)1);
                dest.writeDouble(longitude);
            }
			else{
			    dest.writeByte((byte)0);
			}
			if(relevantText!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(relevantText);
			}
			else{
			    dest.writeByte((byte)0);
			}

		}
		
		public void readFromParcel(Parcel in) {

	
	        if(in.readByte()==1){
			latitude = in.readDouble();}
	
	        if(in.readByte()==1){
			longitude = in.readDouble();}
	
	        if(in.readByte()==1){
			relevantText = in.readString();}
		}
		
		public static final Parcelable.Creator<BasePassBookLocationDTO> CREATOR =
			new Parcelable.Creator<BasePassBookLocationDTO>() {
				public BasePassBookLocationDTO createFromParcel(Parcel in) {
					return new BasePassBookLocationDTO(in);
				}
				public BasePassBookLocationDTO[] newArray(int size) {
					return new BasePassBookLocationDTO[size];
				}
			};
			
	}
	/**
	 * DTO defining class BarCodeDTO
	 */
	public static class BaseBarCodeDTO implements Parcelable {
		public BaseBarCodeDTO(Parcel in) {
			readFromParcel(in);
		}
	
		public BaseBarCodeDTO() {
		}
	
		// Field name on service:format
		private String format; 
		// Field name on service:message
		private String message; 
		// Field name on service:messageEncoding
		private String messageEncoding; 
		// Field name on service:altText
		private String altText; 
		// Field name on service:savedImagePath
		private String savedImagePath; 
		
		// Setters y Getters
		
		/**
		 * Setter de la propiedad format , 
		 * Field name on service:format
		 * @param format valor a establecer en el set
		 */
		public void setFormat(String format){
			this.format=format;
		}
		/**
		 * Getter de la propiedad format , 
		 * Field name on service:format
		 * @returns Valor de la propiedad format
		 */
		public String getFormat(){
			return format;
		}
		/**
		 * Setter de la propiedad message , 
		 * Field name on service:message
		 * @param message valor a establecer en el set
		 */
		public void setMessage(String message){
			this.message=message;
		}
		/**
		 * Getter de la propiedad message , 
		 * Field name on service:message
		 * @returns Valor de la propiedad message
		 */
		public String getMessage(){
			return message;
		}
		/**
		 * Setter de la propiedad messageEncoding , 
		 * Field name on service:messageEncoding
		 * @param messageEncoding valor a establecer en el set
		 */
		public void setMessageEncoding(String messageEncoding){
			this.messageEncoding=messageEncoding;
		}
		/**
		 * Getter de la propiedad messageEncoding , 
		 * Field name on service:messageEncoding
		 * @returns Valor de la propiedad messageEncoding
		 */
		public String getMessageEncoding(){
			return messageEncoding;
		}
		/**
		 * Setter de la propiedad altText , 
		 * Field name on service:altText
		 * @param altText valor a establecer en el set
		 */
		public void setAltText(String altText){
			this.altText=altText;
		}
		/**
		 * Getter de la propiedad altText , 
		 * Field name on service:altText
		 * @returns Valor de la propiedad altText
		 */
		public String getAltText(){
			return altText;
		}
		/**
		 * Setter de la propiedad savedImagePath , 
		 * Field name on service:savedImagePath
		 * @param savedImagePath valor a establecer en el set
		 */
		public void setSavedImagePath(String savedImagePath){
			this.savedImagePath=savedImagePath;
		}
		/**
		 * Getter de la propiedad savedImagePath , 
		 * Field name on service:savedImagePath
		 * @returns Valor de la propiedad savedImagePath
		 */
		public String getSavedImagePath(){
			return savedImagePath;
		}

        // Equality stuff

	@Override
	public boolean equals(final Object object) {
		if (this == object) return true;
        	if (!(object instanceof BaseBarCodeDTO)) return false;
		final BaseBarCodeDTO other = (BaseBarCodeDTO) object;
        	boolean equality = true;
		if (getFormat()==null) {
			if(other.getFormat()!=null) {
				return false;
			}
		} else {
			equality = equality && getFormat().equals(other.getFormat());
		}
		if (getMessage()==null) {
			if(other.getMessage()!=null) {
				return false;
			}
		} else {
			equality = equality && getMessage().equals(other.getMessage());
		}
		if (getMessageEncoding()==null) {
			if(other.getMessageEncoding()!=null) {
				return false;
			}
		} else {
			equality = equality && getMessageEncoding().equals(other.getMessageEncoding());
		}
		if (getAltText()==null) {
			if(other.getAltText()!=null) {
				return false;
			}
		} else {
			equality = equality && getAltText().equals(other.getAltText());
		}
		if (getSavedImagePath()==null) {
			if(other.getSavedImagePath()!=null) {
				return false;
			}
		} else {
			equality = equality && getSavedImagePath().equals(other.getSavedImagePath());
		}
		return equality;
	}

	@Override
	public int hashCode() {
		int result = 0;
		if (getFormat()!=null) {
        	    result = result ^ getFormat().hashCode();
		}
		if (getMessage()!=null) {
        	    result = result ^ getMessage().hashCode();
		}
		if (getMessageEncoding()!=null) {
        	    result = result ^ getMessageEncoding().hashCode();
		}
		if (getAltText()!=null) {
        	    result = result ^ getAltText().hashCode();
		}
		if (getSavedImagePath()!=null) {
        	    result = result ^ getSavedImagePath().hashCode();
		}
		return result;
	}

    	// Parcelable stuff
		
		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			if(format!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(format);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(message!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(message);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(messageEncoding!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(messageEncoding);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(altText!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(altText);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(savedImagePath!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(savedImagePath);
			}
			else{
			    dest.writeByte((byte)0);
			}

		}
		
		public void readFromParcel(Parcel in) {

	
	        if(in.readByte()==1){
			format = in.readString();}
	
	        if(in.readByte()==1){
			message = in.readString();}
	
	        if(in.readByte()==1){
			messageEncoding = in.readString();}
	
	        if(in.readByte()==1){
			altText = in.readString();}
	
	        if(in.readByte()==1){
			savedImagePath = in.readString();}
		}
		
		public static final Parcelable.Creator<BaseBarCodeDTO> CREATOR =
			new Parcelable.Creator<BaseBarCodeDTO>() {
				public BaseBarCodeDTO createFromParcel(Parcel in) {
					return new BaseBarCodeDTO(in);
				}
				public BaseBarCodeDTO[] newArray(int size) {
					return new BaseBarCodeDTO[size];
				}
			};
			
	}
	/**
	 * DTO defining class PassDTO
	 */
	public static class BasePassDTO implements Parcelable {
		public BasePassDTO(Parcel in) {
			readFromParcel(in);
		}
	
		public BasePassDTO() {
		}
	
		// Field name on service:auxiliaryFields
		private List<FieldDictionaryDTO> auxiliaryFields; 
		// Field name on service:backFields
		private List<FieldDictionaryDTO> backFields; 
		// Field name on service:headerFields
		private List<FieldDictionaryDTO> headerFields; 
		// Field name on service:primaryFields
		private List<FieldDictionaryDTO> primaryFields; 
		// Field name on service:secondaryFields
		private List<FieldDictionaryDTO> secondaryFields; 
		// Field name on service:transitType
		private String transitType; 
		
		// Setters y Getters
		
		/**
		 * Setter de la propiedad auxiliaryFields , 
		 * Field name on service:auxiliaryFields
		 * @param auxiliaryFields valor a establecer en el set
		 */
		public void setAuxiliaryFields(List<FieldDictionaryDTO> auxiliaryFields){
			this.auxiliaryFields=auxiliaryFields;
		}
		/**
		 * Getter de la propiedad auxiliaryFields , 
		 * Field name on service:auxiliaryFields
		 * @returns Valor de la propiedad auxiliaryFields
		 */
		public List<FieldDictionaryDTO> getAuxiliaryFields(){
			return auxiliaryFields;
		}
		/**
		 * Setter de la propiedad backFields , 
		 * Field name on service:backFields
		 * @param backFields valor a establecer en el set
		 */
		public void setBackFields(List<FieldDictionaryDTO> backFields){
			this.backFields=backFields;
		}
		/**
		 * Getter de la propiedad backFields , 
		 * Field name on service:backFields
		 * @returns Valor de la propiedad backFields
		 */
		public List<FieldDictionaryDTO> getBackFields(){
			return backFields;
		}
		/**
		 * Setter de la propiedad headerFields , 
		 * Field name on service:headerFields
		 * @param headerFields valor a establecer en el set
		 */
		public void setHeaderFields(List<FieldDictionaryDTO> headerFields){
			this.headerFields=headerFields;
		}
		/**
		 * Getter de la propiedad headerFields , 
		 * Field name on service:headerFields
		 * @returns Valor de la propiedad headerFields
		 */
		public List<FieldDictionaryDTO> getHeaderFields(){
			return headerFields;
		}
		/**
		 * Setter de la propiedad primaryFields , 
		 * Field name on service:primaryFields
		 * @param primaryFields valor a establecer en el set
		 */
		public void setPrimaryFields(List<FieldDictionaryDTO> primaryFields){
			this.primaryFields=primaryFields;
		}
		/**
		 * Getter de la propiedad primaryFields , 
		 * Field name on service:primaryFields
		 * @returns Valor de la propiedad primaryFields
		 */
		public List<FieldDictionaryDTO> getPrimaryFields(){
			return primaryFields;
		}
		/**
		 * Setter de la propiedad secondaryFields , 
		 * Field name on service:secondaryFields
		 * @param secondaryFields valor a establecer en el set
		 */
		public void setSecondaryFields(List<FieldDictionaryDTO> secondaryFields){
			this.secondaryFields=secondaryFields;
		}
		/**
		 * Getter de la propiedad secondaryFields , 
		 * Field name on service:secondaryFields
		 * @returns Valor de la propiedad secondaryFields
		 */
		public List<FieldDictionaryDTO> getSecondaryFields(){
			return secondaryFields;
		}
		/**
		 * Setter de la propiedad transitType , 
		 * Field name on service:transitType
		 * @param transitType valor a establecer en el set
		 */
		public void setTransitType(String transitType){
			this.transitType=transitType;
		}
		/**
		 * Getter de la propiedad transitType , 
		 * Field name on service:transitType
		 * @returns Valor de la propiedad transitType
		 */
		public String getTransitType(){
			return transitType;
		}

        // Equality stuff

	@Override
	public boolean equals(final Object object) {
		if (this == object) return true;
        	if (!(object instanceof BasePassDTO)) return false;
		final BasePassDTO other = (BasePassDTO) object;
        	boolean equality = true;
		if (getAuxiliaryFields()==null) {
			if(other.getAuxiliaryFields()!=null) {
				return false;
			}
		} else {
			equality = equality && getAuxiliaryFields().equals(other.getAuxiliaryFields());
		}
		if (getBackFields()==null) {
			if(other.getBackFields()!=null) {
				return false;
			}
		} else {
			equality = equality && getBackFields().equals(other.getBackFields());
		}
		if (getHeaderFields()==null) {
			if(other.getHeaderFields()!=null) {
				return false;
			}
		} else {
			equality = equality && getHeaderFields().equals(other.getHeaderFields());
		}
		if (getPrimaryFields()==null) {
			if(other.getPrimaryFields()!=null) {
				return false;
			}
		} else {
			equality = equality && getPrimaryFields().equals(other.getPrimaryFields());
		}
		if (getSecondaryFields()==null) {
			if(other.getSecondaryFields()!=null) {
				return false;
			}
		} else {
			equality = equality && getSecondaryFields().equals(other.getSecondaryFields());
		}
		if (getTransitType()==null) {
			if(other.getTransitType()!=null) {
				return false;
			}
		} else {
			equality = equality && getTransitType().equals(other.getTransitType());
		}
		return equality;
	}

	@Override
	public int hashCode() {
		int result = 0;
		if (getAuxiliaryFields()!=null) {
        	    result = result ^ getAuxiliaryFields().hashCode();
		}
		if (getBackFields()!=null) {
        	    result = result ^ getBackFields().hashCode();
		}
		if (getHeaderFields()!=null) {
        	    result = result ^ getHeaderFields().hashCode();
		}
		if (getPrimaryFields()!=null) {
        	    result = result ^ getPrimaryFields().hashCode();
		}
		if (getSecondaryFields()!=null) {
        	    result = result ^ getSecondaryFields().hashCode();
		}
		if (getTransitType()!=null) {
        	    result = result ^ getTransitType().hashCode();
		}
		return result;
	}

    	// Parcelable stuff
		
		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			if(transitType!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(transitType);
			}
			else{
			    dest.writeByte((byte)0);
			}

			if(auxiliaryFields!=null){
                dest.writeByte((byte)1);
			    dest.writeTypedList(auxiliaryFields);
            }
			else{
			    dest.writeByte((byte)0);
			}
			if(backFields!=null){
                dest.writeByte((byte)1);
			    dest.writeTypedList(backFields);
            }
			else{
			    dest.writeByte((byte)0);
			}
			if(headerFields!=null){
                dest.writeByte((byte)1);
			    dest.writeTypedList(headerFields);
            }
			else{
			    dest.writeByte((byte)0);
			}
			if(primaryFields!=null){
                dest.writeByte((byte)1);
			    dest.writeTypedList(primaryFields);
            }
			else{
			    dest.writeByte((byte)0);
			}
			if(secondaryFields!=null){
                dest.writeByte((byte)1);
			    dest.writeTypedList(secondaryFields);
            }
			else{
			    dest.writeByte((byte)0);
			}
		}
		
		public void readFromParcel(Parcel in) {

	
	        if(in.readByte()==1){
			transitType = in.readString();}
            if(in.readByte()==1)
            {
			auxiliaryFields = new ArrayList<FieldDictionaryDTO>();
			in.readTypedList(auxiliaryFields, FieldDictionaryDTO.CREATOR);
			}
            if(in.readByte()==1)
            {
			backFields = new ArrayList<FieldDictionaryDTO>();
			in.readTypedList(backFields, FieldDictionaryDTO.CREATOR);
			}
            if(in.readByte()==1)
            {
			headerFields = new ArrayList<FieldDictionaryDTO>();
			in.readTypedList(headerFields, FieldDictionaryDTO.CREATOR);
			}
            if(in.readByte()==1)
            {
			primaryFields = new ArrayList<FieldDictionaryDTO>();
			in.readTypedList(primaryFields, FieldDictionaryDTO.CREATOR);
			}
            if(in.readByte()==1)
            {
			secondaryFields = new ArrayList<FieldDictionaryDTO>();
			in.readTypedList(secondaryFields, FieldDictionaryDTO.CREATOR);
			}
		}
		
		public static final Parcelable.Creator<BasePassDTO> CREATOR =
			new Parcelable.Creator<BasePassDTO>() {
				public BasePassDTO createFromParcel(Parcel in) {
					return new BasePassDTO(in);
				}
				public BasePassDTO[] newArray(int size) {
					return new BasePassDTO[size];
				}
			};
			
	}
	/**
	 * DTO defining class FieldDictionaryDTO
	 */
	public static class BaseFieldDictionaryDTO implements Parcelable {
		public BaseFieldDictionaryDTO(Parcel in) {
			readFromParcel(in);
		}
	
		public BaseFieldDictionaryDTO() {
		}
	
		// Field name on service:key
		private String key; 
		// Field name on service:value
		private String value; 
		// Field name on service:label
		private String label; 
		// Field name on service:currencyCode
		private String currencyCode; 
		// Field name on service:attributedValue
		private String attributedValue; 
		// Field name on service:changeMessage
		private String changeMessage; 
		// Field name on service:dataDectectorTypes
		private List<String> dataDectectorTypes; 
		// Field name on service:textAlignment
		private String textAlignment; 
		// Field name on service:dateStyle
		private String dateStyle; 
		// Field name on service:timeStyle
		private String timeStyle; 
		
		// Setters y Getters
		
		/**
		 * Setter de la propiedad key , 
		 * Field name on service:key
		 * @param key valor a establecer en el set
		 */
		public void setKey(String key){
			this.key=key;
		}
		/**
		 * Getter de la propiedad key , 
		 * Field name on service:key
		 * @returns Valor de la propiedad key
		 */
		public String getKey(){
			return key;
		}
		/**
		 * Setter de la propiedad value , 
		 * Field name on service:value
		 * @param value valor a establecer en el set
		 */
		public void setValue(String value){
			this.value=value;
		}
		/**
		 * Getter de la propiedad value , 
		 * Field name on service:value
		 * @returns Valor de la propiedad value
		 */
		public String getValue(){
			return value;
		}
		/**
		 * Setter de la propiedad label , 
		 * Field name on service:label
		 * @param label valor a establecer en el set
		 */
		public void setLabel(String label){
			this.label=label;
		}
		/**
		 * Getter de la propiedad label , 
		 * Field name on service:label
		 * @returns Valor de la propiedad label
		 */
		public String getLabel(){
			return label;
		}
		/**
		 * Setter de la propiedad currencyCode , 
		 * Field name on service:currencyCode
		 * @param currencyCode valor a establecer en el set
		 */
		public void setCurrencyCode(String currencyCode){
			this.currencyCode=currencyCode;
		}
		/**
		 * Getter de la propiedad currencyCode , 
		 * Field name on service:currencyCode
		 * @returns Valor de la propiedad currencyCode
		 */
		public String getCurrencyCode(){
			return currencyCode;
		}
		/**
		 * Setter de la propiedad attributedValue , 
		 * Field name on service:attributedValue
		 * @param attributedValue valor a establecer en el set
		 */
		public void setAttributedValue(String attributedValue){
			this.attributedValue=attributedValue;
		}
		/**
		 * Getter de la propiedad attributedValue , 
		 * Field name on service:attributedValue
		 * @returns Valor de la propiedad attributedValue
		 */
		public String getAttributedValue(){
			return attributedValue;
		}
		/**
		 * Setter de la propiedad changeMessage , 
		 * Field name on service:changeMessage
		 * @param changeMessage valor a establecer en el set
		 */
		public void setChangeMessage(String changeMessage){
			this.changeMessage=changeMessage;
		}
		/**
		 * Getter de la propiedad changeMessage , 
		 * Field name on service:changeMessage
		 * @returns Valor de la propiedad changeMessage
		 */
		public String getChangeMessage(){
			return changeMessage;
		}
		/**
		 * Setter de la propiedad dataDectectorTypes , 
		 * Field name on service:dataDectectorTypes
		 * @param dataDectectorTypes valor a establecer en el set
		 */
		public void setDataDectectorTypes(List<String> dataDectectorTypes){
			this.dataDectectorTypes=dataDectectorTypes;
		}
		/**
		 * Getter de la propiedad dataDectectorTypes , 
		 * Field name on service:dataDectectorTypes
		 * @returns Valor de la propiedad dataDectectorTypes
		 */
		public List<String> getDataDectectorTypes(){
			return dataDectectorTypes;
		}
		/**
		 * Setter de la propiedad textAlignment , 
		 * Field name on service:textAlignment
		 * @param textAlignment valor a establecer en el set
		 */
		public void setTextAlignment(String textAlignment){
			this.textAlignment=textAlignment;
		}
		/**
		 * Getter de la propiedad textAlignment , 
		 * Field name on service:textAlignment
		 * @returns Valor de la propiedad textAlignment
		 */
		public String getTextAlignment(){
			return textAlignment;
		}
		/**
		 * Setter de la propiedad dateStyle , 
		 * Field name on service:dateStyle
		 * @param dateStyle valor a establecer en el set
		 */
		public void setDateStyle(String dateStyle){
			this.dateStyle=dateStyle;
		}
		/**
		 * Getter de la propiedad dateStyle , 
		 * Field name on service:dateStyle
		 * @returns Valor de la propiedad dateStyle
		 */
		public String getDateStyle(){
			return dateStyle;
		}
		/**
		 * Setter de la propiedad timeStyle , 
		 * Field name on service:timeStyle
		 * @param timeStyle valor a establecer en el set
		 */
		public void setTimeStyle(String timeStyle){
			this.timeStyle=timeStyle;
		}
		/**
		 * Getter de la propiedad timeStyle , 
		 * Field name on service:timeStyle
		 * @returns Valor de la propiedad timeStyle
		 */
		public String getTimeStyle(){
			return timeStyle;
		}

        // Equality stuff

	@Override
	public boolean equals(final Object object) {
		if (this == object) return true;
        	if (!(object instanceof BaseFieldDictionaryDTO)) return false;
		final BaseFieldDictionaryDTO other = (BaseFieldDictionaryDTO) object;
        	boolean equality = true;
		if (getKey()==null) {
			if(other.getKey()!=null) {
				return false;
			}
		} else {
			equality = equality && getKey().equals(other.getKey());
		}
		if (getValue()==null) {
			if(other.getValue()!=null) {
				return false;
			}
		} else {
			equality = equality && getValue().equals(other.getValue());
		}
		if (getLabel()==null) {
			if(other.getLabel()!=null) {
				return false;
			}
		} else {
			equality = equality && getLabel().equals(other.getLabel());
		}
		if (getCurrencyCode()==null) {
			if(other.getCurrencyCode()!=null) {
				return false;
			}
		} else {
			equality = equality && getCurrencyCode().equals(other.getCurrencyCode());
		}
		if (getAttributedValue()==null) {
			if(other.getAttributedValue()!=null) {
				return false;
			}
		} else {
			equality = equality && getAttributedValue().equals(other.getAttributedValue());
		}
		if (getChangeMessage()==null) {
			if(other.getChangeMessage()!=null) {
				return false;
			}
		} else {
			equality = equality && getChangeMessage().equals(other.getChangeMessage());
		}
		if (getDataDectectorTypes()==null) {
			if(other.getDataDectectorTypes()!=null) {
				return false;
			}
		} else {
			equality = equality && getDataDectectorTypes().equals(other.getDataDectectorTypes());
		}
		if (getTextAlignment()==null) {
			if(other.getTextAlignment()!=null) {
				return false;
			}
		} else {
			equality = equality && getTextAlignment().equals(other.getTextAlignment());
		}
		if (getDateStyle()==null) {
			if(other.getDateStyle()!=null) {
				return false;
			}
		} else {
			equality = equality && getDateStyle().equals(other.getDateStyle());
		}
		if (getTimeStyle()==null) {
			if(other.getTimeStyle()!=null) {
				return false;
			}
		} else {
			equality = equality && getTimeStyle().equals(other.getTimeStyle());
		}
		return equality;
	}

	@Override
	public int hashCode() {
		int result = 0;
		if (getKey()!=null) {
        	    result = result ^ getKey().hashCode();
		}
		if (getValue()!=null) {
        	    result = result ^ getValue().hashCode();
		}
		if (getLabel()!=null) {
        	    result = result ^ getLabel().hashCode();
		}
		if (getCurrencyCode()!=null) {
        	    result = result ^ getCurrencyCode().hashCode();
		}
		if (getAttributedValue()!=null) {
        	    result = result ^ getAttributedValue().hashCode();
		}
		if (getChangeMessage()!=null) {
        	    result = result ^ getChangeMessage().hashCode();
		}
		if (getDataDectectorTypes()!=null) {
        	    result = result ^ getDataDectectorTypes().hashCode();
		}
		if (getTextAlignment()!=null) {
        	    result = result ^ getTextAlignment().hashCode();
		}
		if (getDateStyle()!=null) {
        	    result = result ^ getDateStyle().hashCode();
		}
		if (getTimeStyle()!=null) {
        	    result = result ^ getTimeStyle().hashCode();
		}
		return result;
	}

    	// Parcelable stuff
		
		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			if(key!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(key);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(value!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(value);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(label!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(label);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(currencyCode!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(currencyCode);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(attributedValue!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(attributedValue);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(changeMessage!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(changeMessage);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(textAlignment!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(textAlignment);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(dateStyle!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(dateStyle);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(timeStyle!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(timeStyle);
			}
			else{
			    dest.writeByte((byte)0);
			}

			if(dataDectectorTypes!=null){
                dest.writeByte((byte)1);
			    dest.writeList(dataDectectorTypes);
            }
			else{
			    dest.writeByte((byte)0);
			}
		}
		
		public void readFromParcel(Parcel in) {

	
	        if(in.readByte()==1){
			key = in.readString();}
	
	        if(in.readByte()==1){
			value = in.readString();}
	
	        if(in.readByte()==1){
			label = in.readString();}
	
	        if(in.readByte()==1){
			currencyCode = in.readString();}
	
	        if(in.readByte()==1){
			attributedValue = in.readString();}
	
	        if(in.readByte()==1){
			changeMessage = in.readString();}
	
	        if(in.readByte()==1){
			textAlignment = in.readString();}
	
	        if(in.readByte()==1){
			dateStyle = in.readString();}
	
	        if(in.readByte()==1){
			timeStyle = in.readString();}
            if(in.readByte()==1)
            {
			dataDectectorTypes = new ArrayList<String>(); 
			in.readList(dataDectectorTypes, String.class.getClassLoader());
			}
		}
		
		public static final Parcelable.Creator<BaseFieldDictionaryDTO> CREATOR =
			new Parcelable.Creator<BaseFieldDictionaryDTO>() {
				public BaseFieldDictionaryDTO createFromParcel(Parcel in) {
					return new BaseFieldDictionaryDTO(in);
				}
				public BaseFieldDictionaryDTO[] newArray(int size) {
					return new BaseFieldDictionaryDTO[size];
				}
			};
			
	}
	/**
	 * DTO defining class PassBookCompaniesDTO
	 */
	public static class BasePassBookCompaniesDTO implements Parcelable {
		public BasePassBookCompaniesDTO(Parcel in) {
			readFromParcel(in);
		}
	
		public BasePassBookCompaniesDTO() {
		}
	
		// Field name on service:companies
		private List<PassBookCompanyDTO> companies; 
		
		// Setters y Getters
		
		/**
		 * Setter de la propiedad companies , 
		 * Field name on service:companies
		 * @param companies valor a establecer en el set
		 */
		public void setCompanies(List<PassBookCompanyDTO> companies){
			this.companies=companies;
		}
		/**
		 * Getter de la propiedad companies , 
		 * Field name on service:companies
		 * @returns Valor de la propiedad companies
		 */
		public List<PassBookCompanyDTO> getCompanies(){
			return companies;
		}

        // Equality stuff

	@Override
	public boolean equals(final Object object) {
		if (this == object) return true;
        	if (!(object instanceof BasePassBookCompaniesDTO)) return false;
		final BasePassBookCompaniesDTO other = (BasePassBookCompaniesDTO) object;
        	boolean equality = true;
		if (getCompanies()==null) {
			if(other.getCompanies()!=null) {
				return false;
			}
		} else {
			equality = equality && getCompanies().equals(other.getCompanies());
		}
		return equality;
	}

	@Override
	public int hashCode() {
		int result = 0;
		if (getCompanies()!=null) {
        	    result = result ^ getCompanies().hashCode();
		}
		return result;
	}

    	// Parcelable stuff
		
		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {

			if(companies!=null){
                dest.writeByte((byte)1);
			    dest.writeTypedList(companies);
            }
			else{
			    dest.writeByte((byte)0);
			}
		}
		
		public void readFromParcel(Parcel in) {

            if(in.readByte()==1)
            {
			companies = new ArrayList<PassBookCompanyDTO>();
			in.readTypedList(companies, PassBookCompanyDTO.CREATOR);
			}
		}
		
		public static final Parcelable.Creator<BasePassBookCompaniesDTO> CREATOR =
			new Parcelable.Creator<BasePassBookCompaniesDTO>() {
				public BasePassBookCompaniesDTO createFromParcel(Parcel in) {
					return new BasePassBookCompaniesDTO(in);
				}
				public BasePassBookCompaniesDTO[] newArray(int size) {
					return new BasePassBookCompaniesDTO[size];
				}
			};
			
	}
	/**
	 * DTO defining class PassBookCompanyDTO
	 */
	public static class BasePassBookCompanyDTO implements Parcelable {
		public BasePassBookCompanyDTO(Parcel in) {
			readFromParcel(in);
		}
	
		public BasePassBookCompanyDTO() {
		}
	
		// Field name on service:organizationName
		private String organizationName; 
		// Field name on service:teamIdentifier
		private String teamIdentifier; 
		// Field name on service:passbooksCounter
		private Integer passbooksCounter; 
		
		// Setters y Getters
		
		/**
		 * Setter de la propiedad organizationName , 
		 * Field name on service:organizationName
		 * @param organizationName valor a establecer en el set
		 */
		public void setOrganizationName(String organizationName){
			this.organizationName=organizationName;
		}
		/**
		 * Getter de la propiedad organizationName , 
		 * Field name on service:organizationName
		 * @returns Valor de la propiedad organizationName
		 */
		public String getOrganizationName(){
			return organizationName;
		}
		/**
		 * Setter de la propiedad teamIdentifier , 
		 * Field name on service:teamIdentifier
		 * @param teamIdentifier valor a establecer en el set
		 */
		public void setTeamIdentifier(String teamIdentifier){
			this.teamIdentifier=teamIdentifier;
		}
		/**
		 * Getter de la propiedad teamIdentifier , 
		 * Field name on service:teamIdentifier
		 * @returns Valor de la propiedad teamIdentifier
		 */
		public String getTeamIdentifier(){
			return teamIdentifier;
		}
		/**
		 * Setter de la propiedad passbooksCounter , 
		 * Field name on service:passbooksCounter
		 * @param passbooksCounter valor a establecer en el set
		 */
		public void setPassbooksCounter(Integer passbooksCounter){
			this.passbooksCounter=passbooksCounter;
		}
		/**
		 * Getter de la propiedad passbooksCounter , 
		 * Field name on service:passbooksCounter
		 * @returns Valor de la propiedad passbooksCounter
		 */
		public Integer getPassbooksCounter(){
			return passbooksCounter;
		}

        // Equality stuff

	@Override
	public boolean equals(final Object object) {
		if (this == object) return true;
        	if (!(object instanceof BasePassBookCompanyDTO)) return false;
		final BasePassBookCompanyDTO other = (BasePassBookCompanyDTO) object;
        	boolean equality = true;
		if (getOrganizationName()==null) {
			if(other.getOrganizationName()!=null) {
				return false;
			}
		} else {
			equality = equality && getOrganizationName().equals(other.getOrganizationName());
		}
		if (getTeamIdentifier()==null) {
			if(other.getTeamIdentifier()!=null) {
				return false;
			}
		} else {
			equality = equality && getTeamIdentifier().equals(other.getTeamIdentifier());
		}
		if (getPassbooksCounter()==null) {
			if(other.getPassbooksCounter()!=null) {
				return false;
			}
		} else {
			equality = equality && getPassbooksCounter().equals(other.getPassbooksCounter());
		}
		return equality;
	}

	@Override
	public int hashCode() {
		int result = 0;
		if (getOrganizationName()!=null) {
        	    result = result ^ getOrganizationName().hashCode();
		}
		if (getTeamIdentifier()!=null) {
        	    result = result ^ getTeamIdentifier().hashCode();
		}
		if (getPassbooksCounter()!=null) {
        	    result = result ^ getPassbooksCounter().hashCode();
		}
		return result;
	}

    	// Parcelable stuff
		
		@Override
		public int describeContents() {
			return 0;
		}

		@Override
		public void writeToParcel(Parcel dest, int flags) {
			if(organizationName!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(organizationName);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(teamIdentifier!=null){
			    dest.writeByte((byte)1);
			    dest.writeString(teamIdentifier);
			}
			else{
			    dest.writeByte((byte)0);
			}
			if(passbooksCounter!=null){
                dest.writeByte((byte)1);
    			dest.writeInt(passbooksCounter);
			}
			else{
			    dest.writeByte((byte)0);
			}

		}
		
		public void readFromParcel(Parcel in) {

	
	        if(in.readByte()==1){
			organizationName = in.readString();}
	
	        if(in.readByte()==1){
			teamIdentifier = in.readString();}
	
	        if(in.readByte()==1){
			passbooksCounter = in.readInt();}
		}
		
		public static final Parcelable.Creator<BasePassBookCompanyDTO> CREATOR =
			new Parcelable.Creator<BasePassBookCompanyDTO>() {
				public BasePassBookCompanyDTO createFromParcel(Parcel in) {
					return new BasePassBookCompanyDTO(in);
				}
				public BasePassBookCompanyDTO[] newArray(int size) {
					return new BasePassBookCompanyDTO[size];
				}
			};
			
	}


}
