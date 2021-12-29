package project.model;

public class DatiUSA implements Dati{
	
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
	private String colour = null;
	private long positive = 0;
	private long positiveIncrease = 0;
	private long negative = 0;
	private long negativeIncrease = 0; 
	private long deathIncrease = 0; 
	
	
	/*
	 * Valori semplificati a costanti, anche se costanti non sono, per la determinazione del colore:
	 * uno è la popolazione degli USA(popolation_USA) e le altre due sono i letti di terapia intensiva totali(ICU_total)
	 * e i letti degli ospedali totali(beds_total) ricavate da tale sito (//https://globalepidemics.org/hospital-capacity-2/)
	 */
	
	
	
	public DatiUSA () {}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public long getNum_states() {
		return num_states;
	}

	public void setNum_states(long num_states) {
		this.num_states = num_states;
	}

	public String getColour() {
		return colour;
	}
	
	public void setColour(String colour) {
		this.colour = colour;
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

	
}
