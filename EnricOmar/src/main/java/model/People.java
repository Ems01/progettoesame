package model;

public class People implements DatiUSA{
	
	/*
	 * Classe principale in cui verranno raccolte le principali informazioni di ogni giorno:
	 * saranno sempre presenti il numero dei positivi(positive), dei negativi(negative) 
	 * e il numero dei morti(death) e i numeri dei relativi aumenti. I restanti attributi sono specificati nell'interfaccia; 
	 * 
	 */

	protected long day=0;  //consente la modifica della data attraverso il metodo setDay implementato su hospital
	private long num_states=0; 
	private String id=null;
	private String colour = null;
	private long positive = 0;
	private long positiveIncrease = 0;
	private long negative = 0;
	private long negativeIncrease = 0;
	private long death = 0; 
	private long deathIncrease = 0; 
			
	public People(String nome, String cognome) {
		String n = nome;
		String c = cognome;
	}
	
	public long getPositiveIncrease() {
		return positiveIncrease;
	}

	public void setPositiveIncrease(long positiveIncrease) {
		this.positiveIncrease = positiveIncrease;
	}


	public long getNegativeIncrease() {
		return negativeIncrease;
	}


	public void setNegativeIncrease(long negativeIncrease) {
		this.negativeIncrease = negativeIncrease;
	}


	public long getDay() {
		return day;
	}
	public void setDay(long day) {
		this.day=day; 
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
	
	public long getPositive() {
		return positive;
	}

	
	public void setPositive(long positive) {
		this.positive = positive;
	}


	public long getNegative() {
		return negative;
	}


	public void setNegative(long negative) {
		this.negative = negative;
	}


	public long getDeath() {
		return this.death;
	}


	public void setDeath(long DT) {
		this.death = DT;
	}


	public long getDeathIncrease() {
		return this.deathIncrease;
	}


	public void setDeathIncrease(long DN) {
		this.deathIncrease = DN;
	}

	public String getColour() {
		return colour;
	}

	public void setColour(String colour) {
		this.colour = colour;
	}
}
