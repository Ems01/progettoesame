package project.service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import project.model.DatiUSA;
import project.model.DatiHospital;
import project.stats.Statistics;

@Service
public class connection implements Int_connection {
	

	public connection() {
		this.parsingData();
	}	
	
	/*
	 * Questo metodo converte i dati letti dal file USA.json 
	 * in oggetti (DatiUSA) utilizzabili in java eli inserisce nelle Arraylist
	 * 
	 */
	
	ArrayList<DatiUSA> vett1 = new ArrayList<DatiUSA>();
	ArrayList<DatiHospital> vett2 = new ArrayList<DatiHospital>();
	
	public void parsingData() {
		
		/*
		 * Usiamo JSONsimple per effettuare il parsing 
		 * e apriamo un flusso di input dal file USA.json
		 */
		
		JSONParser par= new JSONParser();
		FileReader read;
		try {
			read = new FileReader("src/main/java/project/USA.json");
			/*
			 * con i JSONobject e i JSONArray creati possiamo accedere all'interno
			 * della struttura annidata del file JSON , utilizzando poi i setter
			 * delle classi del package project.model per assegnare i valori ai nostri oggetti
			 */
			
			Object oggetto = par.parse(read);
			JSONArray array = (JSONArray) oggetto;
			
			/*
			 * Queste variabili long servono a prendere il valore in ingresso
			 * per verificare se tale valore può essere accettato dal relativo
			 * metodo set o nel caso fosse un "null" ad evitare errori di parsing
			 * facendo assumere al parametro long del relativo metodo set uno zero(0)
			 */
			Long day, positive, negative, death, HN, TN, PI, NI;
			String gg, mm, aaaa, finale;
				
			for(int i=0; i<array.size(); i++) {
				JSONObject obj = (JSONObject) array.get(i);
				DatiUSA usa = new DatiUSA();
				DatiHospital hospital = new DatiHospital();
				
				day = (Long) obj.get("date");
				gg = String.valueOf(day%100);
				mm = String.valueOf(((day%10000) - (day%100))/100);
				aaaa = String.valueOf(day/10000);
				finale = gg + "." + mm + "." + aaaa;
				usa.setDay(finale);
				hospital.setDay(finale);
				
				usa.setNum_states((long) obj.get("states"));
				hospital.setNum_states((long) obj.get("states"));
					
				positive = ((Long) obj.get("positive"));
			    if(positive == null) usa.setPositive(0);
			    else usa.setPositive(positive);
			    if(positive == null) hospital.setPositive(0);
			    else hospital.setPositive(positive);
			    
			    negative = ((Long) obj.get("negative"));
			    if(negative == null) usa.setNegative(0);
			    else usa.setNegative(negative);
				    
			    PI = ((Long) obj.get("positiveIncrease"));
			    if(PI == null) usa.setPositiveIncrease(0);
			    else usa.setPositiveIncrease(PI);
				    
			    NI = ((Long) obj.get("negativeIncrease"));
			    if(NI == null) usa.setNegativeIncrease(0);
			    else usa.setNegativeIncrease(NI);
			    
			    HN = ((Long) obj.get("hospitalizedCurrently"));
			    if(HN == null) hospital.setHospitalized(0);
			    else hospital.setHospitalized(HN);
				    
			    TN = ((Long) obj.get("inIcuCurrently"));
			    if(TN == null) hospital.setIntensive_care(0);
			    else hospital.setIntensive_care(TN);
				    
			    usa.setColour(hospital.addColour());
			    //System.out.println(people.getColour());
				    
			    death = ((Long) obj.get("deathIncrease"));
			    if(death == null) usa.setDeathIncrease(0);
			    else usa.setDeathIncrease(death);
				
				vett1.add(usa);
				vett2.add(hospital);
				};
			}
			
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
	}
	
	@Override
	/*
	 * Ricordarsi di fare il try catch per il giorno
	 * Rivedere l'ordine delle proprietà
	 * 
	 */
	
	public JSONObject getToday(String day){
		JSONObject obj = new JSONObject();
		String mistake = "Day not found!";
		boolean done = false;
		for(int i=0; i<vett1.size(); i++) {
		if (day.equals(vett1.get(i).getDay())) {
			obj.put("number states", vett1.get(i).getNum_states());
			obj.put("death increase", vett1.get(i).getDeathIncrease());
			obj.put("day", vett1.get(i).getDay()); 
            obj.put("colour", vett1.get(i).getColour());
            obj.put("positive", vett1.get(i).getPositiveIncrease());
            obj.put("negative", vett1.get(i).getNegativeIncrease());
            done = true;
            System.out.println("Funziona");  //per il TestException
			}
		}
		if (done = false)
			{
			System.out.println("Non funziona");   //per il TestException
			throw new EccezioneGiorno(mistake);  //il done fa quello che deve fare ma EccezioneGiorno non fa quello che deve fare
			}
		return obj;
	}
	
	@Override
	/*
	 * try catch per il controllo del range della data
	 */
public JSONArray getWeek(String day){
		
		JSONArray array = new JSONArray();
		boolean done = false;
		String mistake = "Week not found!";
		
		long positive=0, negative=0, death=0, h1=0, h7=0, t1=0, t7=0; 
		String last=null;
		
		for(int i=0; i<vett1.size(); i++) {
			if (day.equals(vett1.get(i).getDay())) {
				for(int j=0; j<7; j++) {
					
					JSONObject obj = new JSONObject();
					obj.put("number states", vett1.get(i-j).getNum_states());
					obj.put("day", vett1.get(i-j).getDay()); 
					obj.put("colour", vett1.get(i-j).getColour());
					obj.put("positive", vett1.get(i-j).getPositive());
					obj.put("negative", vett1.get(i-j).getNegative());
					
					array.add(obj);
					done = true;
				}
				if (done = false) throw new EccezioneGiorno(mistake);
				Statistics stats = new Statistics();
				stats.StatsLong(vett1, vett2, array, i, 7);
			}
		}
		return array;
	}
	
	@Override
	public JSONArray getMonth(String month, String year){
		int dayfinal=0;
		int m = 0; 
		boolean done = false;
		String mistake = "Month not found!";
		switch(month) {
		case "january" , "January", "JANUARY": dayfinal=31; m= 1; break; 
		case "february", "February", "FEBRUARY": dayfinal=28; m=2; break;
		case "march", "March", "MARCH": dayfinal =31; m=3; break; 
		case "April", "april", "APRIL": dayfinal=30; m=4; break; 
		case "may", "May", "MAY": dayfinal=31; m=5; break; 
		case "june", "JUNE", "June": dayfinal=30; m=6; break; 
		case "july", "July", "JULY": dayfinal=31; m=7; break; 
		case "august", "August", "AUGUST": dayfinal=31; m=8; break; 
		case "September", "SEPTEMBER", "september": dayfinal=30; m=9; break; 
		case "October", "october", "OCTOBER": dayfinal=31; m=10; break; 
		case "november", "November", "NOVEMBER": dayfinal=30; m=11; break; 
		case "December", "december", "DECEMBER": dayfinal=31; m=12; break; 
		}
		String m_a =(m + "." + year);
		JSONArray array = new JSONArray();
		
		int daystart=1;
		if(m_a.equals("1.2020")) {
			daystart = 13;
			dayfinal = 19;
		}
		if(m_a.equals("2.2020")) dayfinal = 29;
		if(m_a.equals("3.2021")) dayfinal = 7;
		String day = daystart + "." + m_a;
		
		for(int i=0; i<vett1.size(); i++) {
			if (day.equals(vett1.get(i).getDay())) {
				for(int j=0; j<dayfinal; j++) {
				
					JSONObject obj = new JSONObject();	
					obj.put("number states", vett1.get(i-j).getNum_states());
					obj.put("day", vett1.get(i-j).getDay()); 
					obj.put("colour", vett1.get(i-j).getColour());
					obj.put("positive", vett1.get(i-j).getPositive());
					obj.put("negative", vett1.get(i-j).getNegative());
					done = true;
	
					array.add(obj);
				}
				if (done = false) throw new EccezioneGiorno(mistake);
				Statistics stats = new Statistics();
				if(daystart != 13) stats.StatsLong(vett1, vett2, array, i, dayfinal);
				else stats.StatsLong(vett1, vett2, array, i, 19);	
			}
		}
		return array;
	};
	
	@Override
	public JSONArray getColour(String colour) {
		
		switch(colour) {
		case "white", "WHITE": colour = "White"; break; 
		case "yellow", "YELLOW": colour = "Yellow"; break; 
		case "orange", "ORANGE": colour = "Orange";break; 
		case "red", "RED": colour = "Red";break; 
		default: colour = "Not found"; break;
		}
		
		JSONArray array = new JSONArray();
		JSONObject color  = new JSONObject ();
		color.put("Type of colour is: ", colour);
		array.add(color);
		
		for(int i=0; i<vett1.size(); i++) {
			if(colour.equals(vett1.get(i).getColour())) {
				JSONObject obj = new JSONObject();
				obj = getToday(vett1.get(i).getDay());
				array.add(obj);
			}
		}
		return array;
	}

	@Override
	public JSONArray get2days(String day1, String day2) {
		
		JSONArray array = new JSONArray();
		Statistics stats = new Statistics();
		stats.Stats2day(vett1, vett2, array, day1, day2);
		
		JSONObject obj1 = getToday(day1);
		JSONObject obj2 = getToday(day2);
		array.add(obj1);
		array.add(obj2);
		
		return array;
	}

}

