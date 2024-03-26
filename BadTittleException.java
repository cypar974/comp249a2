public class BadTittleException extends Exception {

    public BadTittleException() {

        super("Invalid Tittle.");

    }

    public BadTittleException(String message) {

        super(message);
    }
}
