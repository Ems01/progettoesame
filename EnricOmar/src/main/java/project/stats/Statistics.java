package project.stats;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;

import project.model.DatiUSA;
import project.model.DatiHospital;

public class Statistics implements Statistics_interface{

		static final int population_USA = 330000000; 
		static final int ICU_total= 84750; 
		static final int beds_total = 737567;
		
		public Statistics() {};
		
		public void StatsLong(ArrayList<DatiUSA> vett1, ArrayList<DatiHospital> vett2, JSONArray array, Integer i, Integer dayfinal) {
			JSONObject obj = new JSONObject();
			if(dayfinal == 7) obj.put("Week:", vett1.get(i).getDay() + "-" + vett1.get(i-7).getDay());
			else obj.put("Month:", vett1.get(i).getDay() + "-" + vett1.get(i-dayfinal).getDay());
			//
			long positive=0, negative=0, death=0;
			for (int j=0; j<dayfinal; j++) {
				positive += vett1.get(i-j).getPositiveIncrease();
			    negative += vett1.get(i-j).getNegativeIncrease();
			    death += vett1.get(i-j).getDeathIncrease();
			}	
			if (dayfinal ==7) obj.put("Death in the week:", "+" + death);
			else obj.put("Death in the months:", "+" + death);
			
			/*
			 * percentuale positivi
			 */
			double percPos1 = ((double)vett1.get(i).getPositive()/(double)population_USA)*100; 
			percPos1 = Math.round(percPos1*100.0);
			double percPos2 = ((double)vett1.get(i-dayfinal).getPositive()/(double)population_USA)*100;
			percPos2 = Math.round(percPos2*100.0);
			double aumentoPos = percPos2-percPos1;
			if(dayfinal ==7) {
				if (percPos1 >= percPos2) obj.put("Positive percentage in the week:", aumentoPos + "%");
				else obj.put("Positive percentage in the week:", "+" + aumentoPos + "%");
				obj.put("Positive increase in the week:", positive);
			}
			else {
				if (percPos1 >= percPos2) obj.put("Positive percentage in the month:", aumentoPos + "%");
				else obj.put("Positive percentage in the month:", "+" + aumentoPos + "%");
				obj.put("Positive increase in the month:", positive);
			}
			/*
			 * percentuale negativi
			 */
			double percNeg1 = ((double)vett1.get(i).getNegative()/(double)population_USA)*100; 
			percNeg1 = Math.round(percNeg1*100.0);
			double percNeg2 = ((double)vett1.get(i-dayfinal).getNegative()/(double)population_USA)*100;
			percNeg2 = Math.round(percNeg2*100.0);
			double aumentoNeg = percNeg2-percNeg1;
			if(dayfinal ==7) {
				if (percNeg1 >= percNeg2) obj.put("Negative percentage in the week", aumentoNeg + "%");
				else obj.put("Negative percentage in the week", "+" + aumentoNeg + "%");
				obj.put("Negative increase in the week", negative);
			}
			else {
				if (percNeg1 >= percNeg2) obj.put("Negative percentage in the month:", aumentoNeg + "%");
				else obj.put("Negative percentage in the month:", "+" + aumentoNeg + "%");
				obj.put("Negative increase in the month:", negative);
			}
			/*
			 * percentuale ospedalizzazioni
			 */
			double percHos1 = ((double)vett2.get(i).getHospitalized()/(double)beds_total)*100; 
			percHos1 = Math.round(percHos1*100.0);
			double percHos2 = ((double)vett2.get(i-dayfinal).getHospitalized()/(double)beds_total)*100;
			percHos2 = Math.round(percHos2*100.0);
			double aumentoHos = percHos2-percHos1;
			if(dayfinal ==7) {
				if (percHos1 >= percHos2) {
					obj.put("Hospitalized percentage in the week:", aumentoHos + "%");
					obj.put("Hospitalized in the week:", (vett2.get(i-dayfinal).getHospitalized()-vett2.get(i).getHospitalized()));
				}
				else {
					obj.put("Hospitalized percentage in the week:", "+" + aumentoHos + "%");
					obj.put("Hospitalized in the week:", "+" + (vett2.get(i-dayfinal).getHospitalized()-vett2.get(i).getHospitalized()));
				}
			}
			else {
				if (percHos1 >= percHos2) {
					obj.put("Hospitalized percentage in the month:", aumentoHos + "%");
					obj.put("Hospitalized in the month:", (vett2.get(i-dayfinal).getHospitalized()-vett2.get(i).getHospitalized()));
				}
				else {
					obj.put("Hospitalized percentage in the month:", "+" + aumentoHos + "%");
					obj.put("Hospitalized in the month:", "+" + (vett2.get(i-dayfinal).getHospitalized()-vett2.get(i).getHospitalized()));
				}
			}
			/*
			 * percentuale terapie intensive
			 */
			double percIcu1 = ((double)vett2.get(i).getIntensive_care()/(double)ICU_total)*100; 
			percIcu1 = Math.round(percIcu1*100.0);
			double percIcu2 = ((double)vett2.get(i-dayfinal).getIntensive_care()/(double)ICU_total)*100;
			percIcu2 = Math.round(percIcu2*100.0);
			double aumentoIcu = percIcu2-percIcu1;
			if(dayfinal ==7) {
				if (percIcu1 >= percIcu2) {
					obj.put("Percentage of intensive care in the week:", aumentoIcu + "%");
					obj.put("Intensive care in the week:", (vett2.get(i-dayfinal).getHospitalized()-vett2.get(i).getHospitalized()));
				}
				else {
					obj.put("Percentage of intensive care during the week:", "+" + aumentoIcu + "%");
					obj.put("Intensive care in the week:", "+" + (vett2.get(i-dayfinal).getHospitalized()-vett2.get(i).getHospitalized()));	
				}
			}
			else {
				if (percIcu1 >= percIcu2) {
					obj.put("Percentage of intensive care in the month:", aumentoIcu + "%");
					obj.put("Intensive care in the month:", (vett2.get(i-dayfinal).getHospitalized()-vett2.get(i).getHospitalized()));
				}
				else {
					obj.put("Percentage of intensive care during the month:", "+" + aumentoIcu + "%");
					obj.put("Intensive care in the month:", "+" + (vett2.get(i-dayfinal).getHospitalized()-vett2.get(i).getHospitalized()));	
				}
			}
			
			array.add(obj);
		}

		public void Stats2day(ArrayList<DatiUSA> vett1, ArrayList<DatiHospital> vett2, JSONArray array, String day1, String day2) {
			JSONObject obj = new JSONObject();
			obj.put("Days:", day1 + " and " + day2);
			int i=0, j=0;
			for(i=0; i<vett1.size(); i++) {
				if(day1.equals(vett1.get(i).getDay())) break; 
			}
			for(j=0; j<vett1.size(); j++) {
				if(day1.equals(vett1.get(j).getDay())) break; 
			}
			/*
			 * percentuale positivi
			 */

			double percPos1 = (double)vett1.get(i).getPositive();
			double percPos2 = (double)vett1.get(j).getPositive();
			double aumentoPos = (percPos2-percPos1/(double)population_USA)*100;
			aumentoPos = Math.round(percPos1*100.0);
			if (i>=j) {
				if(percPos1 >= percPos2) obj.put("Positive percentage:", "+" + aumentoPos + "%");
				else obj.put("Positive percentage:", aumentoPos + "%");
			}
			else {
				if(percPos1 >= percPos2) obj.put("Positive percentage:", aumentoPos + "%");
				else obj.put("Positive percentage:", "+" + aumentoPos + "%");
			}
			/*
			 * percentuale negativi
			 */
			double percNeg1 = (double)vett1.get(i).getNegative();
			double percNeg2 = (double)vett1.get(j).getNegative();
			double aumentoNeg = (percNeg2-percNeg1/(double)population_USA)*100;
			aumentoNeg = Math.round(aumentoNeg);
			if(i>=j) {
				if (percNeg1 >= percNeg2) obj.put("Negative percentage:", "+" + aumentoNeg + "%");
				else obj.put("Negative percentage:", aumentoNeg + "%");
			}
			else {
				if (percNeg1 >= percNeg2) obj.put("Negative percentage:", aumentoNeg + "%");
				else obj.put("Negative percentage:", "+" + aumentoNeg + "%");
			}
			/*
			 * percentuale ospedalizzazioni
			 */
			double percHos1 = (double)vett2.get(i).getHospitalized();
			double percHos2 = (double)vett2.get(j).getHospitalized();
			double aumentoHos = (percHos2-percHos1/(double)population_USA)*100;
			aumentoHos = Math.round(aumentoHos);
			if(i>=j) {
				if (percHos1 >= percHos2) obj.put("Hospitalized percentage:", "+" + aumentoHos + "%");
				else obj.put("Hospitalized percentage:", aumentoHos + "%");
			}
			else {
				if (percHos1 >= percHos2) obj.put("Hospitalized percentage:", aumentoHos + "%");
				else obj.put("Hospitalized percentage:", "+" + aumentoHos + "%");
			}
			/*
			 * percentuale terapie intensive
			 */
			double percIcu1 = (double)vett2.get(i).getIntensive_care(); 
			double percIcu2 = (double)vett2.get(j).getIntensive_care();
			double aumentoIcu = (percIcu2-percIcu1/(double)ICU_total)*100;
			if(i>=j) {
				if (percIcu1 >= percIcu2) obj.put("Percentage of intensive care:", "+" + aumentoIcu + "%");
				else obj.put("Percentage of intensive care:", aumentoIcu + "%");
			}
			else {
				if (percIcu1 >= percIcu2) obj.put("Percentage of intensive care:", aumentoIcu + "%");
				else obj.put("Percentage of intensive care:", "+" + aumentoIcu + "%");
			}
			
			array.add(obj);
		};
}
