public class BadRatingException extends Exception {

	public BadRatingException () {
		
		super("Invalid Rating. Ratings must be either PG, Unrated, G,R,PG-13 or NC-17");
	}
	
	public BadRatingException (String message) {
		
		super (message);
		
		
	}
	
}
