package es.claucookie.pasbuk.model.dao;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.List;
import java.util.ArrayList;
import java.io.*;
import es.claucookie.pasbuk.model.dto.*;

/**
 * DAO for entity PassBooksDAO, Singleton
 * @author Service Generator
 *
 * Generated Class - DO NOT MODIFY
 */
public final class PassBooksDAO{

	// XML field constants
	private static final String CONSTANT_PASSES="passes";
	
	private static PassBooksDAO instance=new PassBooksDAO();

	private PassBooksDAO(){
		
	}
	/**
	 * Gets DAO instance
	 * @returns instance
	 */
	public static PassBooksDAO getInstance(){
		return instance;
	}
	
	/**
	 * Build an PassBooksDTO from JSON
	 */
	public PassBooksDTO create(JSONObject value) throws JSONException{
		PassBooksDTO returnValue=new PassBooksDTO();
		
		
		
		
		ArrayList<PassBookDTO> passesList=new ArrayList<PassBookDTO>();
		if(value.has(CONSTANT_PASSES) && !value.get(CONSTANT_PASSES).toString().equals("null")) {
			if(value.get(CONSTANT_PASSES) instanceof JSONArray){
				JSONArray passesArray=(JSONArray)value.get(CONSTANT_PASSES);
				for(int i=0;i!=passesArray.length();i++){
					JSONObject obj=(JSONObject)passesArray.get(i);
					passesList.add(PassBookDAO.getInstance().create(obj));
				}
			}
			else{
				passesList.add(PassBookDAO.getInstance().create((JSONObject)value.get(CONSTANT_PASSES)));
			}
		}
		returnValue.setPasses(passesList);

		return returnValue;
	}
	
	/**
	 * Build JSON (JSONObject) from a DTO object
	 */
	public JSONObject serialize(PassBooksDTO object) throws JSONException {		
		JSONObject returnValue = new JSONObject();
		
		if(object.getPasses()!=null){
			JSONArray passesArray = new JSONArray();
			for (PassBookDTO passesObject : object.getPasses()) {
				passesArray.put(PassBookDAO.getInstance().serialize(passesObject));
			}
			returnValue.put(CONSTANT_PASSES,passesArray);
		}

		return returnValue;
	}
}
