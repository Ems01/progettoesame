package project.model;

public class DatiUSA implements DatiUSA_Interface {
	
	/*
	 * Classe in cui verranno raccolte le principali informazioni di ogni giorno:
	 * saranno sempre presenti il numero dei positivi(positive), dei negativi(negative) 
	 * e il numero dei morti(death) e i numeri dei relativi aumenti.
	 * Oltre ciò saranno presenti i numeri dei posti letto sia in generale (hospitalized)
	 * sia delle terapie intensive (intensive_care) occupate.
	 * 
	 * altri attributi come il giorno (day), il numero degli stati coinvolti (num_states) 
	 * e il colore (colour), saranno utili nel controller
	 * 
	 */
	
	private String day=null;  
	private long num_states=0; 
	private String id=null;
	private String colour = null;
	private long positive = 0;
	private long positiveIncrease = 0;
	private long negative = 0;
	private long negativeIncrease = 0; 
	private long deathIncrease = 0; 
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
	
	public DatiUSA () {}

	public String getDay() {
		return day;
	}

	public void setDay(long day) {
		String gg, mm, aaaa, finale;
		gg = String.valueOf(day%100);
		mm = String.valueOf(((day%10000) - (day%100))/100);
		aaaa = String.valueOf(day/10000);
		finale = gg + "." + mm + "." + aaaa;
		this.day = finale;
		//System.out.println(this.day);
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

	public String getColour() {
		return colour;
	}

	public long getPositive() {
		return positive;
	}

	public void setPositive(long positive) {
		this.positive = positive;
	}

	public long getPositiveIncrease() {
		return positiveIncrease;
	}

	public void setPositiveIncrease(long positiveIncrease) {
		this.positiveIncrease = positiveIncrease;
	}

	public long getNegative() {
		return negative;
	}

	public void setNegative(long negative) {
		this.negative = negative;
	}

	public long getNegativeIncrease() {
		return negativeIncrease;
	}

	public void setNegativeIncrease(long negativeIncrease) {
		this.negativeIncrease = negativeIncrease;
	}
	
	public long getDeathIncrease() {
		return deathIncrease;
	}

	public void setDeathIncrease(long deathIncrease) {
		this.deathIncrease = deathIncrease;
	}

	public long getHospitalized() {
		return hospitalized;
	}

	public void setHospitalized(long hospitalized) {
		this.hospitalized = hospitalized;
	}

	public long getIntensive_care() {
		return intensive_care;
	}

	public void setIntensive_care(long intensive_care) {
		this.intensive_care = intensive_care;
	};
	
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
	

	public void setColour() {
		double popolation_states = (((double)popolation_USA/56)*this.getNum_states());
		double casi = ((double)this.getPositive()/(double)popolation_USA)*100000;
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
		this.colour = colour;
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
		this.colour = colour;
		*/
	}
	
}
