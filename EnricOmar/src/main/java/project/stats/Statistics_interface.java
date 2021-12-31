package project.stats;

import java.util.ArrayList;
import org.json.simple.JSONArray;
import project.model.DatiHospital;
import project.model.DatiUSA;

public interface Statistics_interface {
	
	public abstract void StatsLong(ArrayList<DatiUSA> vett1, ArrayList<DatiHospital> vett2, JSONArray array, Integer i, Integer dayfinal);
	public abstract void Stats2day(ArrayList<DatiUSA> vett1, ArrayList<DatiHospital> vett2, JSONArray array, String day1, String day2);
}
