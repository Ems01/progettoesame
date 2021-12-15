package exam.EnricOmar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import service.connection;

@SpringBootApplication
public class EnricOmarApplication {

	public static void main(String[] args) {
		
		connection uss = new connection();
		uss.parsingData();
	}

}
