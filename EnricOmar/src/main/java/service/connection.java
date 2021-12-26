package service;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import model.DatiUSA;

@Service
public class connection implements Int_connection {
	
	/*
	 * Questo metodo converte i dati letti dal file USA.json 
	 * in oggetti (DatiUSA) utilizzabili in java
	 * 
	 */
	
	//private static Map<String, DatiUSA>DatiRepo = new HashMap<>();
	ArrayList<DatiUSA> vett = new ArrayList<DatiUSA>();
	
	public void parsingData() {
		
		/*
		 * Usiamo JSONsimple per effettuare il parsing 
		 * e apriamo un flusso di input dal file USA.json
		 */
		
		JSONParser par= new JSONParser();
		FileReader read;
		try {
			read = new FileReader("src/main/java/service/USA.json");
			/*
			 * con i JSONobject e i JSONArray creati possiamo accedere all'interno
			 * della struttura annidata del file JSON , utilizzando poi i setter
			 * delle classi del package model per assegnare i valori ai nostri oggetti
			 */
			
			Object oggetto = par.parse(read);
			JSONArray array = (JSONArray) oggetto;
			System.out.println("entro");
			
			/*
			 * Queste variabili long servono a prendere il valore in ingresso
			 * per verificare se tale valore può essere accettato dal relativo
			 * metodo set o nel caso fosse un "null" ad evitare errori di parsing
			 * facendo assumere al parametro long del relativo metodo set uno zero(0)
			 */
			Long positive, negative, death, HN, TN, PI, NI;
				
			for(int i=0; i<array.size(); i++) {
				JSONObject obj = (JSONObject) array.get(i);
				DatiUSA dati = new DatiUSA();
					
				dati.setDay((Long) obj.get("date"));
				
				dati.setNum_states((long) obj.get("states"));
					
				positive = ((Long) obj.get("positive"));
			    if(positive == null) dati.setPositive(0);
			    else dati.setPositive(positive);
				    
			    PI = ((Long) obj.get("positiveIncrease"));
			    if(PI == null) dati.setPositiveIncrease(0);
			    else dati.setPositiveIncrease(PI);
				    
			    NI = ((Long) obj.get("negativeIncrease"));
			    if(NI == null) dati.setNegativeIncrease(0);
			    else dati.setNegativeIncrease(NI);
			    
			    HN = ((Long) obj.get("hospitalizedCurrently"));
			    if(HN == null) dati.setHospitalized(0);
			    else dati.setHospitalized(HN);
				    
			    TN = ((Long) obj.get("inIcuCurrently"));
			    if(TN == null) dati.setIntensive_care(0);
			    else dati.setIntensive_care(TN);
				    
			    dati.setColour();
			    //System.out.println(people.getColour());
				    
			    death = ((Long) obj.get("deathIncrease"));
			    if(death == null) dati.setDeathIncrease(0);
			    else dati.setDeathIncrease(death);
				   
				dati.setId((String) obj.get("hash"));
				dati.setId((String) obj.get("hash"));
				
				vett.add(dati);
				};
			}
			
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
	}
	
	@Override
	public JSONObject getToday(String day) {
		
		JSONObject obj = new JSONObject();
		for(int i=0; i<vett.size(); i++) {
		if (day.equals(vett.get(i).getDay())) {
            obj.put("day", vett.get(i).getDay());
            obj.put("colour", vett.get(i).getColour());
			}
		}
		return obj;
		//throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not existing Day...");
	};
	
	/*
	@Override
	public ArrayList<People> getWeek(long day){};
	
	@Override
	public ArrayList<People> getMonth(long day){};
	
	@Override
	public ArrayList<People> getColor(long day, String colour){};
	*/
}

