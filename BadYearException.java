public class BadYearException extends Exception {

    public BadYearException() {

        super("Invalid Year. Year is not in bound (1990-1999)");

    }

    public BadYearException(String message) {

        super(message);

    }
}
