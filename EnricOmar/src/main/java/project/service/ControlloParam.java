package project.service;

import project.exception.EccezionePersonalizzata;

public class ControlloParam implements Int_ControlloParam{
	
	public ControlloParam() {};
	
	public void ControlDay(String day) {
		
		char punto = '.';
		int lunghezza = day.length();
		
		if((lunghezza<=7) && (lunghezza>=11)) throw new EccezionePersonalizzata("The writing of the day is not suitable! Examples of days (12.1.2020 or 3.2.2021)"); 
		if(!ControlYear(day)) throw new EccezionePersonalizzata("Invalid year entered! Only 2020 & 2021 are accepted");
		
		if (lunghezza==8) {
			if((Character.compare(day.charAt(1),punto)!=0) || (Character.compare(day.charAt(3), punto)!=0)) throw new EccezionePersonalizzata("Days, months and years are separated by a '.'! Examples of days (2.7.2020 or 3.2.2021)"); 
			if(Character.compare(day.charAt(0),'0')==0) throw new EccezionePersonalizzata("The day cannot be '0'!");
			if(Character.compare(day.charAt(2),'0')==0) throw new EccezionePersonalizzata("The month cannot be '0'!");
		}
		else if(lunghezza==9) {
			if((Character.compare(day.charAt(1),punto)==0) && (Character.compare(day.charAt(4), punto)==0)) {
				if(Character.compare(day.charAt(0),'0')==0) throw new EccezionePersonalizzata("The day cannot be '0'!");
				if(Character.compare(day.charAt(2),'0')==0) throw new EccezionePersonalizzata("The month cannot have '0'!");
			}
			else if((Character.compare(day.charAt(2),punto)==0) && (Character.compare(day.charAt(4), punto)==0)) {
				if(Character.compare(day.charAt(0),'0')==0) throw new EccezionePersonalizzata("The day cannot have '0'!");
				if(Character.compare(day.charAt(3),'0')==0) throw new EccezionePersonalizzata("The month cannot be'0'!");
			}
			else throw new EccezionePersonalizzata("Days, months and years are separated by a '.'! Examples of days (12.1.2021 or 7.10.2020)");
		}
		else if(lunghezza==10) {
			if((Character.compare(day.charAt(2),punto)!=0) || (Character.compare(day.charAt(5), punto)!=0)) throw new EccezionePersonalizzata("Days, months and years are separated by a '.'! Examples of days (12.11.2020 or 22.12.2020)"); 
			if(Character.compare(day.charAt(0),'0')==0) throw new EccezionePersonalizzata("The day cannot have '0'!");
			if(Character.compare(day.charAt(3),'0')==0) throw new EccezionePersonalizzata("The month cannot have '0'!");
		}
	};

	public String ControlColour(String finale) {
		
		String colour;
		String mistake = "Colour not found! The possible colors are: red, orange, yellow and white";
		
		switch(finale) {
		case "white", "WHITE", "White": colour = "White"; break; 
		case "yellow", "YELLOW", "Yellow": colour = "Yellow"; break; 
		case "orange", "ORANGE", "Orange": colour = "Orange";break; 
		case "red", "RED", "Red": colour = "Red";break; 
		default: colour = "Error"; break;
		}
		if(colour.equals("Error")) throw new EccezionePersonalizzata(mistake);
		return colour;
	};
	
	public int ControlMonth(String month, String year) {
		
		int m = 0; 
		
		switch(month) {
		case "1", "january" , "January", "JANUARY": m= 1; break; 
		case "2","february", "February", "FEBRUARY":  m=2; break;
		case "3", "march", "March", "MARCH": m=3; break; 
		case "4", "April", "april", "APRIL":  m=4; break; 
		case "5", "may", "May", "MAY": m=5; break; 
		case "6", "june", "JUNE", "June": m=6; break; 
		case "7", "july", "July", "JULY": m=7; break; 
		case "8", "august", "August", "AUGUST": m=8; break; 
		case "9", "September", "SEPTEMBER", "september": m=9; break; 
		case "10", "October", "october", "OCTOBER": m=10; break; 
		case "11", "november", "November", "NOVEMBER": m=11; break; 
		case "12", "December", "december", "DECEMBER": m=12; break; 
		default: break; 
		}
		
		if(m>=4 && year.equals("2021")) throw new EccezionePersonalizzata("Month not found! It runs from January 2020 to March 2021");
		if(m == 0) throw new EccezionePersonalizzata("Invalid month entered!");
		if(!year.equals("2020") && !year.equals("2021")) throw new EccezionePersonalizzata("Invalid year entered! Only 2020 & 2021 are accepted");
		
		return m;
	};

	public boolean ControlYear(String day) {
		boolean done = false;
		int m = day.length();
		if((Character.compare(day.charAt(m-1),'1')==0) && (Character.compare(day.charAt(m-1),'0')==0)) {
			if(Character.compare(day.charAt(m-2),'2')==0) {
				if(Character.compare(day.charAt(m-3),'0')==0){
					if(Character.compare(day.charAt(m-4),'2')==0) done = true; 
				}
			}
		}
		return done;
	}

	public void ControlWeek(String day) {
		if(day.equals("1.3.2021")) throw new EccezionePersonalizzata("The '/week' route only accepts the days from 13.1.2020 to 28.2.2021!");
		if(day.equals("2.3.2021")) throw new EccezionePersonalizzata("The '/week' route only accepts the days from 13.1.2020 to 28.2.2021!");
		if(day.equals("3.3.2021")) throw new EccezionePersonalizzata("The '/week' route only accepts the days from 13.1.2020 to 28.2.2021!");
		if(day.equals("4.3.2021")) throw new EccezionePersonalizzata("The '/week' route only accepts the days from 13.1.2020 to 28.2.2021!");
		if(day.equals("5.3.2021")) throw new EccezionePersonalizzata("The '/week' route only accepts the days from 13.1.2020 to 28.2.2021!");
		if(day.equals("6.3.2021")) throw new EccezionePersonalizzata("The '/week' route only accepts the days from 13.1.2020 to 28.2.2021!");
		if(day.equals("7.3.2021")) throw new EccezionePersonalizzata("The '/week' route only accepts the days from 13.1.2020 to 28.2.2021!");
	}
}
