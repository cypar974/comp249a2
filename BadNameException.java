public class BadNameException extends Exception {

	public BadNameException () {
		
		super("Invalid Actor Name.");
	}
	
	public BadNameException (String message) {
		
		super(message);
	}
	
}
