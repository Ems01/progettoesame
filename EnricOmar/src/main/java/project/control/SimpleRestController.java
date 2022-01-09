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
	 * @param data
	 * @throws EccezioneGiorno
	 */
	@RequestMapping(value = "/day", method = RequestMethod.GET)
	public ResponseEntity<Object> getDay(@RequestParam String data) throws EccezioneGiorno {
		return new ResponseEntity<Object>(this.uss.getToday(data), HttpStatus.OK);		
	}

	/**
	 * (/week) permette di ottenere un andamento della pandemia nella settimana,
	 * che ha come 1° giorno la stringa data, e la lista dei bollettini della settimana 
	 * 
	 * @author Enrico Maria Sardellini
	 * @param data
	 */
	@RequestMapping(value = "/week", method = RequestMethod.GET)
	public ResponseEntity<Object> getWeek(@RequestParam String data) {
		return new ResponseEntity<Object>(this.uss.getWeek(data), HttpStatus.OK);
	}
	
	/**
	 * (/month) permette di ottenere un andamento della pandemia nel mese,
	 * e la lista dei bollettini del mese 
	 * 
	 * @author Enrico Maria Sardellini
	 * @param month
	 * @param year
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
	 * @param data1
	 * @param data2
	 */
	@RequestMapping(value = "/2days", method = RequestMethod.GET)
	public ResponseEntity<Object> getDays(@RequestParam String data1, String data2) {
		return new ResponseEntity<Object>(this.uss.get2days(data1, data2), HttpStatus.OK);
	}

}