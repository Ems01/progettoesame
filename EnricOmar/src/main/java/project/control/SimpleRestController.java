package project.control;

import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import project.service.EccezioneGiorno;
import project.service.connection;

/*
 * la classe SimpleRestController traccia le 5 rotte che permettono di ottenere una lista e/o una statistica 
 * del file USA.json.
 * 
 * le rotte permettono:
 * -di ottenere un giorno (day) passandogli una stringa (data) rapressentante il giorno
 * -i giorni di una settimana (week) passandogli una stringa data rappresentante il primo giorno e andando a calcolarne i successivi 7
 * -i giorni di un mese(month) passandogli due stringe (month & year) rappresentante i mesi e l'anno da considerare
 * -i giorni di un determinato colore(colour) passandogli la stringa (colour) contenente il colore da considerare
 * -2 giorni scelti(2days) passandogli due date differenti (data1 & data2) 
 * 
 * limitazioni file:
 * -i giorni del file JSON vanno dal 13.1.2020 al 7.3.2021;
 * -la stringa per inserire una data è come si vede qui sopra;
 * -i colori sono: white, yellow, orange, red;
 */

@RestController
public class SimpleRestController {
	
	public connection uss = new connection();

	@RequestMapping(value = "/day", method = RequestMethod.GET)
	public ResponseEntity<Object> getDay(@RequestParam String data) throws EccezioneGiorno {
		return new ResponseEntity<Object>(this.uss.getToday(data), HttpStatus.OK);		
	}

	@RequestMapping(value = "/week", method = RequestMethod.GET)
	public ResponseEntity<Object> getWeek(@RequestParam String data) {
		return new ResponseEntity<Object>(this.uss.getWeek(data), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/month", method = RequestMethod.GET)
	public ResponseEntity<Object> getMonth(@RequestParam String month, String year) {
		return new ResponseEntity<Object>(this.uss.getMonth(month, year), HttpStatus.OK);
	}
		
	@RequestMapping(value = "/colour", method = RequestMethod.GET)
	public ResponseEntity<Object> getColour(@RequestParam String colour) {
		return new ResponseEntity<Object>(this.uss.getColour(colour), HttpStatus.OK);
	}
	
	@RequestMapping(value = "/2days", method = RequestMethod.GET)
	public ResponseEntity<Object> getDays(@RequestParam String data1, String data2) {
		return new ResponseEntity<Object>(this.uss.get2days(data1, data2), HttpStatus.OK);
	}

}