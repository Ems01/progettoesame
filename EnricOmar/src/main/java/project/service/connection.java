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
import project.exception.EccezionePersonalizzata;

/**
 * Tale classe: 
 * -gestirà il parsing dei dati dal file Json ad oggetti
 * -riporterà un JSONObject/JSONArray che verranno postati dalle rotte
 * 
 * @author Enrico Maria Sardellini
 * @author Omar Naja
 */
@Service
public class connection implements Int_connection {
	
	ArrayList<DatiUSA> vett1 = new ArrayList<DatiUSA>();
	ArrayList<DatiHospital> vett2 = new ArrayList<DatiHospital>();
	ControlloParam control = new ControlloParam();
	Statistics stats = new Statistics();
	
	/**
	 * cotruttore della classe
	 */
	public connection() {
		this.parsingData();
	}	
	
	public void parsingData() {
		/**
		 * Questo metodo converte i dati letti dal file USA.json 
		 * in oggetti (DatiUSA e DatiHospital) utilizzabili in java
		 * e li inserisce nelle rispettive Arraylist
		 * 
		 * @author Enrico Maria Sardellini
		 * @author Omar Naja
		 *
		 * Usiamo JSONsimple per effettuare il parsing 
		 * e apriamo un flusso di input dal file USA.json
		 */
		JSONParser par= new JSONParser();
		FileReader read;
		try {
			read = new FileReader("src/main/java/project/USA.json");
			/**
			 * con i JSONobject e i JSONArray creati possiamo accedere all'interno
			 * della struttura annidata del file JSON , utilizzando poi i setter
			 * delle classi del package project.model per assegnare i valori ai nostri oggetti
			 * 
			 */
			Object oggetto = par.parse(read);
			JSONArray array = (JSONArray) oggetto;
			/**
			 * Queste variabili long servono a prendere il valore in ingresso
			 * per verificare se tale valore può essere accettato dal relativo
			 * metodo set 
			 * 
			 * Ci siamo accorti che se, per esempio, non ci sono contagi non abbiamo uno 0 ma un null
			 * nel file Json, e ci crea problemi di compatibilità del tipo.
			 * quindi salviamo i valori nelle variabili e se sono null, assegniamo ai metodi set uno 0, 
			 * altrimenti assegnamo ai metodi set la variabile
			 */
			Long day, positive, negative, death, HN, TN, PI, NI;
			
			/**
			 * queste variabili string servono a scomporre la data di 8 cifre in formato long
			 * per poi ricomporla in formato europeo nella stringa finale
			 */
			String gg, mm, aaaa, finale;
				
			for(int i=0; i<array.size(); i++) {
				JSONObject obj = (JSONObject) array.get(i);
				DatiUSA usa = new DatiUSA();
				DatiHospital hospital = new DatiHospital();
				
				/**
				 * tale "metodo" prende la data del giorno che è in formato americano (anno-mese-giorno)
				 * e lo converte in formato europe (giorno-mese-anno);
				 * 
				 * @author Enrico Maria Sardellini
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
				    
			    hospital.setColour();
				    
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
	/**
	 * tale metodo prende la stringa del giorno (day) e se compare 
	 * nell'arraylist, tramuta i dati dell'oggetto in un JSONObject
	 * restituendolo;
	 * 
	 * se non viene trovato stamperà un errore.
	 * 
	 * @author Enrico Maria Sardellini
	 * @author Omar Naja
	 * @param day
	 * @return JSONObject
	 * @throws EccezionePersonalizzata
	 */
	public JSONObject getToday(String day){
		
		control.ControlDay(day);
		
		JSONObject obj = new JSONObject();
		String mistake = "Day not found or irregular!";
		boolean done = false;
		
		for(int i=0; i<vett1.size(); i++) {
		if (day.equals(vett1.get(i).getDay())) {
			obj.put("Number of states", vett1.get(i).getNum_states());
			obj.put("Death increase", vett1.get(i).getDeathIncrease());
			obj.put("Day", vett1.get(i).getDay()); 
            obj.put("Colour", vett2.get(i).getColour());
            obj.put("Positive increase", vett1.get(i).getPositiveIncrease());
            obj.put("Positive total", vett1.get(i).getPositive());
            obj.put("Negative increase", vett1.get(i).getPositiveIncrease());
            obj.put("Negative total", vett1.get(i).getNegative());
            done = true;
			}
		}
		if (done == false) throw new EccezionePersonalizzata(mistake);
		return obj;
	}
	
	@Override
	/**
	 * tale metodo prende la stringa del giorno (day) e se compare 
	 * nell'arraylist, tramuta i dati dell'oggetto in un JSONObject
	 * restituendolo;
	 * 
	 * se non viene trovato stamperà un errore.
	 * 
	 * @author Enrico Maria Sardellini
	 * @author Omar Naja
	 * @param day
	 * @return JSONObject
	 * @throws EccezionePersonalizzata
	 */
	public JSONObject getToday(String day, boolean different){
		
		JSONObject obj = new JSONObject();
		String mistake = "Day not found or irregular!";
		boolean done = false;
		
		for(int i=0; i<vett1.size(); i++) {
		if (day.equals(vett1.get(i).getDay())) {
			obj.put("Number of states", vett1.get(i).getNum_states());
			obj.put("Death increase", vett1.get(i).getDeathIncrease());
			obj.put("Day", vett1.get(i).getDay()); 
            obj.put("Colour", vett2.get(i).getColour());
            obj.put("Positive increase", vett1.get(i).getPositiveIncrease());
            obj.put("Positive total", vett1.get(i).getPositive());
            obj.put("Negative increase", vett1.get(i).getPositiveIncrease());
            obj.put("Negative total", vett1.get(i).getNegative());
            done = true;
			}
		}
		if (done == false) throw new EccezionePersonalizzata(mistake);
		return obj;
	}
	
	@Override
	/**
	 * Se il giorno viene trovato, chiamerà il metodo getToday() per
	 * ottenere un JSONObject per poi inserirlo nell'JSONArray.
	 * Tale processo verrà ripetuto per i 7 giorni successivi.
	 * 
	 * Se il giorno non esiste, stamperà un errore.
	 * 
	 * @author Enrico Maria Sardellini
	 * @author Omar Naja
	 * @param day
	 * @return JSONArray
	 * @throws EccezionePersonalizzata
	 * @see project.service.connection.getToday()
	 * @see project.stats.Statistics.StatsLong()
	 */
	public JSONArray getWeek(String day){
		
		control.ControlDay(day);
		control.ControlWeek(day);
		
		JSONArray array = new JSONArray();
		boolean done = false;
		String mistake = "Week not found or irregular";
		
		long positive=0, negative=0, death=0, h1=0, h7=0, t1=0, t7=0; 
		String last=null;
		
		for(int i=0; i<vett1.size(); i++) {
			if (day.equals(vett1.get(i).getDay())) {
				
				stats.StatsLong(vett1, vett2, array, i, 7);
				
				done = true;
				
				for(int j=0; j<7; j++) {
					JSONObject obj = new JSONObject();
					obj = getToday(vett1.get(i-j).getDay(), true);
					array.add(obj);
				}
				
			}
		}
		if (done == false) throw new EccezionePersonalizzata(mistake);
		return array;
	}
	
	@Override
	/**
	 * tale metodo prende in ingresso il mese e l'anno(month & year) e
	 * stamperà le statistiche di tale mese e la lista dei giorni;
	 * 
	 * @author Enrico Maria Sardellini
	 * @author Omar Naja
	 * @param month
	 * @param year
	 * @return JSONArray
	 * @throws EccezionePersonalizzata
	 * @see project.service.connection.getToday()
	 * @see project.stats.Statistics.StatsLong()
	 */
	public JSONArray getMonth(String month, String year){
		int dayfinal=0;
		int m = control.ControlMonth(month, year); 
		/**
		 * lo switch serve per definire bene alcuni parametri:
		 * 
		 * 1) dayfinal è il numero di giorni in un mese e serve per il for però ci sono delle eccezzioni:
		 * -marzo 2021 arriva fino al 6° giorno;
		 * -febbraio 2020 è bisestile quindi fino al 29° giorno;
		 * -gennaio 2020 parte dal 13° giorno quindi le ripetizioni del for saranno 19;
		 * 
		 * 2) m rappresenta il numero del mese 
		 * 
		 * 3) daystart è il giorno del mese da cui deve iniziare a contare il for, ma 
		 * gennaio 2020 parte dal giorno 13 e non dall'1.
		 *
		 */
		switch(m) {
		case 1: dayfinal=31; break; 
		case 2: dayfinal=28; break;
		case 3: dayfinal=31; break; 
		case 4: dayfinal=30; break; 
		case 5: dayfinal=31; break; 
		case 6: dayfinal=30; break; 
		case 7: dayfinal=31; break; 
		case 8: dayfinal=31; break; 
		case 9: dayfinal=30; break; 
		case 10: dayfinal=31; break; 
		case 11: dayfinal=30; break; 
		case 12: dayfinal=31; break; 
		default: break; 
		}
		/**
		 * questa parte del programma crea la stringa con cui andremo a verificare 
		 * le varie date. 
		 */
		String m_a =(m + "." + year);
		JSONArray array = new JSONArray();
		int daystart=1;
		if(m_a.equals("1.2020")) {
			daystart = 13;
			dayfinal = 19;
		}
		else if(m_a.equals("2.2020")) dayfinal = 29;
		else if(m_a.equals("3.2021")) dayfinal = 6;
		String day = daystart + "." + m_a;
		
		for(int i=0; i<vett1.size(); i++) {
			if (day.equals(vett1.get(i).getDay())) {
				
				stats.StatsLong(vett1, vett2, array, i, dayfinal);
				
				for(int j=0; j<dayfinal; j++) {
				
					JSONObject obj = new JSONObject();	
					obj = getToday(vett1.get(i-j).getDay(), true);
					array.add(obj);
				}
				
			}
		}
		return array;
	};
	
	@Override
	
	/**
	 * stamperà la lista dei giorni con quel colore e 
	 * accederà al metodo StatsColour se tale colore è presente nello switch;
	 * 
	 * @author Enrico Maria Sardellini
	 * @author Omar Naja
	 * @param colour
	 * @return JSONArray
	 * @throws EccezioneGiorno
	 * @see project.service.connection.getToday()
	 * @see project.stats.Statistics.StatsColour()
	 */
	public JSONArray getColour(String finale) {
		
		String colour = control.ControlColour(finale);
		JSONArray array = new JSONArray();
		JSONObject general  = new JSONObject ();
		
		general.put("Type of colour is", colour);
		int volte = stats.StatsColour(vett2, colour);
		general.put("There were", volte + " " + colour + " days");
		array.add(general);
			
		for(int i=0; i<vett1.size(); i++) {
			if(colour.equals(vett2.get(i).getColour())) {
				JSONObject obj = new JSONObject();
				obj = getToday(vett1.get(i).getDay());
				stats.StatsColour(vett2, obj, i);
				array.add(obj);
			}
		}
		return array;
	}
	
	@Override
	/**
	 * prende in ingresso 2 stringhe giorno e se sono differenti stamperà quei giorni
	 * ed eseguirà il metodo Stats2days, altrimenti se i giorni sono identici
	 * eseguirà un solo getToday
	 * 
	 * @author Enrico Maria Sardellini
	 * @param day1
	 * @param day2
	 * @return JSONArray
	 * @see project.service.connection.getToday()
	 * @see project.stats.Statistics.Stats2day()
	 * 
	 */
	public JSONArray get2days(String day1, String day2) {
		
		JSONArray array = new JSONArray();
		
		if(day1.equals(day2)) {
			JSONObject obj = getToday(day1);
			array.add(obj);
		}
		else {
			stats.Stats2days(vett1, vett2, array, day1, day2);
			JSONObject obj1 = getToday(day1);
			JSONObject obj2 = getToday(day2);
			array.add(obj1);
			array.add(obj2);
		}
		return array;
	}

}

