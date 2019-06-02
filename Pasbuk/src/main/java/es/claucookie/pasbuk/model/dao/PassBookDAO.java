package es.claucookie.pasbuk.model.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import es.claucookie.pasbuk.model.dto.*;

/**
 * DAO for entity PassBookDAO, Singleton
 * @author Service Generator
 *
 * Generated Class - DO NOT MODIFY
 */
public final class PassBookDAO{

	// XML field constants
	private static final String CONSTANT_FORMATVERSION="formatVersion";
	private static final String CONSTANT_SERIALNUMBER="serialNumber";
	private static final String CONSTANT_PASSTYPEIDENTIFIER="passTypeIdentifier";
	private static final String CONSTANT_TEAMIDENTIFIER="teamIdentifier";
	private static final String CONSTANT_AUTHENTICATIONTOKEN="authenticationToken";
	private static final String CONSTANT_WEBSERVICEURL="webServiceURL";
	private static final String CONSTANT_ORGANIZATIONNAME="organizationName";
	private static final String CONSTANT_DESCRIPTION="description";
	private static final String CONSTANT_MAXDISTANCE="maxDistance";
	private static final String CONSTANT_RELEVANTDATE="relevantDate";
	private static final String CONSTANT_UPDATEDATE="updateDate";
	private static final String CONSTANT_BACKGROUNDCOLOR="backgroundColor";
	private static final String CONSTANT_FOREGROUNDCOLOR="foregroundColor";
	private static final String CONSTANT_LABELCOLOR="labelColor";
	private static final String CONSTANT_LOGOTEXT="logoText";
	private static final String CONSTANT_LOGOIMAGE="logoImage";
	private static final String CONSTANT_BACKGROUNDIMAGE="backgroundImage";
	private static final String CONSTANT_STRIPIMAGE="stripImage";
	private static final String CONSTANT_THUMBNAILIMAGE="thumbnailImage";
	private static final String CONSTANT_PASSBOOKFILE="passbookFile";
	private static final String CONSTANT_BARCODE="barcode";
	private static final String CONSTANT_BOARDINGPASS="boardingPass";
	private static final String CONSTANT_COUPON="coupon";
	private static final String CONSTANT_EVENTTICKET="eventTicket";
	private static final String CONSTANT_GENERIC="generic";
	private static final String CONSTANT_STORECARD="storeCard";
	private static final String CONSTANT_LOCATIONS="locations";
	
	private static PassBookDAO instance=new PassBookDAO();

	private PassBookDAO(){
		
	}
	/**
	 * Gets DAO instance
	 * @returns instance
	 */
	public static PassBookDAO getInstance(){
		return instance;
	}
	
	/**
	 * Build an PassBookDTO from JSON
	 */
	public PassBookDTO create(JSONObject value) throws JSONException{
		PassBookDTO returnValue=new PassBookDTO();
		
		if(value.has(CONSTANT_FORMATVERSION) && !value.get(CONSTANT_FORMATVERSION).toString().equals("null")) {
			if(value.get(CONSTANT_FORMATVERSION).getClass()==String.class){
				returnValue.setFormatVersion(Integer.parseInt((String)value.get(CONSTANT_FORMATVERSION)));
			}
			else{
				returnValue.setFormatVersion((Integer)value.get(CONSTANT_FORMATVERSION));
			}
		}	
		
		
		if(value.has(CONSTANT_SERIALNUMBER) && !value.get(CONSTANT_SERIALNUMBER).toString().equals("null")) {
			returnValue.setSerialNumber(value.get(CONSTANT_SERIALNUMBER).toString());
		}
		
		if(value.has(CONSTANT_PASSTYPEIDENTIFIER) && !value.get(CONSTANT_PASSTYPEIDENTIFIER).toString().equals("null")) {
			returnValue.setPassTypeIdentifier(value.get(CONSTANT_PASSTYPEIDENTIFIER).toString());
		}
		
		if(value.has(CONSTANT_TEAMIDENTIFIER) && !value.get(CONSTANT_TEAMIDENTIFIER).toString().equals("null")) {
			returnValue.setTeamIdentifier(value.get(CONSTANT_TEAMIDENTIFIER).toString());
		}
		
		if(value.has(CONSTANT_AUTHENTICATIONTOKEN) && !value.get(CONSTANT_AUTHENTICATIONTOKEN).toString().equals("null")) {
			returnValue.setAuthenticationToken(value.get(CONSTANT_AUTHENTICATIONTOKEN).toString());
		}
		
		if(value.has(CONSTANT_WEBSERVICEURL) && !value.get(CONSTANT_WEBSERVICEURL).toString().equals("null")) {
			returnValue.setWebServiceURL(value.get(CONSTANT_WEBSERVICEURL).toString());
		}
		
		if(value.has(CONSTANT_ORGANIZATIONNAME) && !value.get(CONSTANT_ORGANIZATIONNAME).toString().equals("null")) {
			returnValue.setOrganizationName(value.get(CONSTANT_ORGANIZATIONNAME).toString());
		}
		
		if(value.has(CONSTANT_DESCRIPTION) && !value.get(CONSTANT_DESCRIPTION).toString().equals("null")) {
			returnValue.setDescription(value.get(CONSTANT_DESCRIPTION).toString());
		}
		if(value.has(CONSTANT_MAXDISTANCE) && !value.get(CONSTANT_MAXDISTANCE).toString().equals("null")) {
			if(value.get(CONSTANT_MAXDISTANCE).getClass()==String.class){
				returnValue.setMaxDistance(Integer.parseInt((String)value.get(CONSTANT_MAXDISTANCE)));
			}
			else{
				returnValue.setMaxDistance((Integer)value.get(CONSTANT_MAXDISTANCE));
			}
		}	
		
		
		if(value.has(CONSTANT_RELEVANTDATE) && !value.get(CONSTANT_RELEVANTDATE).toString().equals("null")) {
			returnValue.setRelevantDate(value.get(CONSTANT_RELEVANTDATE).toString());
		}
		if(value.has(CONSTANT_UPDATEDATE) && !value.get(CONSTANT_UPDATEDATE).toString().equals("null")) {
			returnValue.setUpdateDate(Long.parseLong(String.valueOf(value.get(CONSTANT_UPDATEDATE))));
		}
		
		
		if(value.has(CONSTANT_BACKGROUNDCOLOR) && !value.get(CONSTANT_BACKGROUNDCOLOR).toString().equals("null")) {
			returnValue.setBackgroundColor(value.get(CONSTANT_BACKGROUNDCOLOR).toString());
		}
		
		if(value.has(CONSTANT_FOREGROUNDCOLOR) && !value.get(CONSTANT_FOREGROUNDCOLOR).toString().equals("null")) {
			returnValue.setForegroundColor(value.get(CONSTANT_FOREGROUNDCOLOR).toString());
		}
		
		if(value.has(CONSTANT_LABELCOLOR) && !value.get(CONSTANT_LABELCOLOR).toString().equals("null")) {
			returnValue.setLabelColor(value.get(CONSTANT_LABELCOLOR).toString());
		}
		
		if(value.has(CONSTANT_LOGOTEXT) && !value.get(CONSTANT_LOGOTEXT).toString().equals("null")) {
			returnValue.setLogoText(value.get(CONSTANT_LOGOTEXT).toString());
		}
		
		if(value.has(CONSTANT_LOGOIMAGE) && !value.get(CONSTANT_LOGOIMAGE).toString().equals("null")) {
			returnValue.setLogoImage(value.get(CONSTANT_LOGOIMAGE).toString());
		}
		
		if(value.has(CONSTANT_BACKGROUNDIMAGE) && !value.get(CONSTANT_BACKGROUNDIMAGE).toString().equals("null")) {
			returnValue.setBackgroundImage(value.get(CONSTANT_BACKGROUNDIMAGE).toString());
		}
		
		if(value.has(CONSTANT_STRIPIMAGE) && !value.get(CONSTANT_STRIPIMAGE).toString().equals("null")) {
			returnValue.setStripImage(value.get(CONSTANT_STRIPIMAGE).toString());
		}
		
		if(value.has(CONSTANT_THUMBNAILIMAGE) && !value.get(CONSTANT_THUMBNAILIMAGE).toString().equals("null")) {
			returnValue.setThumbnailImage(value.get(CONSTANT_THUMBNAILIMAGE).toString());
		}
		
		if(value.has(CONSTANT_PASSBOOKFILE) && !value.get(CONSTANT_PASSBOOKFILE).toString().equals("null")) {
			returnValue.setPassbookFile(value.get(CONSTANT_PASSBOOKFILE).toString());
		}
		
		
		if(value.has(CONSTANT_BARCODE) && !value.get(CONSTANT_BARCODE).toString().equals("null")) {
			BarCodeDTO barcode=BarCodeDAO.getInstance().create((JSONObject)value.get(CONSTANT_BARCODE));
			returnValue.setBarcode(barcode);
		}
		if(value.has(CONSTANT_BOARDINGPASS) && !value.get(CONSTANT_BOARDINGPASS).toString().equals("null")) {
			PassDTO boardingPass=PassDAO.getInstance().create((JSONObject)value.get(CONSTANT_BOARDINGPASS));
			returnValue.setBoardingPass(boardingPass);
		}
		if(value.has(CONSTANT_COUPON) && !value.get(CONSTANT_COUPON).toString().equals("null")) {
			PassDTO coupon=PassDAO.getInstance().create((JSONObject)value.get(CONSTANT_COUPON));
			returnValue.setCoupon(coupon);
		}
		if(value.has(CONSTANT_EVENTTICKET) && !value.get(CONSTANT_EVENTTICKET).toString().equals("null")) {
			PassDTO eventTicket=PassDAO.getInstance().create((JSONObject)value.get(CONSTANT_EVENTTICKET));
			returnValue.setEventTicket(eventTicket);
		}
		if(value.has(CONSTANT_GENERIC) && !value.get(CONSTANT_GENERIC).toString().equals("null")) {
			PassDTO generic=PassDAO.getInstance().create((JSONObject)value.get(CONSTANT_GENERIC));
			returnValue.setGeneric(generic);
		}
		if(value.has(CONSTANT_STORECARD) && !value.get(CONSTANT_STORECARD).toString().equals("null")) {
			PassDTO storeCard=PassDAO.getInstance().create((JSONObject)value.get(CONSTANT_STORECARD));
			returnValue.setStoreCard(storeCard);
		}
		
		ArrayList<PassBookLocationDTO> locationsList=new ArrayList<PassBookLocationDTO>();
		if(value.has(CONSTANT_LOCATIONS) && !value.get(CONSTANT_LOCATIONS).toString().equals("null")) {
			if(value.get(CONSTANT_LOCATIONS) instanceof JSONArray){
				JSONArray locationsArray=(JSONArray)value.get(CONSTANT_LOCATIONS);
				for(int i=0;i!=locationsArray.length();i++){
					JSONObject obj=(JSONObject)locationsArray.get(i);
					locationsList.add(PassBookLocationDAO.getInstance().create(obj));
				}
			}
			else{
				locationsList.add(PassBookLocationDAO.getInstance().create((JSONObject)value.get(CONSTANT_LOCATIONS)));
			}
		}
		returnValue.setLocations(locationsList);

		return returnValue;
	}
	
	/**
	 * Build JSON (JSONObject) from a DTO object
	 */
	public JSONObject serialize(PassBookDTO object) throws JSONException {		
		JSONObject returnValue = new JSONObject();
		
		if(object.getFormatVersion()!=null){
			returnValue.put(CONSTANT_FORMATVERSION, (object.getFormatVersion() == null)? JSONObject.NULL : object.getFormatVersion());
		}
		if(object.getSerialNumber()!=null){
			returnValue.put(CONSTANT_SERIALNUMBER, (object.getSerialNumber() == null)? JSONObject.NULL : object.getSerialNumber());
		}
		if(object.getPassTypeIdentifier()!=null){
			returnValue.put(CONSTANT_PASSTYPEIDENTIFIER, (object.getPassTypeIdentifier() == null)? JSONObject.NULL : object.getPassTypeIdentifier());
		}
		if(object.getTeamIdentifier()!=null){
			returnValue.put(CONSTANT_TEAMIDENTIFIER, (object.getTeamIdentifier() == null)? JSONObject.NULL : object.getTeamIdentifier());
		}
		if(object.getAuthenticationToken()!=null){
			returnValue.put(CONSTANT_AUTHENTICATIONTOKEN, (object.getAuthenticationToken() == null)? JSONObject.NULL : object.getAuthenticationToken());
		}
		if(object.getWebServiceURL()!=null){
			returnValue.put(CONSTANT_WEBSERVICEURL, (object.getWebServiceURL() == null)? JSONObject.NULL : object.getWebServiceURL());
		}
		if(object.getOrganizationName()!=null){
			returnValue.put(CONSTANT_ORGANIZATIONNAME, (object.getOrganizationName() == null)? JSONObject.NULL : object.getOrganizationName());
		}
		if(object.getDescription()!=null){
			returnValue.put(CONSTANT_DESCRIPTION, (object.getDescription() == null)? JSONObject.NULL : object.getDescription());
		}
		if(object.getMaxDistance()!=null){
			returnValue.put(CONSTANT_MAXDISTANCE, (object.getMaxDistance() == null)? JSONObject.NULL : object.getMaxDistance());
		}
		if(object.getRelevantDate()!=null){
			returnValue.put(CONSTANT_RELEVANTDATE, (object.getRelevantDate() == null)? JSONObject.NULL : object.getRelevantDate());
		}
		if(object.getUpdateDate()!=null){
			returnValue.put(CONSTANT_UPDATEDATE, (object.getUpdateDate() == null)? JSONObject.NULL : object.getUpdateDate());
		}
		if(object.getBackgroundColor()!=null){
			returnValue.put(CONSTANT_BACKGROUNDCOLOR, (object.getBackgroundColor() == null)? JSONObject.NULL : object.getBackgroundColor());
		}
		if(object.getForegroundColor()!=null){
			returnValue.put(CONSTANT_FOREGROUNDCOLOR, (object.getForegroundColor() == null)? JSONObject.NULL : object.getForegroundColor());
		}
		if(object.getLabelColor()!=null){
			returnValue.put(CONSTANT_LABELCOLOR, (object.getLabelColor() == null)? JSONObject.NULL : object.getLabelColor());
		}
		if(object.getLogoText()!=null){
			returnValue.put(CONSTANT_LOGOTEXT, (object.getLogoText() == null)? JSONObject.NULL : object.getLogoText());
		}
		if(object.getLogoImage()!=null){
			returnValue.put(CONSTANT_LOGOIMAGE, (object.getLogoImage() == null)? JSONObject.NULL : object.getLogoImage());
		}
		if(object.getBackgroundImage()!=null){
			returnValue.put(CONSTANT_BACKGROUNDIMAGE, (object.getBackgroundImage() == null)? JSONObject.NULL : object.getBackgroundImage());
		}
		if(object.getStripImage()!=null){
			returnValue.put(CONSTANT_STRIPIMAGE, (object.getStripImage() == null)? JSONObject.NULL : object.getStripImage());
		}
		if(object.getThumbnailImage()!=null){
			returnValue.put(CONSTANT_THUMBNAILIMAGE, (object.getThumbnailImage() == null)? JSONObject.NULL : object.getThumbnailImage());
		}
		if(object.getPassbookFile()!=null){
			returnValue.put(CONSTANT_PASSBOOKFILE, (object.getPassbookFile() == null)? JSONObject.NULL : object.getPassbookFile());
		}
		if(object.getBarcode()!=null){
			returnValue.put(CONSTANT_BARCODE,BarCodeDAO.getInstance().serialize(object.getBarcode()));
		}
		if(object.getBoardingPass()!=null){
			returnValue.put(CONSTANT_BOARDINGPASS,PassDAO.getInstance().serialize(object.getBoardingPass()));
		}
		if(object.getCoupon()!=null){
			returnValue.put(CONSTANT_COUPON,PassDAO.getInstance().serialize(object.getCoupon()));
		}
		if(object.getEventTicket()!=null){
			returnValue.put(CONSTANT_EVENTTICKET,PassDAO.getInstance().serialize(object.getEventTicket()));
		}
		if(object.getGeneric()!=null){
			returnValue.put(CONSTANT_GENERIC,PassDAO.getInstance().serialize(object.getGeneric()));
		}
		if(object.getStoreCard()!=null){
			returnValue.put(CONSTANT_STORECARD,PassDAO.getInstance().serialize(object.getStoreCard()));
		}
		if(object.getLocations()!=null){
			JSONArray locationsArray = new JSONArray();
			for (PassBookLocationDTO locationsObject : object.getLocations()) {
				locationsArray.put(PassBookLocationDAO.getInstance().serialize(locationsObject));
			}
			returnValue.put(CONSTANT_LOCATIONS,locationsArray);
		}

		return returnValue;
	}
}
