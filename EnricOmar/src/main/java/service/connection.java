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
	public void parsingData() {
		
		String nulla = "null";
		JSONParser par= new JSONParser();
		FileReader read;
		try {
			read = new FileReader("C:\\Users\\emsar\\Documents\\GitHub\\progettoesame\\EnricOmar\\src\\main\\java\\service\\USA.json");
			Object oggetto = par.parse(read);
				ArrayList<DatiUSA> listaUsa = new ArrayList<DatiUSA>();
				JSONArray array = (JSONArray) oggetto;
				System.out.println("entro");
				for(int i=0; i<array.size(); i++) {
					JSONObject obj2 = (JSONObject) array.get(i);
					DatiUSA value = new DatiUSA(); 
					value.setDay((long) obj2.get("date"));
					value.setNum_states((long) obj2.get("states"));
					System.out.println(value.getDay());
					
					if(obj2.get("positive").equals(nulla)) value.setPositive(0);
					else value.setPositive((Long) obj2.get("positive"));
					
					if(obj2.get("negative").equals(nulla)) value.setNegative(0);
					else value.setNegative((Long) obj2.get("negative"));
					
					if(obj2.get("hospitalizedCumulative").equals(nulla)) value.setHN(0);
					else value.setHN((Long) obj2.get("hospitalizedCurrently"));
					
					if(obj2.get("hospitalizedCumulative").equals(nulla)) value.setHT(0);
					else value.setHT((Long) obj2.get("hospitalizedCumulative"));
					
					if(obj2.get("inIcuCurrently").equals(nulla)) value.setTN(0);
					else value.setTN((Long) obj2.get("inIcuCurrently"));
					
					if(obj2.get("inIcuCumulative").equals(nulla)) value.setTT(0);
					else value.setTT((Long) obj2.get("inIcuCumulative"));
					
					if(obj2.get("onVentilatorCurrently").equals(nulla)) value.setVN(0);
					else value.setVN((Long) obj2.get("onVentilatorCurrently"));
					
					if(obj2.get("onVentilatorCumulative").equals(nulla)) value.setVT(0);
					else value.setVT((Long) obj2.get("onVentilatorCumulative"));
					
					if(obj2.get("death").equals(nulla)) value.setDT(0);
					else value.setDT((Long) obj2.get("death"));
					
					if(obj2.get("deathIncrease").equals(nulla)) value.setDN(0);
					else value.setDN((Long) obj2.get("deathIncrease"));
					
					value.setId((String) obj2.get("hash"));
					
					listaUsa.add(value);
				};
			}
			
			catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException | ParseException e) {
				e.printStackTrace();
			}
	}
}

