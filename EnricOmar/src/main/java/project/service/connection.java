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

@Service
public class connection implements Int_connection {
	

	public connection() {
		this.parsingData();
	}	
	
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
			read = new FileReader("src/main/java/project/USA.json");
			/*
			 * con i JSONobject e i JSONArray creati possiamo accedere all'interno
			 * della struttura annidata del file JSON , utilizzando poi i setter
			 * delle classi del package project.model per assegnare i valori ai nostri oggetti
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
			    
			    negative = ((Long) obj.get("negative"));
			    if(negative == null) dati.setNegative(0);
			    else dati.setNegative(negative);
				    
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
	/*
	 * Ricordarsi di fare il try catch per il giorno
	 * Rivedere l'ordine delle proprietà
	 * 
	 */
	public JSONObject getToday(String day) {	
		JSONObject obj = new JSONObject();
		for(int i=0; i<vett.size(); i++) {
		if (day.equals(vett.get(i).getDay())) {
			obj.put("number states", vett.get(i).getNum_states());
			obj.put("death increase", vett.get(i).getDeathIncrease());
			obj.put("day", vett.get(i).getDay()); 
            obj.put("colour", vett.get(i).getColour());
            obj.put("positive", vett.get(i).getPositiveIncrease());
            obj.put("negative", vett.get(i).getNegativeIncrease());
			}
		}
		return obj;
		//throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Not existing Day...");
	};
	
	
	@Override
	/*
	 * try catch per il controllo del range della data
	 */
	public JSONArray getWeek(String day){
		
		JSONArray array = new JSONArray();
		
		long positive=0, negative=0, death=0, h1=0, h7=0, t1=0, t7=0; 
		String last=null;
		
		for(int i=0; i<vett.size(); i++) {
			if (day.equals(vett.get(i).getDay())) {
				for(int j=0; j<7; j++) {
					JSONObject obj = new JSONObject();
					obj.put("number states", vett.get(i-j).getNum_states());
					obj.put("day", vett.get(i-j).getDay()); 
					obj.put("colour", vett.get(i-j).getColour());
					obj.put("positive", vett.get(i-j).getPositive());
					obj.put("negative", vett.get(i-j).getNegative());
					
					positive += vett.get(i-j).getPositiveIncrease();
					negative += vett.get(i-j).getNegativeIncrease();
					death += vett.get(i-j).getDeathIncrease();
					h1 = vett.get(i).getHospitalized();
					h7 = vett.get(i-7).getHospitalized();
					t1 = vett.get(i).getIntensive_care();
					t7 = vett.get(i-7).getIntensive_care();
					last = vett.get(i-7).getDay();
					array.add(obj);
				}
				JSONObject total = new JSONObject();
				total.put("Week", day + "-" + last);
				total.put("Death in week", death);
				total.put("Positive in week", positive);
				total.put("Negative in week", negative);
				if(h7 >= h1) {
					total.put("Hospitalized in week", "+" + (h7-h1));
				}
				else total.put("Hospitalized in week", (h7-h1));
				if(t7 >= t1) {
					total.put("Intensive care in week", "+" + (t7-t1));
				}
				else total.put("Intensive care in week", (t7-t1));
				
				array.add(total);
			}
		}
		return array;
	};
	
	@Override
	public JSONArray getMonth(String month, String year){
		int dayfinal=0;
		String m = null; 
		switch(month) {
		case "january" , "January", "JANUARY": dayfinal=31; m= "1"; break; 
		case "february", "February", "FEBRUARY": dayfinal=29; m="2"; break;
		case "march", "March", "MARCH": dayfinal =31; m="3"; break; 
		case "April", "april", "APRIL": dayfinal=30; m="4"; break; 
		case "may", "May", "MAY": dayfinal=31; m="5"; break; 
		case "june", "JUNE", "June": dayfinal=30; m="6"; break; 
		case "july", "July", "JULY": dayfinal=31; m="7"; break; 
		case "august", "August", "AUGUST": dayfinal=31; m="8"; break; 
		case "September", "SEPTEMBER", "september": dayfinal=30; m="9"; break; 
		case "October", "october", "OCTOBER": dayfinal=31; m="10"; break; 
		case "november", "November", "NOVEMBER": dayfinal=30; m="11"; break; 
		case "December", "december", "DECEMBER": dayfinal=31; m="12"; break; 
		}
		String m_a =(m + "." + year);
		JSONArray array = new JSONArray();
		long positive=0, negative=0, death=0, h1=0, hf=0, t1=0, tf=0; 
		
		int daystart=1;
		if(m_a.equals(1.2020)) daystart = 13;
		if(m_a.equals(3.2021)) dayfinal = 7;
		String day = daystart + "." + m_a;
		
		for(int i=0; i<vett.size(); i++) {
			if (day.equals(vett.get(i).getDay())) {
				for(int j=0; j<dayfinal; j++) {
					JSONObject obj = new JSONObject();
					obj.put("number states", vett.get(i-j).getNum_states());
					obj.put("day", vett.get(i-j).getDay()); 
					obj.put("colour", vett.get(i-j).getColour());
					obj.put("positive", vett.get(i-j).getPositive());
					obj.put("negative", vett.get(i-j).getNegative());
					
					positive += vett.get(i-j).getPositiveIncrease();
					negative += vett.get(i-j).getNegativeIncrease();
					death += vett.get(i-j).getDeathIncrease();
					h1 = vett.get(i).getHospitalized();
					hf = vett.get(i-7).getHospitalized();
					t1 = vett.get(i).getIntensive_care();
					tf = vett.get(i-7).getIntensive_care();
					array.add(obj);
				}
				JSONObject total = new JSONObject();
				total.put("Month:", month +" "+ year);
				total.put("Death in month", death);
				total.put("Positive in month", positive);
				total.put("Negative in month", negative);
				if(hf >= h1) {
					total.put("Hospitalized in month", "+" + (hf-h1));
				}
				else total.put("Hospitalized in month", (hf-h1));
				if(tf >= t1) {
					total.put("Intensive care in month", "+" + (tf-t1));
				}
				else total.put("Intensive care in month", (tf-t1));
				
				array.add(total);
			}
		}
		return array;
	};
	
	/*
	@Override
	public ArrayList<People> getColor(long day, String colour){};
	*/
}

