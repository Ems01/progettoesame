package service;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.io.PrintWriter;
//import java.net.HttpURLConnection;
//import java.net.URL;
import java.util.ArrayList;
//import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//import model.Dati;
//import model.DatiStati;
import model.DatiUSA;
import model.Hospital;
import model.People;

public class connection implements Int_connection {
	
	/*public void saveData() {
		
		String sito= "https://api.covidtracking.com/v1/us/daily.json";
		String sito="";
		String testo= "";
		
		
		try {
		
			URL url = new URL(sito);
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
	
			conn.setRequestMethod("GET");				
			conn.connect();
			
			int responseCode = conn.getResponseCode();//verifica se la connessione è avvenuta
		
			StringBuilder informationString = new StringBuilder();//ci salverò i dati dell'url
			BufferedWriter writer; //mi serve per scrivere i dati nel file;
				
				if(responseCode != 200){
					throw new RuntimeException("HttpResponseCode: " + responseCode);
				} 
				else {			
					Scanner scan = new Scanner(url.openStream());
					
					writer = new BufferedWriter (new FileWriter("prova1.txt"));
					testo = "prova1.txt";
					
					while(scan.hasNext()) {
						writer.write(scan.nextLine());
					}
					writer.close();
					scan.close();
					conn.disconnect();
				}
			
			}catch (IOException e) {
				e.printStackTrace();
			}
			System.out.println("entro");
			parsingData(testo);
			}
		}
	*/
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
				    
				    VN = ((Long) obj.get("onVentilatorCurrently"));
				    if(VN == null) hospital.setVN(0);
				    else hospital.setVN(VN);
				    
				    VT = ((Long) obj.get("onVentilatoreCumulative"));
				    if(VT == null) hospital.setVT(0);
				    else hospital.setVT(VT);
				    
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
}

