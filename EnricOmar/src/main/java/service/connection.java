package service;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import model.Dati;
import model.DatiStati;
import model.DatiUSA;

public class connection implements Int_connection {
	
	public void saveData() {
		String url1= ("https://api.covidtracking.com/v1/us/daily.json");
		String url2= ("https://api.covidtracking.com/v1/states/daily.json");
	
		String sito="";
		String testo= "";
		for(int i=0; i<2; i++){
			try {
				if(i==0) sito=url1;
				else sito=url2;
		
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
					if(i==0) {
						writer = new BufferedWriter (new FileWriter("prova1.txt"));
						testo = "prova1.txt";
					}
					else {
						writer = new BufferedWriter (new FileWriter("prova2.txt"));
						testo = "prova2.txt";
					}	
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
			
			parsingData(testo);
			}
		}
	
	public void parsingData(String testo) {
		
		JSONParser par= new JSONParser();
		FileReader read;
		try {
			read = new FileReader(testo);
			Object oggetto = par.parse(read);
			if(testo.equals("prova1.txt")) {
				ArrayList<DatiUSA> listaUsa = new ArrayList<DatiUSA>();
				JSONArray array = (JSONArray) oggetto;
				
				for(int i=0; i<array.size(); i++) {
					JSONObject obj2 = (JSONObject) array.get(i);
					DatiUSA value = new DatiUSA(); 
					value.setDay((Integer) obj2.get("date"));
					value.setNum_states((Integer) obj2.get("states"));
					value.setPositive((Long) obj2.get("positive"));
					value.setNegative((Long) obj2.get("negative"));
					value.setHN((Long) obj2.get("hospitalizedCurrently"));
					value.setHT((Long) obj2.get("hospitalizedCumulative"));
					value.setTN((Long) obj2.get("inIcuCurrently"));
					value.setTT((Long) obj2.get("inIcuCumulative"));
					value.setVN((Long) obj2.get("onVentilatorCurrently"));
					value.setVT((Long) obj2.get("onVentilatorCumulative"));
					value.setDT((Long) obj2.get("death"));
					value.setDN((Long) obj2.get("deathIncrease"));
					value.setId((String) obj2.get("hash"));
					listaUsa.add(value);
				};
			}
			else {
				ArrayList<DatiStati> listaState = new ArrayList<DatiStati>();
				JSONArray array = (JSONArray) oggetto;
				
				for(int i=0; i<array.size(); i++) {
					JSONObject obj3 = (JSONObject) array.get(i);
					DatiStati value = new DatiStati(); 
					value.setDay((Integer) obj3.get("date"));
					value.setName((String) obj3.get("state"));
					value.setPositive((Long) obj3.get("positive"));
					value.setNegative((Long) obj3.get("negative"));
					value.setHN((Long) obj3.get("hospitalizedCurrently"));
					value.setHT((Long) obj3.get("hospitalizedCumulative"));
					value.setTN((Long) obj3.get("inIcuCurrently"));
					value.setTT((Long) obj3.get("inIcuCumulative"));
					value.setVN((Long) obj3.get("onVentilatorCurrently"));
					value.setVT((Long) obj3.get("onVentilatorCumulative"));
					value.setDT((Long) obj3.get("death"));
					value.setDN((Long) obj3.get("deathIncrease"));
					value.setId((String) obj3.get("hash"));
					listaState.add(value);
				};
				
			}
			
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
	}
}

