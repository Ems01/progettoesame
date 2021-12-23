package model;

public interface DatiUSA_Interface {
	
	/*
	 * interfaccia di DatiUSA per ordinare i vare attributi e metodi
	 */
	
	public String day=null;  
	public long num_states=0; 
	public String id=null;
	public String colour = null;
	public long positive = 0;
	public long positiveIncrease = 0;
	public long negative = 0;
	public long negativeIncrease = 0; 
	public long deathIncrease = 0; 
	public long hospitalized=0;  
	public long intensive_care=0;
	
	public String getDay();
	public void setDay(long day);
	public long getNum_states();
	public void setNum_states(long num_states);
	public String getId();
	public void setId(String id);
	public String getColour();
	public void setColour();
	public long getPositive();
	public void setPositive(long positive);
	public long getPositiveIncrease();
	public void setPositiveIncrease(long positiveIncrease);
	public long getNegative();
	public void setNegative(long negative);
	public long getNegativeIncrease();
	public void setNegativeIncrease(long negativeIncrease);
	public long getDeathIncrease();
	public void setDeathIncrease(long deathIncrease);
	public long getHospitalized();
	public void setHospitalized(long hospitalized);
	public long getIntensive_care();
	public void setIntensive_care(long intensive_care);
	
}
