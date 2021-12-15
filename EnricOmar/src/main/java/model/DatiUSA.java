package model;

public class DatiUSA implements Dati {
	
	private int day=0;
	private int num_states=0; 
	private String name=null;
	private long positive=0;
	private long negative=0;
	private long HN=0;  //Hospitalized Now
	private long HT=0;  //Hospitalized Total
	private long TN=0;  //Therapy intensive Now
	private long TT=0;  //Therapy Intensive Total
	private long VN=0;  //Ventilator Now
	private long VT=0;  //Ventilator Total
	private long DT=0;  //Death Total
	private long DN=0;  //Death Now
	private String id=null;
	private String colour=null;
	
	public DatiUSA() {}; //costruttore
	
	public int getNum_states() {
		return num_states;
	}

	public void setNum_states(int num_states) {
		this.num_states = num_states;
	}

	public int getDay() {
		return day;
	}

	public void setDay(int day) {
		this.day = day;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public long getHN() {
		return HN;
	}

	public void setHN(long hN) {
		HN = hN;
	}

	public long getHT() {
		return HT;
	}

	public void setHT(long hT) {
		HT = hT;
	}

	public long getTN() {
		return TN;
	}

	public void setTN(long tN) {
		TN = tN;
	}

	public long getTT() {
		return TT;
	}

	public void setTT(long tT) {
		TT = tT;
	}

	public long getVN() {
		return VN;
	}

	public void setVN(long vN) {
		VN = vN;
	}

	public long getVT() {
		return VT;
	}

	public void setVT(long vT) {
		VT = vT;
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

	
}
