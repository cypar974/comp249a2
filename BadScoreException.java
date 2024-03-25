
public class BadScoreException extends IllegalArgumentException {

	public BadScoreException() {

		super("Invalid Score. Score must be less than or equal to 10");
	}

	public BadScoreException(String message) {

		super(message);

	}
}
