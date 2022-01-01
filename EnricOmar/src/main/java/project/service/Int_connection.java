package project.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public interface Int_connection {
	
	/*
	 * interfaccia di connection che ci permette di visualizzare i vari metodi che andremo a programmare
	 */
	
	public abstract void parsingData();
	public abstract JSONObject getToday(String day) throws Exception;
	public abstract JSONArray getWeek(String day);
	public abstract JSONArray getMonth(String month, String year);
	public abstract JSONArray getColour(String colour);
}
