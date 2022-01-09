package project.service;
 

/**
 * 
 * 
 * @author Omar Naja
 */
public class EccezioneGiorno extends IllegalArgumentException {

	private static final long serialVersionUID = 1L;

	public EccezioneGiorno() {
		super();
	}
	
	public EccezioneGiorno(String messaggio) {
		super(messaggio);
	}

}