package project.control;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import project.exception.EccezionePersonalizzata;
import project.service.connection;

/**
 * la classe SimpleRestController traccia le 5 rotte che permettono di ottenere una lista e/o una statistica 
 * del file USA.json.
 * 
 * limitazioni parametri:
 * -i giorni del file JSON vanno dal 13.1.2020 al 7.3.2021;
 * -la stringa per inserire una data è come si vede qui sopra;
 * -i colori sono: white, yellow, orange, red;
 * 
 * @author Enrico Maria Sardellini
 * @author Omar Naja
 */

@RestController
public class SimpleRestController {
	
	public connection uss = new connection();

	/**
	 * (/day) permette di ottenere il bollettino covid del giorno inserito
	 * 	 
	 * @author Enrico Maria Sardellini
	 * @author Omar Naja
	 * @param day
	 * @throws project.exception.EccezionePersonalizzata
	 * @see project.service.connection.getToday()
	 */
	@RequestMapping(value = "/day", method = RequestMethod.GET)
	public ResponseEntity<Object> getDay(@RequestParam String day) throws EccezionePersonalizzata {
		return new ResponseEntity<Object>(this.uss.getToday(day), HttpStatus.OK);		
	}

	/**
	 * (/week) permette di ottenere un andamento della pandemia nella settimana,
	 * che ha come 1° giorno la stringa data, e la lista dei bollettini della settimana 
	 * 
	 * @author Enrico Maria Sardellini
	 * @param day
	 * @see project.service.connection.getWeek()
	 */
	@RequestMapping(value = "/week", method = RequestMethod.GET)
	public ResponseEntity<Object> getWeek(@RequestParam String day) {
		return new ResponseEntity<Object>(this.uss.getWeek(day), HttpStatus.OK);
	}
	
	/**
	 * (/month) permette di ottenere un andamento della pandemia nel mese,
	 * e la lista dei bollettini del mese 
	 * 
	 * @author Enrico Maria Sardellini
	 * @param month
	 * @param year
	 * @see project.service.connection.getMonth()
	 */
	@RequestMapping(value = "/month", method = RequestMethod.GET)
	public ResponseEntity<Object> getMonth(@RequestParam String month, String year) {
		return new ResponseEntity<Object>(this.uss.getMonth(month, year), HttpStatus.OK);
	}
	
	/**
	 * (/colour) permette di avere la lista dei giorni aventi quel colore
	 * 
	 * @author Enrico Maria Sardellini
	 * @param colour
	 * @see project.service.connection.getColour()
	 */
	@RequestMapping(value = "/colour", method = RequestMethod.GET)
	public ResponseEntity<Object> getColour(@RequestParam String colour) {
		return new ResponseEntity<Object>(this.uss.getColour(colour), HttpStatus.OK);
	}
	
	/**
	 * (/2days) permette di avere i bollettini dei due giorni e una serie 
	 * di statistiche messe a confronto. se il giorno è lo stesso, verrà stampato solo il 
	 * bollettino di quel giorno
	 * 
	 * @author Enrico Maria Sardellini
	 * @param day1
	 * @param day2
	 * @see project.service.connection.get2days()
	 */
	@RequestMapping(value = "/2days", method = RequestMethod.GET)
	public ResponseEntity<Object> getDays(@RequestParam String day1, String day2) {
		return new ResponseEntity<Object>(this.uss.get2days(day1, day2), HttpStatus.OK);
	}

}