package model;

public class Hospital {
	
	private long day=0;
	private long num_states=0; 
	private String name="USA";
	private String id=null;
	private String colour=null;
	private long HN=0;  //Hospitalized Now
	private long HT=0;  //Hospitalized Total
	private long TN=0;  //Therapy intensive Now
	private long TT=0;  //Therapy Intensive Total
	
	static final int ICU= 84750; //https://globalepidemics.org/hospital-capacity-2/
	static final int beds = 737567;
	
	public void Hospital() {}

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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public void setColour(String colour) {
		this.colour = colour;
	}

	
	public long getHN() {
		return HN;
	}

	
	public void setHN(long HN) {
		this.HN = HN;
	}

	
	public long getHT() {
		return HT;
	}

	
	public void setHT(long HT) {
		this.HT = HT;
	}

	
	public long getTN() {
		return TN;
	}

	
	public void setTN(long TN) {
		this.TN = TN;
	}

	
	public long getTT() {
		return TT;
	}

	
	public void setTT(long TT) {
		this.TT = TT;
	}

}
