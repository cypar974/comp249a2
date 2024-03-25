
public class BadScoreException extends Exception{

	public BadScoreException() {
		
		super ("Invalid Score. Score must be less than or equal to 10");
	}
	
	public BadScoreException( String message) {
		
		super (message);	
		
	}
}
