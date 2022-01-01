package project.service;
  	
public class EccezioneGiorno extends IllegalArgumentException {

	public EccezioneGiorno() {
		super();
	}
	
	public EccezioneGiorno(String messaggio) {
		super(messaggio);
	}

}
