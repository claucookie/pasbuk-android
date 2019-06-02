package es.claucookie.pasbuk.model.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import es.claucookie.pasbuk.model.dto.*;

/**
 * DAO for entity PassBookCompanyDAO, Singleton
 * @author Service Generator
 *
 * Generated Class - DO NOT MODIFY
 */
public final class PassBookCompanyDAO{

	// XML field constants
	private static final String CONSTANT_ORGANIZATIONNAME="organizationName";
	private static final String CONSTANT_TEAMIDENTIFIER="teamIdentifier";
	private static final String CONSTANT_PASSBOOKSCOUNTER="passbooksCounter";
	
	private static PassBookCompanyDAO instance=new PassBookCompanyDAO();

	private PassBookCompanyDAO(){
		
	}
	/**
	 * Gets DAO instance
	 * @returns instance
	 */
	public static PassBookCompanyDAO getInstance(){
		return instance;
	}
	
	/**
	 * Build an PassBookCompanyDTO from JSON
	 */
	public PassBookCompanyDTO create(JSONObject value) throws JSONException{
		PassBookCompanyDTO returnValue=new PassBookCompanyDTO();
		
		
		if(value.has(CONSTANT_ORGANIZATIONNAME) && !value.get(CONSTANT_ORGANIZATIONNAME).toString().equals("null")) {
			returnValue.setOrganizationName(value.get(CONSTANT_ORGANIZATIONNAME).toString());
		}
		
		if(value.has(CONSTANT_TEAMIDENTIFIER) && !value.get(CONSTANT_TEAMIDENTIFIER).toString().equals("null")) {
			returnValue.setTeamIdentifier(value.get(CONSTANT_TEAMIDENTIFIER).toString());
		}
		if(value.has(CONSTANT_PASSBOOKSCOUNTER) && !value.get(CONSTANT_PASSBOOKSCOUNTER).toString().equals("null")) {
			if(value.get(CONSTANT_PASSBOOKSCOUNTER).getClass()==String.class){
				returnValue.setPassbooksCounter(Integer.parseInt((String)value.get(CONSTANT_PASSBOOKSCOUNTER)));
			}
			else{
				returnValue.setPassbooksCounter((Integer)value.get(CONSTANT_PASSBOOKSCOUNTER));
			}
		}	
		
		
		
		

		return returnValue;
	}
	
	/**
	 * Build JSON (JSONObject) from a DTO object
	 */
	public JSONObject serialize(PassBookCompanyDTO object) throws JSONException {		
		JSONObject returnValue = new JSONObject();
		
		if(object.getOrganizationName()!=null){
			returnValue.put(CONSTANT_ORGANIZATIONNAME, (object.getOrganizationName() == null)? JSONObject.NULL : object.getOrganizationName());
		}
		if(object.getTeamIdentifier()!=null){
			returnValue.put(CONSTANT_TEAMIDENTIFIER, (object.getTeamIdentifier() == null)? JSONObject.NULL : object.getTeamIdentifier());
		}
		if(object.getPassbooksCounter()!=null){
			returnValue.put(CONSTANT_PASSBOOKSCOUNTER, (object.getPassbooksCounter() == null)? JSONObject.NULL : object.getPassbooksCounter());
		}

		return returnValue;
	}
}
