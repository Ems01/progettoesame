package exam.EnricOmar;

import project.service.connection;

public class TestException {

	public static void main(String[] args) {

    connection conn = new connection();
    String data;
    
    System.out.println("PROVA 1: 13.11.2020");
    
    data= "13.11.2020";
    conn.getToday(data);
    
    System.out.println("PROVA 2: 13.11.2021");
    
    data= "13.11.2021";
    conn.getToday(data);

	}

}
