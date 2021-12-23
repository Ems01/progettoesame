package model;

public class Hospital implements DatiUSA{
	
	/*
	 * Classe secondaria che contiene alcuni dati in comune con people la cui differenza principale
	 * è contenere i dati ospedalieri per la determinazione del colore degli USA. Presenta:
	 * il numero degli ospedalizzati del giorno(hospitalized) e il numero delle terapie
	 * intensive(intensive_care)
	 * 
	 */
	
	private long day=0;
	private long num_states=0; 
	private String id=null;
	private long hospitalized=0;  
	private long intensive_care=0;
	
	/*
	 * Valori semplificati a costanti, anche se costanti non sono, per la determinazione del colore:
	 * uno è la popolazione degli USA(popolation_USA) e le altre due sono i letti di terapia intensiva totali(ICU_total)
	 * e i letti degli ospedali totali(beds_total) ricavate da tale sito (//https://globalepidemics.org/hospital-capacity-2/)
	 */
	
	static final int popolation_USA = 330000000; 
	static final int ICU_total= 84750; 
	static final int beds_total = 737567;
	
	People p;
	
	public Hospital(String nome) {
		String n = nome;   //nome dell'ospedale
	};

	public long getDay() {
		return day;
	}
	
	public void setDay(long day) {
		String giorno= String.valueOf(day); //ora giorno=day nel formato aaaaggmm
		String appoggio = "";
		String EUFormat = "";
		for (int i=0; i<4;i++) appoggio+=giorno.charAt(i);  //appoggio=aaaa
		for (int i=4;i<giorno.length();i++) EUFormat += giorno.charAt(i);  //EUFOrmat = ggmm
		EUFormat = EUFormat + appoggio;  //EUFormat == ggmmaaaa
		System.out.println(EUFormat);
		this.day = Long.parseLong(EUFormat);
		p.day= Long.parseLong(EUFormat);
	}

	public long getNum_states() {
		return num_states;
	}

	public void setNum_states(long num_states) {
		this.num_states = num_states;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	/*
	 * Il metodo setColour ci permette di ottenere il numero dei casi ogni 100mila abitanti 
	 * ed anche i valori delle occupazioni percentuali delle terapie intensive(perIcu) 
	 * e degli ospedali(perBeds). 
	 * 
	 * (popolation_states) prende la costante (popolation_USA) la divide per il numero totali dei suoi stati (56) e la moltiplica per il
	 * numero di stati effettivi di cui sono stati campionati i dati, generalizzando che ogni stato abbia lo stesso numero di abitanti
	 * 
	 * Il colore verrà poi girato al metodo setColour() dell'oggetto people passatogli per riferimento
	 * 
	 * Per la determinazione dei parametri la fonte viene da tale link
	 * (https://www.ilsole24ore.com/art/come-cambiano-colori-regioni-restano-bianche-sicilia-piu-rischio-contagi-e-ricoveri-AEi3FOY)
	 * 
	 * E' presente anche un'altra funzione che non considera (perBeds)
	 * 
	 */
	

	public void setColour(People people) {
		double popolation_states = (((double)popolation_USA/56)*people.getNum_states());
		double casi = ((double)people.getPositive()/(double)popolation_USA)*100000;
		double perIcu = ((double)intensive_care/(double)ICU_total)*100;
		double perBeds = ((double)hospitalized/(double)beds_total)*100;
		
		String colour=null;
		if(casi < 50) colour = "Bianca";
		else if((casi >= 50) && (casi < 150)) {  
			if((perIcu < 10) || (perBeds < 15)) colour = "Bianca";
			if((perIcu >= 10) && (perBeds >= 15)) colour = "Gialla";
		}else if (casi >= 150) {
			if((perIcu < 20) || (perBeds < 30)) colour = "Gialla";
			if((perIcu >= 20) && (perBeds >= 30)) colour = "Arancione";
			if((perIcu >= 30) && (perBeds >= 40)) colour = "Rossa";
		}
		people.setColour(colour);
		/* 
		if (casi < 50) colour = "bianca";
		else if((casi >= 50) && (casi < 150)) {  
			if((perIcu < 10))colour = "Bianca";
			if((perIcu >= 10)) colour = "Gialla";
		}else if (casi >= 150) {
			if((perIcu < 20)) colour = "gialla";
			if((perIcu >= 20)) colour = "Arancione";
			if((perIcu >= 30)) colour = "Rossa";
		}
		people.setColour(colour);
		*/
	}

	
	public long getHospitalized() {
		return hospitalized;
	}

	
	public void setHospitalized(long HN) {
		this.hospitalized = HN;
	}

	
	public long getIntensive_care() {
		return intensive_care;
	}

	
	public void setIntensive_care(long TN) {
		this.intensive_care = TN;
	}
}
