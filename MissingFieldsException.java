public class MissingFieldsException extends Exception{

	
	public MissingFieldsException () {
		
		super ("There are too many fields in this record. There should be exactly 10");
	}
	
	public MissingFieldsException (String message) {
		
		super(message);
	}
	
	
}
