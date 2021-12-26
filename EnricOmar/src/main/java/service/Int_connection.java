package service;

import java.util.ArrayList;
import java.util.Collection;

import org.json.simple.JSONObject;

import model.DatiUSA;

public interface Int_connection {
	
	/*
	 * interfaccia di connection che ci permette di visualizzare i vari metodi che andremo a programmare
	 * 
	 */
	
	public abstract void parsingData();
	public abstract JSONObject getToday(String day);
	//public abstract ArrayList<People> getWeek(long day);
	//public abstract ArrayList<People> getMonth(long day);
	//public abstract ArrayList<People> getColor(long day, String colour);

	
	

}
