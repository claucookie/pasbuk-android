package es.claucookie.pasbuk.model.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import es.claucookie.pasbuk.model.dto.*;

/**
 * DAO for entity PassBookCompaniesDAO, Singleton
 * @author Service Generator
 *
 * Generated Class - DO NOT MODIFY
 */
public final class PassBookCompaniesDAO{

	// XML field constants
	private static final String CONSTANT_COMPANIES="companies";
	
	private static PassBookCompaniesDAO instance=new PassBookCompaniesDAO();

	private PassBookCompaniesDAO(){
		
	}
	/**
	 * Gets DAO instance
	 * @returns instance
	 */
	public static PassBookCompaniesDAO getInstance(){
		return instance;
	}
	
	/**
	 * Build an PassBookCompaniesDTO from JSON
	 */
	public PassBookCompaniesDTO create(JSONObject value) throws JSONException{
		PassBookCompaniesDTO returnValue=new PassBookCompaniesDTO();
		
		
		
		
		ArrayList<PassBookCompanyDTO> companiesList=new ArrayList<PassBookCompanyDTO>();
		if(value.has(CONSTANT_COMPANIES) && !value.get(CONSTANT_COMPANIES).toString().equals("null")) {
			if(value.get(CONSTANT_COMPANIES) instanceof JSONArray){
				JSONArray companiesArray=(JSONArray)value.get(CONSTANT_COMPANIES);
				for(int i=0;i!=companiesArray.length();i++){
					JSONObject obj=(JSONObject)companiesArray.get(i);
					companiesList.add(PassBookCompanyDAO.getInstance().create(obj));
				}
			}
			else{
				companiesList.add(PassBookCompanyDAO.getInstance().create((JSONObject)value.get(CONSTANT_COMPANIES)));
			}
		}
		returnValue.setCompanies(companiesList);

		return returnValue;
	}
	
	/**
	 * Build JSON (JSONObject) from a DTO object
	 */
	public JSONObject serialize(PassBookCompaniesDTO object) throws JSONException {		
		JSONObject returnValue = new JSONObject();
		
		if(object.getCompanies()!=null){
			JSONArray companiesArray = new JSONArray();
			for (PassBookCompanyDTO companiesObject : object.getCompanies()) {
				companiesArray.put(PassBookCompanyDAO.getInstance().serialize(companiesObject));
			}
			returnValue.put(CONSTANT_COMPANIES,companiesArray);
		}

		return returnValue;
	}
}
