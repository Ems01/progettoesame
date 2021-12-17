package model;

public interface DatiUSA {
	
	/*
	 * interfaccia di Hospital and people per ordinare i vare attributi e metodi
	 */
	
	public long day=0;
	public long num_states=0; 
	public String id=null;
	
	public abstract long getDay();
	public abstract long getNum_states();
	public abstract String getId();
	
	public void setDay(long day);
	public void setNum_states(long num_states);
	public void setId(String id);
		
}
