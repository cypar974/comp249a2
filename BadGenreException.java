public class BadGenreException extends Exception{

	public BadGenreException () {
		
		super("Invalid Genre. Genre does not exist in catalog");
		
	}
	
	public BadGenreException(String message) {
		
		super (message);
		
	}
	
}
