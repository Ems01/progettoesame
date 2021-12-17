package model;

public class People {

	private long day=0;
	private long num_states=0; 
	private String name="USA";
	private String id=null;
	private long positive = 0;
	private long positiveIncrease = 0;
	private long negative = 0;
	private long negativeIncrease = 0;
	private long DT = 0; //Death total
	private long DN = 0; //Death now
			
	public void People() {}

	public String getName() {
		return this.name;
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
		this.day = day;
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


	public long getDT() {
		return DT;
	}


	public void setDT(long dT) {
		DT = dT;
	}


	public long getDN() {
		return DN;
	}


	public void setDN(long dN) {
		DN = dN;
	}
}
