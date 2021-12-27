package project.service;

public class ignore {
	
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
}
