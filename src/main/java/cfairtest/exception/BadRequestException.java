package cfairtest.exception;

public class BadRequestException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8248790533619048927L;

	public BadRequestException(String message){
		super(message);
	}
}
