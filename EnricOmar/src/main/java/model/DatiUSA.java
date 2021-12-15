package model;

public class DatiUSA implements Dati {
	
	private long day=0;
	private long num_states=0; 
	private String name=null;
	private String positive=null;
	private String negative=null;
	private String HN=null;  //Hospitalized Now
	private String HT=null;  //Hospitalized Total
	private String TN=null;  //Therapy intensive Now
	private String TT=null;  //Therapy Intensive Total
	private String VN=null;  //Ventilator Now
	private String VT=null;  //Ventilator Total
	private String DT=null;  //Death Total
	private String DN=null;  //Death Now
	private String id=null;
	private String colour=null;
	
	public DatiUSA() {}; //costruttore
	
	public long getNum_states() {
		return num_states;
	}

	public void setNum_states(long num_states) {
		this.num_states = num_states;
	}

	public long getDay() {
		return day;
	}

	public void setDay(long day) {
		this.day = day;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPositive() {
		return positive;
	}

	public void setPositive(String positive) {
		this.positive = positive;
	}

	public String getNegative() {
		return negative;
	}

	public void setNegative(String negative) {
		this.negative = negative;
	}

	public String getHN() {
		return HN;
	}

	public void setHN(String hN) {
		HN = hN;
	}

	public String getHT() {
		return HT;
	}

	public void setHT(String hT) {
		HT = hT;
	}

	public String getTN() {
		return TN;
	}

	public void setTN(String tN) {
		TN = tN;
	}

	public String getTT() {
		return TT;
	}

	public void setTT(String tT) {
		TT = tT;
	}

	public String getVN() {
		return VN;
	}

	public void setVN(String vN) {
		VN = vN;
	}

	public String getVT() {
		return VT;
	}

	public void setVT(String vT) {
		VT = vT;
	}

	public String getDT() {
		return DT;
	}

	public void setDT(String dT) {
		DT = dT;
	}

	public String getDN() {
		return DN;
	}

	public void setDN(String dN) {
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
