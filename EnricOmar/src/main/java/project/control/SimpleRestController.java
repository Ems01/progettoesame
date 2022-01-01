package project.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



import project.service.connection;

@RestController
public class SimpleRestController {
	
	public connection uss = new connection();

	@RequestMapping(value = "/day", method = RequestMethod.GET)
	public ResponseEntity<Object> getDay(@RequestParam String data) throws Exception{
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