public class BadDurationException extends Exception{

	public BadDurationException () {
		
		super("Invalid Duration. Duration must be from 30 minutes to 300 minutes long");
		
	}
	
	public BadDurationException (String message) {
		
		super(message);
	}
	
}
