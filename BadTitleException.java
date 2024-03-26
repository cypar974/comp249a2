public class BadTitleException extends Exception {

    public BadTitleException() {

        super("Invalid Title.");

    }

    public BadTitleException(String message) {

        super(message);
    }
}
