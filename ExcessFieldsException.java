public class ExcessFieldsException extends Exception{

	public ExcessFieldsException () {
		
		super("This movie record has too many fields. There should be 10");
	}
	
	public ExcessFieldsException(String message) {
		
		super(message);
	}
	
}
