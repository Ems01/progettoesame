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
	
	ArrayList<DatiUSA> vett1 = new ArrayList<DatiUSA>();
	ArrayList<DatiHospital> vett2 = new ArrayList<DatiHospital>();
	

	public connection() {
		this.parsingData();
	}	
	
	/*
	 * Questo metodo converte i dati letti dal file USA.json 
	 * in oggetti (DatiUSA e DatiHospital) utilizzabili in java
	 *  e li inserisce nelle rispettive Arraylist
	 * 
	 */
	
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
				
				/*
				 * tale "metodo" prende la data del giorno che è in formato americano (anno-mese-giorno)
				 * e lo converte in formato europer (giorno-mese-anno);
				 */
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
	 * tale metodo prende la stringa del giorno (day) e se compare 
	 * nell'arraylist tale giorno, tramuta i dati dell'oggetto in un JSONObject
	 * restituendolo;
	 * 
	 */
	
	public JSONObject getToday(String day){
		JSONObject obj = new JSONObject();
		String mistake = "Day not found!";
		boolean done = false;
		for(int i=0; i<vett1.size(); i++) {
		if (day.equals(vett1.get(i).getDay())) {
			obj.put("Number of states:", vett1.get(i).getNum_states());
			obj.put("Death increase:", vett1.get(i).getDeathIncrease());
			obj.put("Day:", vett1.get(i).getDay()); 
            obj.put("Colour:", vett1.get(i).getColour());
            obj.put("Positive increase:", vett1.get(i).getPositiveIncrease());
            obj.put("Positive total:", vett1.get(i).getPositive());
            obj.put("Negative increase:", vett1.get(i).getPositiveIncrease());
            obj.put("Negative total:", vett1.get(i).getNegative());
            done = true;
			}
		}
		if (done == false)
			{
			throw new EccezioneGiorno(mistake);  //il done fa quello che deve fare ma EccezioneGiorno non fa quello che deve fare
			}
		return obj;
	}
	
	@Override
	/*
	 * Metodo simile a getToday (ed infatti lo richiama), ma con la differenza 
	 * che va a prendere i 7 giorni successivi chiamando poi StatsLong per avere delle statistiche della settimana 
	 */
	public JSONArray getWeek(String day){
		
		JSONArray array = new JSONArray();
		boolean done = false;
		String mistake = "Week not found!";
		
		long positive=0, negative=0, death=0, h1=0, h7=0, t1=0, t7=0; 
		String last=null;
		
		for(int i=0; i<vett1.size(); i++) {
			if (day.equals(vett1.get(i).getDay())) {
				
				Statistics stats = new Statistics();
				stats.StatsLong(vett1, vett2, array, i, 7);
				
				for(int j=0; j<7; j++) {
					JSONObject obj = new JSONObject();
					obj = getToday(vett1.get(i-j).getDay());
					array.add(obj);
					done = true;
				}
				if (done == false) throw new EccezioneGiorno(mistake);
			}
		}
		return array;
	}
	
	@Override
	/*
	 * tale metodo prende in ingresso il mese e l'anno(month & year) e
	 * stamperà le statistiche di tale mese e la lista dei giorni;
	 */
	public JSONArray getMonth(String month, String year){
		int dayfinal=0;
		int m = 0; 
		boolean done = false;
		String mistake = "Month not found!";
		/*
		 * lo switch mi serve per definire bene alcuni parametri:
		 * 
		 * 1) dayfinal è il numero di giorni in un mese e serve per il for però
		 * ci sono delle eccezzioni:
		 * -marzo 2021 arriva fino al 7° giorno;
		 * -febbraio 2020 è bisestile quindi fino al 29° giorno;
		 * -gennaio 2020 parte dal 13 giorno quindi le ripetizioni del for saranno 19;
		 * 
		 * 2) m rappresenta il numero del mese così come noi lo intendiamo 1° mese, 2° mese etc...
		 * 
		 * 3) daystart è il giorno del mese da cui deve iniziare a contare il for, ma 
		 * gennaio 2020 parte dal giorno 13.
		 *
		 */
		
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
		else if(m_a.equals("2.2020")) dayfinal = 29;
		else if(m_a.equals("3.2021")) dayfinal = 7;
		String day = daystart + "." + m_a;
		
		for(int i=0; i<vett1.size(); i++) {
			if (day.equals(vett1.get(i).getDay())) {
				Statistics stats = new Statistics();
				stats.StatsLong(vett1, vett2, array, i, dayfinal);
				for(int j=0; j<dayfinal; j++) {
				
					JSONObject obj = new JSONObject();	
					obj = getToday(vett1.get(i-j).getDay());
					done = true;
	
					array.add(obj);
				}
				//if (done == false) throw new EccezioneGiorno(mistake);
			}
		}
		return array;
	};
	
	@Override
	
	/*
	 * dato una stringa del colore in ingresso stamperà la lista di quei colori e 
	 * accederà al metodo StatsColour se tale colore è presente nello switch;
	 */
	public JSONArray getColour(String colour) {
		
		switch(colour) {
		case "white", "WHITE", "White": colour = "White"; break; 
		case "yellow", "YELLOW", "Yellow": colour = "Yellow"; break; 
		case "orange", "ORANGE", "Orange": colour = "Orange";break; 
		case "red", "RED", "Red": colour = "Red";break; 
		default: colour = "Not found"; break;
		}
		
		JSONArray array = new JSONArray();
		JSONObject color  = new JSONObject ();
		color.put("Type of colour is: ", colour);
		array.add(color);
		
		Statistics stats = new Statistics();
		if(!(colour.equals("Not found"))) {
			stats.StatsColour(vett1, array);
			for(int i=0; i<vett1.size(); i++) {
				if(colour.equals(vett1.get(i).getColour())) {
					JSONObject obj = new JSONObject();
					obj = getToday(vett1.get(i).getDay());
					array.add(obj);
				}
			}
		}
		return array;
	}
	@Override
	/*
	 * prende in ingresso 2 stringhe giorno e se sono differenti stamperà quei giorni
	 * ed eseguirà il metodo Stats2days, altrimenti se i giorni sono identici
	 * eseguirà un semplice getToday
	 * 
	 */
	public JSONArray get2days(String day1, String day2) {
		
		JSONArray array = new JSONArray();
		if(day1.equals(day2)) {
			JSONObject obj = getToday(day1);
			array.add(obj);
		}
		else {
			Statistics stats = new Statistics();
			stats.Stats2day(vett1, vett2, array, day1, day2);
			JSONObject obj1 = getToday(day1);
			JSONObject obj2 = getToday(day2);
			array.add(obj1);
			array.add(obj2);
		}
		
		return array;
	}

}

