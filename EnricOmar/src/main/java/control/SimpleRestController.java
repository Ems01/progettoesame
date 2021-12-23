package control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import model.Hospital;
import model.People;

@RestController
public class SimpleRestController {
	
	@Autowired 
	Hospital hostipal;
	
	public ResponseEntity<Object>
	       getHospital() {
		return new ResponeEntity<>(Hospital.get)
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


}

