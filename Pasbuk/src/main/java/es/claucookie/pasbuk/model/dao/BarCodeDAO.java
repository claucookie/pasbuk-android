package es.claucookie.pasbuk.model.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import es.claucookie.pasbuk.model.dto.*;

/**
 * DAO for entity BarCodeDAO, Singleton
 * @author Service Generator
 *
 * Generated Class - DO NOT MODIFY
 */
public final class BarCodeDAO{

	// XML field constants
	private static final String CONSTANT_FORMAT="format";
	private static final String CONSTANT_MESSAGE="message";
	private static final String CONSTANT_MESSAGEENCODING="messageEncoding";
	private static final String CONSTANT_ALTTEXT="altText";
	private static final String CONSTANT_SAVEDIMAGEPATH="savedImagePath";
	
	private static BarCodeDAO instance=new BarCodeDAO();

	private BarCodeDAO(){
		
	}
	/**
	 * Gets DAO instance
	 * @returns instance
	 */
	public static BarCodeDAO getInstance(){
		return instance;
	}
	
	/**
	 * Build an BarCodeDTO from JSON
	 */
	public BarCodeDTO create(JSONObject value) throws JSONException{
		BarCodeDTO returnValue=new BarCodeDTO();
		
		
		if(value.has(CONSTANT_FORMAT) && !value.get(CONSTANT_FORMAT).toString().equals("null")) {
			returnValue.setFormat(value.get(CONSTANT_FORMAT).toString());
		}
		
		if(value.has(CONSTANT_MESSAGE) && !value.get(CONSTANT_MESSAGE).toString().equals("null")) {
			returnValue.setMessage(value.get(CONSTANT_MESSAGE).toString());
		}
		
		if(value.has(CONSTANT_MESSAGEENCODING) && !value.get(CONSTANT_MESSAGEENCODING).toString().equals("null")) {
			returnValue.setMessageEncoding(value.get(CONSTANT_MESSAGEENCODING).toString());
		}
		
		if(value.has(CONSTANT_ALTTEXT) && !value.get(CONSTANT_ALTTEXT).toString().equals("null")) {
			returnValue.setAltText(value.get(CONSTANT_ALTTEXT).toString());
		}
		
		if(value.has(CONSTANT_SAVEDIMAGEPATH) && !value.get(CONSTANT_SAVEDIMAGEPATH).toString().equals("null")) {
			returnValue.setSavedImagePath(value.get(CONSTANT_SAVEDIMAGEPATH).toString());
		}
		
		
		

		return returnValue;
	}
	
	/**
	 * Build JSON (JSONObject) from a DTO object
	 */
	public JSONObject serialize(BarCodeDTO object) throws JSONException {		
		JSONObject returnValue = new JSONObject();
		
		if(object.getFormat()!=null){
			returnValue.put(CONSTANT_FORMAT, (object.getFormat() == null)? JSONObject.NULL : object.getFormat());
		}
		if(object.getMessage()!=null){
			returnValue.put(CONSTANT_MESSAGE, (object.getMessage() == null)? JSONObject.NULL : object.getMessage());
		}
		if(object.getMessageEncoding()!=null){
			returnValue.put(CONSTANT_MESSAGEENCODING, (object.getMessageEncoding() == null)? JSONObject.NULL : object.getMessageEncoding());
		}
		if(object.getAltText()!=null){
			returnValue.put(CONSTANT_ALTTEXT, (object.getAltText() == null)? JSONObject.NULL : object.getAltText());
		}
		if(object.getSavedImagePath()!=null){
			returnValue.put(CONSTANT_SAVEDIMAGEPATH, (object.getSavedImagePath() == null)? JSONObject.NULL : object.getSavedImagePath());
		}

		return returnValue;
	}
}
