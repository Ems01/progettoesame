package service;
import model.Hospital;
import model.People;
import java.util.ArrayList;

public interface Int_connection {
	
	//public void saveData();
	public void parsingData();
	public abstract People getDay(long day);
	public abstract ArrayList<People> getWeek(long day);
	public abstract ArrayList<People> getMonth(long day);
	public abstract ArrayList<People> getColor(long day, String colour);
	

}
