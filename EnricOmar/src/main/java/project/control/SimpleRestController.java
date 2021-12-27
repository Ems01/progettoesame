package project.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;



import project.service.connection;
import project.model.DatiUSA;

@RestController
public class SimpleRestController {
	public connection uss = new connection();
	

	@RequestMapping(value = "/day", method = RequestMethod.GET)
	public ResponseEntity<Object> getDay(@RequestParam String data) {
		return new ResponseEntity<>(this.uss.getToday(data), HttpStatus.OK);
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String check() {
		return "it works";
	}

	/*
	 *
	 *  getToday()
	       {
		      return new ResponseEntity<>(dati.getDay(), HttpStatus.OK);
	       }

		@GetMapping("/HOSPITAL")
		public Hospital MethodGH(@RequestParam(name="param1", defaultValue="Hospital") String param1 ) {
			return new Hospital("NY");
		}

		@GetMapping("/PEOPLE")
		public People MethodGP(@RequestParam(name="param1", defaultValue="People") String param1 ) {
			return new People("Omar", "Naja");
		}


		@PostMapping("/HOSPITAL")
		public Hospital MethodPH(@RequestBody String body) {
			return new Hospital("NY");
		}

		@PostMapping("/PEOPLE")
		public People MethodPP(@RequestBody String body) {
			return new People("Omar", "Naja");
		}
   */
}