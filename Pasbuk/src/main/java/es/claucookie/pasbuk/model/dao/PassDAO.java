package es.claucookie.pasbuk.model.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import es.claucookie.pasbuk.model.dto.*;

/**
 * DAO for entity PassDAO, Singleton
 * @author Service Generator
 *
 * Generated Class - DO NOT MODIFY
 */
public final class PassDAO{

	// XML field constants
	private static final String CONSTANT_TRANSITTYPE="transitType";
	private static final String CONSTANT_AUXILIARYFIELDS="auxiliaryFields";
	private static final String CONSTANT_BACKFIELDS="backFields";
	private static final String CONSTANT_HEADERFIELDS="headerFields";
	private static final String CONSTANT_PRIMARYFIELDS="primaryFields";
	private static final String CONSTANT_SECONDARYFIELDS="secondaryFields";

	private static PassDAO instance=new PassDAO();

	private PassDAO(){

	}
	/**
	 * Gets DAO instance
	 * @returns instance
	 */
	public static PassDAO getInstance(){
		return instance;
	}

	/**
	 * Build an PassDTO from JSON
	 */
	public PassDTO create(JSONObject value) throws JSONException{
		PassDTO returnValue=new PassDTO();


		if(value.has(CONSTANT_TRANSITTYPE) && !value.get(CONSTANT_TRANSITTYPE).toString().equals("null")) {
			returnValue.setTransitType(value.get(CONSTANT_TRANSITTYPE).toString());
		}



		ArrayList<FieldDictionaryDTO> auxiliaryFieldsList=new ArrayList<FieldDictionaryDTO>();
		if(value.has(CONSTANT_AUXILIARYFIELDS) && !value.get(CONSTANT_AUXILIARYFIELDS).toString().equals("null")) {
			if(value.get(CONSTANT_AUXILIARYFIELDS) instanceof JSONArray){
				JSONArray auxiliaryFieldsArray=(JSONArray)value.get(CONSTANT_AUXILIARYFIELDS);
				for(int i=0;i!=auxiliaryFieldsArray.length();i++){
					JSONObject obj=(JSONObject)auxiliaryFieldsArray.get(i);
					auxiliaryFieldsList.add(FieldDictionaryDAO.getInstance().create(obj));
				}
			}
			else{
				auxiliaryFieldsList.add(FieldDictionaryDAO.getInstance().create((JSONObject)value.get(CONSTANT_AUXILIARYFIELDS)));
			}
		}
		returnValue.setAuxiliaryFields(auxiliaryFieldsList);
		ArrayList<FieldDictionaryDTO> backFieldsList=new ArrayList<FieldDictionaryDTO>();
		if(value.has(CONSTANT_BACKFIELDS) && !value.get(CONSTANT_BACKFIELDS).toString().equals("null")) {
			if(value.get(CONSTANT_BACKFIELDS) instanceof JSONArray){
				JSONArray backFieldsArray=(JSONArray)value.get(CONSTANT_BACKFIELDS);
				for(int i=0;i!=backFieldsArray.length();i++){
					if (backFieldsArray.opt(i) != null) {
						JSONObject obj = (JSONObject) backFieldsArray.get(i);
                        backFieldsList.add(FieldDictionaryDAO.getInstance().create(obj));
					}
				}
			}
			else{
				backFieldsList.add(FieldDictionaryDAO.getInstance().create((JSONObject)value.get(CONSTANT_BACKFIELDS)));
			}
		}
		returnValue.setBackFields(backFieldsList);
		ArrayList<FieldDictionaryDTO> headerFieldsList=new ArrayList<FieldDictionaryDTO>();
		if(value.has(CONSTANT_HEADERFIELDS) && !value.get(CONSTANT_HEADERFIELDS).toString().equals("null")) {
			if(value.get(CONSTANT_HEADERFIELDS) instanceof JSONArray){
				JSONArray headerFieldsArray=(JSONArray)value.get(CONSTANT_HEADERFIELDS);
				for(int i=0;i!=headerFieldsArray.length();i++){
					JSONObject obj=(JSONObject)headerFieldsArray.get(i);
					headerFieldsList.add(FieldDictionaryDAO.getInstance().create(obj));
				}
			}
			else{
				headerFieldsList.add(FieldDictionaryDAO.getInstance().create((JSONObject)value.get(CONSTANT_HEADERFIELDS)));
			}
		}
		returnValue.setHeaderFields(headerFieldsList);
		ArrayList<FieldDictionaryDTO> primaryFieldsList=new ArrayList<FieldDictionaryDTO>();
		if(value.has(CONSTANT_PRIMARYFIELDS) && !value.get(CONSTANT_PRIMARYFIELDS).toString().equals("null")) {
			if(value.get(CONSTANT_PRIMARYFIELDS) instanceof JSONArray){
				JSONArray primaryFieldsArray=(JSONArray)value.get(CONSTANT_PRIMARYFIELDS);
				for(int i=0;i!=primaryFieldsArray.length();i++){
					JSONObject obj=(JSONObject)primaryFieldsArray.get(i);
					primaryFieldsList.add(FieldDictionaryDAO.getInstance().create(obj));
				}
			}
			else{
				primaryFieldsList.add(FieldDictionaryDAO.getInstance().create((JSONObject)value.get(CONSTANT_PRIMARYFIELDS)));
			}
		}
		returnValue.setPrimaryFields(primaryFieldsList);
		ArrayList<FieldDictionaryDTO> secondaryFieldsList=new ArrayList<FieldDictionaryDTO>();
		if(value.has(CONSTANT_SECONDARYFIELDS) && !value.get(CONSTANT_SECONDARYFIELDS).toString().equals("null")) {
			if(value.get(CONSTANT_SECONDARYFIELDS) instanceof JSONArray){
				JSONArray secondaryFieldsArray=(JSONArray)value.get(CONSTANT_SECONDARYFIELDS);
				for(int i=0;i!=secondaryFieldsArray.length();i++){
					JSONObject obj=(JSONObject)secondaryFieldsArray.get(i);
					secondaryFieldsList.add(FieldDictionaryDAO.getInstance().create(obj));
				}
			}
			else{
				secondaryFieldsList.add(FieldDictionaryDAO.getInstance().create((JSONObject)value.get(CONSTANT_SECONDARYFIELDS)));
			}
		}
		returnValue.setSecondaryFields(secondaryFieldsList);

		return returnValue;
	}

	/**
	 * Build JSON (JSONObject) from a DTO object
	 */
	public JSONObject serialize(PassDTO object) throws JSONException {
		JSONObject returnValue = new JSONObject();

		if(object.getTransitType()!=null){
			returnValue.put(CONSTANT_TRANSITTYPE, (object.getTransitType() == null)? JSONObject.NULL : object.getTransitType());
		}
		if(object.getAuxiliaryFields()!=null){
			JSONArray auxiliaryFieldsArray = new JSONArray();
			for (FieldDictionaryDTO auxiliaryFieldsObject : object.getAuxiliaryFields()) {
				auxiliaryFieldsArray.put(FieldDictionaryDAO.getInstance().serialize(auxiliaryFieldsObject));
			}
			returnValue.put(CONSTANT_AUXILIARYFIELDS,auxiliaryFieldsArray);
		}
		if(object.getBackFields()!=null){
			JSONArray backFieldsArray = new JSONArray();
			for (FieldDictionaryDTO backFieldsObject : object.getBackFields()) {
				backFieldsArray.put(FieldDictionaryDAO.getInstance().serialize(backFieldsObject));
			}
			returnValue.put(CONSTANT_BACKFIELDS,backFieldsArray);
		}
		if(object.getHeaderFields()!=null){
			JSONArray headerFieldsArray = new JSONArray();
			for (FieldDictionaryDTO headerFieldsObject : object.getHeaderFields()) {
				headerFieldsArray.put(FieldDictionaryDAO.getInstance().serialize(headerFieldsObject));
			}
			returnValue.put(CONSTANT_HEADERFIELDS,headerFieldsArray);
		}
		if(object.getPrimaryFields()!=null){
			JSONArray primaryFieldsArray = new JSONArray();
			for (FieldDictionaryDTO primaryFieldsObject : object.getPrimaryFields()) {
				primaryFieldsArray.put(FieldDictionaryDAO.getInstance().serialize(primaryFieldsObject));
			}
			returnValue.put(CONSTANT_PRIMARYFIELDS,primaryFieldsArray);
		}
		if(object.getSecondaryFields()!=null){
			JSONArray secondaryFieldsArray = new JSONArray();
			for (FieldDictionaryDTO secondaryFieldsObject : object.getSecondaryFields()) {
				secondaryFieldsArray.put(FieldDictionaryDAO.getInstance().serialize(secondaryFieldsObject));
			}
			returnValue.put(CONSTANT_SECONDARYFIELDS,secondaryFieldsArray);
		}

		return returnValue;
	}
}
