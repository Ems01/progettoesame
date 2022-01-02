package project.model;

public class DatiUSA implements Dati{
	
	/*
	 * Sottoclasse principale in cui verranno raccolte le informazioni di ogni giorno:
	 * saranno sempre presenti il numero dei positivi(positiveincrease), 
	 * dei negativi(negativeincrease) e il numero dei morti(deathincrease) di tale giorno
	 * ed il totale dei positivi (positive) e dei negativi (negative);
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
