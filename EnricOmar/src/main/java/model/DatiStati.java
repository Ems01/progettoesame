package model;

public class DatiStati implements Dati {
	
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

	public DatiStati(int day, String name, long positive, long negative, long HN, long HT, long TN, long TT, long VN, long VT, long DT, long DN) {
		this.day=day;
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
		switch(name) {
		case "AK": this.name = "Alaska"; break; 
		case "AL": this.name = "Alabama"; break;
		case "AR": this.name = "Arkansas"; break; 
		case "AS": this.name = "Samoa Americane"; break;
		case "AZ": this.name = "Arizona"; break;
		case "CA": this.name = "California"; break;
		case "CO": this.name = "Colorado"; break;
		case "CT": this.name = "Connecticut"; break;
		case "DC": this.name = "District of Columbia"; break;
		case "DE": this.name = "Delaware"; break;
		case "FL": this.name = "Florida"; break;
		case "GA": this.name = "Georgia"; break;
		case "GU": this.name = "Guam"; break;
		case "HI": this.name = "Hawaii"; break;
		case "IA": this.name = "Iowa"; break;
		case "ID": this.name = "Idaho"; break;
		case "IL": this.name = "Illinois"; break;
		case "IN": this.name = "Indiana"; break;
		case "KS": this.name = "Kansas"; break;
		case "KY": this.name = "Kentucky"; break;
		case "LA": this.name = "Louisiana"; break;
		case "MA": this.name = "Mssachusetts"; break;
		case "MD": this.name = "Maryland"; break;
		case "ME": this.name = "Maine"; break;
		case "MI": this.name = "Michigan"; break;
		case "MN": this.name = "Minnesota"; break;
		case "MO": this.name = "Missouri"; break;
		case "MP": this.name = "Isole Marianne Settentrionali"; break;
		case "MS": this.name = "Mississippi"; break;
		case "MT": this.name = "Montana"; break;
		case "NC": this.name = "Carolina del Nord"; break;
		case "ND": this.name = "Dakota del Nord"; break;
		case "NE": this.name = "Nebraska"; break;
		case "NH": this.name = "New Hampshire"; break;
		case "NJ": this.name = "New Jersey"; break;
		case "NM": this.name = "Nuovo Messico"; break;
		case "NV": this.name = "Nevada"; break;
		case "NY": this.name = "New York"; break;
		case "OH": this.name = "Ohio"; break;
		case "OK": this.name = "Oklahoma"; break;
		case "OR": this.name = "Oregon"; break;
		case "PA": this.name = "Pennsylvania"; break;
		case "PR": this.name = "Porto Rico"; break;
		case "RI": this.name = "Rhode Island"; break;
		case "SC": this.name = "Carolina del Sud"; break;
		case "SD": this.name = "Dakota del Sud"; break;
		case "TN": this.name = "Tennessee"; break;
		case "TX": this.name = "Texas"; break;
		case "UT": this.name = "Utah"; break;
		case "VA": this.name = "Virginia"; break;
		case "VI": this.name = "Isole Vergini Americane"; break;
		case "VT": this.name = "Vermont"; break;
		case "WA": this.name = "Washington"; break;
		case "WI": this.name = "Wisconsin"; break;
		case "WV": this.name = "Virginia Occidentale"; break;
		case "WY": this.name = "Wyoming"; break;
		}
	}

}
