package model;

public class DatiUSA implements Dati {
	
	private int day=0;
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

	public DatiUSA(int day, long positive, long negative, long HN, long HT, long TN, long TT, long VN, long VT, long DT, long DN) {
		this.day=day;
		this.name="USA";
		this.positive=positive;
		this.negative=negative;
		this.HN=HN;
		this.HT=HT;
		this.TN=TN;
		this.TT=TT;
		this.VN=VN;
		this.VT=VT;
		this.DT=DT;
		this.DN=DN;
		this.id=id;
	}

}
