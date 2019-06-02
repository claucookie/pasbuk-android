package es.claucookie.pasbuk.model.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import es.claucookie.pasbuk.model.dto.*;

/**
 * DAO for entity PassBookLocationDAO, Singleton
 * @author Service Generator
 *
 * Generated Class - DO NOT MODIFY
 */
public final class PassBookLocationDAO{

	// XML field constants
	private static final String CONSTANT_LATITUDE="latitude";
	private static final String CONSTANT_LONGITUDE="longitude";
	private static final String CONSTANT_RELEVANTTEXT="relevantText";
	
	private static PassBookLocationDAO instance=new PassBookLocationDAO();

	private PassBookLocationDAO(){
		
	}
	/**
	 * Gets DAO instance
	 * @returns instance
	 */
	public static PassBookLocationDAO getInstance(){
		return instance;
	}
	
	/**
	 * Build an PassBookLocationDTO from JSON
	 */
	public PassBookLocationDTO create(JSONObject value) throws JSONException{
		PassBookLocationDTO returnValue=new PassBookLocationDTO();
		
		if(value.has(CONSTANT_LATITUDE) && !value.get(CONSTANT_LATITUDE).toString().equals("null")) {
			returnValue.setLatitude(Double.valueOf(String.valueOf(value.get(CONSTANT_LATITUDE))));			
		}	
		
		if(value.has(CONSTANT_LONGITUDE) && !value.get(CONSTANT_LONGITUDE).toString().equals("null")) {
			returnValue.setLongitude(Double.valueOf(String.valueOf(value.get(CONSTANT_LONGITUDE))));			
		}	
		
		
		if(value.has(CONSTANT_RELEVANTTEXT) && !value.get(CONSTANT_RELEVANTTEXT).toString().equals("null")) {
			returnValue.setRelevantText(value.get(CONSTANT_RELEVANTTEXT).toString());
		}
		
		
		

		return returnValue;
	}
	
	/**
	 * Build JSON (JSONObject) from a DTO object
	 */
	public JSONObject serialize(PassBookLocationDTO object) throws JSONException {		
		JSONObject returnValue = new JSONObject();
		
		if(object.getLatitude()!=null){
			returnValue.put(CONSTANT_LATITUDE, (object.getLatitude() == null)? JSONObject.NULL : object.getLatitude());
		}
		if(object.getLongitude()!=null){
			returnValue.put(CONSTANT_LONGITUDE, (object.getLongitude() == null)? JSONObject.NULL : object.getLongitude());
		}
		if(object.getRelevantText()!=null){
			returnValue.put(CONSTANT_RELEVANTTEXT, (object.getRelevantText() == null)? JSONObject.NULL : object.getRelevantText());
		}

		return returnValue;
	}
}
