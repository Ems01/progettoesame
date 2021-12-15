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
					
				    long positive, negative, HN, HT, TN, TT, VN, VT, DT, DN;
					
					if(obj2.get("positive").equals(null)) value.setPositive(String.valueOf(0));
					else
					{
						positive = Long.valueOf(value.getPositive()).longValue();
						value.setPositive(String.valueOf(positive));
						value.setPositive((String) obj2.get("positive"));
						}
					
					if(obj2.get("negative").equals(null)) value.setNegative(String.valueOf(0));
					else
					{
						negative = Long.valueOf(value.getNegative()).longValue();
						value.setNegative(String.valueOf(negative));
						value.setNegative((String) obj2.get("negative"));
						}
					
					if(obj2.get("hospitalizedCurrently").equals(null)) value.setHN(String.valueOf(0));
					else
					{
						HN = Long.valueOf(value.getHN()).longValue();
						value.setHN(String.valueOf(HN));
						value.setHN((String) obj2.get("hospitalizedCurrently"));
						}
					
					if(obj2.get("hospitalizedCumulative").equals(null)) value.setHT(String.valueOf(0));
					else
					{
						HT = Long.valueOf(value.getHT()).longValue();
						value.setHN(String.valueOf(HT));
						value.setHT((String) obj2.get("hospitalizedCumulative"));
						}
					
					if(obj2.get("inIcuCurrently").equals(null)) value.setTN(String.valueOf(0));
					else 
					{
						TN = Long.valueOf(value.getTN()).longValue();
						value.setTN(String.valueOf(TN));
						value.setTN((String) obj2.get("inIcuCurrently"));
					    }
					
					if(obj2.get("inIcuCumulative").equals(null)) value.setTT(String.valueOf(0));
					else
					{
						TT = Long.valueOf(value.getTT()).longValue();
						value.setTT(String.valueOf(TT));
						value.setTT((String) obj2.get("inIcuCumulative"));
				        }
					
					if(obj2.get("onVentilatorCurrently").equals(null)) value.setVN(String.valueOf(0));
					else
					{
						VN = Long.valueOf(value.getVN()).longValue();
						value.setVN(String.valueOf(VN));
						value.setVN((String) obj2.get("onVentilatorCurrently"));
				        }
					
					if(obj2.get("onVentilatorCumulative").equals(null)) value.setVT(String.valueOf(0));
					else 
					{
						VT = Long.valueOf(value.getVT()).longValue();
						value.setVT(String.valueOf(VT));
						value.setVT((String) obj2.get("onVentilatorCumulative"));
				        }
					
					if(obj2.get("death").equals(null)) value.setDT(String.valueOf(0));
					else
					{
						DT = Long.valueOf(value.getDT()).longValue();
						value.setDT(String.valueOf(DT));
						value.setDT((String) obj2.get("death"));
						}
					
					if(obj2.get("deathIncrease").equals(null)) value.setDN(String.valueOf(0));
					else
					{
						DN = Long.valueOf(value.getDN()).longValue();
						value.setDN(String.valueOf(DN));
						value.setDN((String) obj2.get("deathIncrease"));
						}
					
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

