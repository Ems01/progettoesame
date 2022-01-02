package project.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/*
 * interfaccia che ci permette di visualizzare i vari metodi il cui corpo
 * lo definiremo in connection
 */
public interface Int_connection {
	
	public abstract void parsingData();
	public abstract JSONObject getToday(String day) throws Exception;
	public abstract JSONArray getWeek(String day);
	public abstract JSONArray getMonth(String month, String year);
	public abstract JSONArray getColour(String colour);
	public abstract JSONArray get2days(String day1, String day2);
}
