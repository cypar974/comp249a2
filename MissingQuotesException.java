public class MissingQuotesException extends Exception{

	public MissingQuotesException() {
		
		super("The field is missing quotation marks.");
		
	}
	
	public MissingQuotesException(String message) {
		
		super(message);
	}
	
	
}
