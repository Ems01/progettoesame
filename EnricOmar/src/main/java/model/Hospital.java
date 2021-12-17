package model;

public class Hospital {
	
	private long day=0;
	private long num_states=0; 
	private String name="USA";
	private String id=null;
	private String colour=null;
	private long hospitalized=0;  
	private long intensive_care=0;
	
	static final int popolation_USA = 330000000; //330 milioni circa
	static final int ICU_total= 84750; //https://globalepidemics.org/hospital-capacity-2/
	static final int beds_total = 737567;
	
	public void Hospital() {};

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

	public void setColour(long positive) {
		double casi = ((double)positive/(double)popolation_USA)*100000;
		double perIcu = ((double)intensive_care/(double)ICU_total)*100;
		double perBeds = ((double)hospitalized/(double)beds_total)*100;
		// https://www.ilsole24ore.com/art/come-cambiano-colori-regioni-restano-bianche-sicilia-piu-rischio-contagi-e-ricoveri-AEi3FOY
		if(casi < 50) this.colour = "Bianca";
		else if((casi >= 50) && (casi < 150)) {  
			if((perIcu < 10) || (perBeds < 15))this.colour = "Bianca";
			if((perIcu >= 10) && (perBeds >= 15)) this.colour = "Gialla";
		}else if (casi >= 150) {
			if((perIcu < 20) || (perBeds < 30)) this.colour = "Gialla";
			if((perIcu >= 20) && (perBeds >= 30)) this.colour = "Arancione";
			if((perIcu >= 30) && (perBeds >= 40)) this.colour = "Rossa";
		}
		/*
		 * metodo alternativo dove non vengono contate solo le terapie intensive e questo permette la comparsa della regione arancione
		 * 
		if (casi < 50) this.colour = "bianca";
		else if((casi >= 50) && (casi < 150)) {  
			if((perIcu < 10))this.colour = "Bianca";
			if((perIcu >= 10)) this.colour = "Gialla";
		}else if (casi >= 150) {
			if((perIcu < 20)) this.colour = "gialla";
			if((perIcu >= 20)) this.colour = "Arancione";
			if((perIcu >= 30)) this.colour = "Rossa";
		}
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
