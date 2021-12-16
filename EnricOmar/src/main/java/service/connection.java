package service;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.DatiUSA;
import model.Hospital;
import model.People;

public class connection implements Int_connection {
	
	//file locale ("C:\\Users\\emsar\\Documents\\GitHub\\progettoesame\\EnricOmar\\src\\main\\java\\service\\USA.json")
	public void parsingData() {
		
		JSONParser par= new JSONParser();
		FileReader read;
		try {
			read = new FileReader("src/main/java/service/USA.json");
			Object oggetto = par.parse(read);
				ArrayList<People> vett1 = new ArrayList<People>();
				ArrayList<Hospital> vett2 = new ArrayList<Hospital>();
				JSONArray array = (JSONArray) oggetto;
				System.out.println("entro");
				
				Long positive, negative, HN, HT, TN, TT, VN, VT, DT, DN;
				for(int i=0; i<array.size(); i++) {
					JSONObject obj = (JSONObject) array.get(i);
					Hospital hospital = new Hospital();
					People people = new People();
					
					hospital.setDay((Long) obj.get("date"));
					people.setDay((Long) obj.get("date"));
					
					hospital.setNum_states((long) obj.get("states"));
					people.setNum_states((long) obj.get("states"));
					System.out.println(hospital.getDay());
					
				    positive = ((Long) obj.get("positive"));
				    if(positive == null) people.setPositive(0);
				    else people.setPositive(positive);
				    
				    negative = ((Long) obj.get("negative"));
				    if(negative == null) people.setNegative(0);
				    else people.setNegative(negative);
				    
				    HN = ((Long) obj.get("hospitalizedCurrently"));
				    if(HN == null) hospital.setHN(0);
				    else hospital.setHN(HN);
				    
				    HT = ((Long) obj.get("hospitalizedCumulative"));
				    if(HT == null) hospital.setHT(0);
				    else hospital.setHT(HT);
				    
				    TN = ((Long) obj.get("inIcuCurrently"));
				    if(TN == null) hospital.setTN(0);
				    else hospital.setTN(TN);
				    
				    TT = ((Long) obj.get("inIcuCumulative"));
				    if(TT == null) hospital.setTT(0);
				    else hospital.setTT(TT);
				    
				    DT = ((Long) obj.get("death"));
				    if(DT == null) people.setDT(0);
				    else people.setDT(DT);
				    
				    DN = ((Long) obj.get("deathIncrease"));
				    if(DN == null) people.setDN(0);
				    else people.setDN(DN);
				   
					people.setId((String) obj.get("hash"));
					hospital.setId((String) obj.get("hash"));
					
					vett1.add(people);
					vett2.add(hospital);
				};
			}
			
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
	}
	
	/*@Override
	public People getDay(long day) {};
	
	@Override
	public ArrayList<People> getWeek(long day){};
	
	@Override
	public ArrayList<People> getMonth(long day){};
	
	@Override
	public ArrayList<People> getColor(long day, String colour){};
	*/
}

