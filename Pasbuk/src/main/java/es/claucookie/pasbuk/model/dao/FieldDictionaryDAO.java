package es.claucookie.pasbuk.model.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import es.claucookie.pasbuk.model.dto.*;

/**
 * DAO for entity FieldDictionaryDAO, Singleton
 * @author Service Generator
 *
 * Generated Class - DO NOT MODIFY
 */
public final class FieldDictionaryDAO{

	// XML field constants
	private static final String CONSTANT_KEY="key";
	private static final String CONSTANT_VALUE="value";
	private static final String CONSTANT_LABEL="label";
	private static final String CONSTANT_CURRENCYCODE="currencyCode";
	private static final String CONSTANT_ATTRIBUTEDVALUE="attributedValue";
	private static final String CONSTANT_CHANGEMESSAGE="changeMessage";
	private static final String CONSTANT_TEXTALIGNMENT="textAlignment";
	private static final String CONSTANT_DATESTYLE="dateStyle";
	private static final String CONSTANT_TIMESTYLE="timeStyle";
	private static final String CONSTANT_DATADECTECTORTYPES="dataDectectorTypes";
	
	private static FieldDictionaryDAO instance=new FieldDictionaryDAO();

	private FieldDictionaryDAO(){
		
	}
	/**
	 * Gets DAO instance
	 * @returns instance
	 */
	public static FieldDictionaryDAO getInstance(){
		return instance;
	}
	
	/**
	 * Build an FieldDictionaryDTO from JSON
	 */
	public FieldDictionaryDTO create(JSONObject value) throws JSONException{
		FieldDictionaryDTO returnValue=new FieldDictionaryDTO();
		
		
		if(value.has(CONSTANT_KEY) && !value.get(CONSTANT_KEY).toString().equals("null")) {
			returnValue.setKey(value.get(CONSTANT_KEY).toString());
		}
		
		if(value.has(CONSTANT_VALUE) && !value.get(CONSTANT_VALUE).toString().equals("null")) {
			returnValue.setValue(value.get(CONSTANT_VALUE).toString());
		}
		
		if(value.has(CONSTANT_LABEL) && !value.get(CONSTANT_LABEL).toString().equals("null")) {
			returnValue.setLabel(value.get(CONSTANT_LABEL).toString());
		}
		
		if(value.has(CONSTANT_CURRENCYCODE) && !value.get(CONSTANT_CURRENCYCODE).toString().equals("null")) {
			returnValue.setCurrencyCode(value.get(CONSTANT_CURRENCYCODE).toString());
		}
		
		if(value.has(CONSTANT_ATTRIBUTEDVALUE) && !value.get(CONSTANT_ATTRIBUTEDVALUE).toString().equals("null")) {
			returnValue.setAttributedValue(value.get(CONSTANT_ATTRIBUTEDVALUE).toString());
		}
		
		if(value.has(CONSTANT_CHANGEMESSAGE) && !value.get(CONSTANT_CHANGEMESSAGE).toString().equals("null")) {
			returnValue.setChangeMessage(value.get(CONSTANT_CHANGEMESSAGE).toString());
		}
		
		if(value.has(CONSTANT_TEXTALIGNMENT) && !value.get(CONSTANT_TEXTALIGNMENT).toString().equals("null")) {
			returnValue.setTextAlignment(value.get(CONSTANT_TEXTALIGNMENT).toString());
		}
		
		if(value.has(CONSTANT_DATESTYLE) && !value.get(CONSTANT_DATESTYLE).toString().equals("null")) {
			returnValue.setDateStyle(value.get(CONSTANT_DATESTYLE).toString());
		}
		
		if(value.has(CONSTANT_TIMESTYLE) && !value.get(CONSTANT_TIMESTYLE).toString().equals("null")) {
			returnValue.setTimeStyle(value.get(CONSTANT_TIMESTYLE).toString());
		}
		
		ArrayList<String> dataDectectorTypesList=new ArrayList<String>();
		if(value.has(CONSTANT_DATADECTECTORTYPES) && !value.get(CONSTANT_DATADECTECTORTYPES).toString().equals("null")) {

			if(value.get(CONSTANT_DATADECTECTORTYPES) instanceof JSONArray){
				JSONArray dataDectectorTypesArray=value.getJSONArray(CONSTANT_DATADECTECTORTYPES);
				for(int i=0;i!=dataDectectorTypesArray.length();i++){
					dataDectectorTypesList.add(dataDectectorTypesArray.getString(i));
				}
			} else {
				dataDectectorTypesList.add(value.getString(CONSTANT_DATADECTECTORTYPES));
			}
		}
		returnValue.setDataDectectorTypes(dataDectectorTypesList);
		
		

		return returnValue;
	}
	
	/**
	 * Build JSON (JSONObject) from a DTO object
	 */
	public JSONObject serialize(FieldDictionaryDTO object) throws JSONException {		
		JSONObject returnValue = new JSONObject();
		
		if(object.getKey()!=null){
			returnValue.put(CONSTANT_KEY, (object.getKey() == null)? JSONObject.NULL : object.getKey());
		}
		if(object.getValue()!=null){
			returnValue.put(CONSTANT_VALUE, (object.getValue() == null)? JSONObject.NULL : object.getValue());
		}
		if(object.getLabel()!=null){
			returnValue.put(CONSTANT_LABEL, (object.getLabel() == null)? JSONObject.NULL : object.getLabel());
		}
		if(object.getCurrencyCode()!=null){
			returnValue.put(CONSTANT_CURRENCYCODE, (object.getCurrencyCode() == null)? JSONObject.NULL : object.getCurrencyCode());
		}
		if(object.getAttributedValue()!=null){
			returnValue.put(CONSTANT_ATTRIBUTEDVALUE, (object.getAttributedValue() == null)? JSONObject.NULL : object.getAttributedValue());
		}
		if(object.getChangeMessage()!=null){
			returnValue.put(CONSTANT_CHANGEMESSAGE, (object.getChangeMessage() == null)? JSONObject.NULL : object.getChangeMessage());
		}
		if(object.getTextAlignment()!=null){
			returnValue.put(CONSTANT_TEXTALIGNMENT, (object.getTextAlignment() == null)? JSONObject.NULL : object.getTextAlignment());
		}
		if(object.getDateStyle()!=null){
			returnValue.put(CONSTANT_DATESTYLE, (object.getDateStyle() == null)? JSONObject.NULL : object.getDateStyle());
		}
		if(object.getTimeStyle()!=null){
			returnValue.put(CONSTANT_TIMESTYLE, (object.getTimeStyle() == null)? JSONObject.NULL : object.getTimeStyle());
		}
		if(object.getDataDectectorTypes()!=null){
			returnValue.put(CONSTANT_DATADECTECTORTYPES,new JSONArray(object.getDataDectectorTypes()));
		}	

		return returnValue;
	}
}
