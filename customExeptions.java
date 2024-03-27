class BadDurationException extends Exception {

    public BadDurationException() {

        super("Semantic error: Invalid Duration. Duration must be from 30 minutes to 300 minutes long");

    }

    public BadDurationException(String message) {

        super("Semantic error: " + message);
    }

}

class BadGenreException extends Exception {

    public BadGenreException() {

        super("Semantic error: Invalid Genre. Genre does not exist in catalog");

    }

    public BadGenreException(String message) {

        super("Semantic error: " + message);

    }

}

class BadNameException extends Exception {

    public BadNameException() {

        super("Semantic error: Invalid Name.");
    }

    public BadNameException(String message) {

        super("Semantic error: " + message);
    }

}

class BadTitleException extends Exception {

    public BadTitleException() {

        super("Semantic error: Invalid Title.");

    }

    public BadTitleException(String message) {

        super("Semantic error: " + message);
    }
}

class BadScoreException extends IllegalArgumentException {

    public BadScoreException() {

        super("Semantic error: Invalid Score. Score must be less than or equal to 10");
    }

    public BadScoreException(String message) {

        super("Semantic error: " + message);

    }
}

class BadRatingException extends Exception {

    public BadRatingException() {

        super("Semantic error: Invalid Rating. Ratings must be either PG, Unrated, G,R,PG-13 or NC-17");
    }

    public BadRatingException(String message) {

        super("Semantic error: " + message);

    }

}

class BadYearException extends Exception {

    public BadYearException() {

        super("Semantic error: Invalid Year. Year is not in bound (1990-1999)");

    }

    public BadYearException(String message) {

        super("Semantic error: " + message);

    }
}

class ExcessFieldsException extends Exception {

    public ExcessFieldsException() {

        super("Syntax error: This movie record has too many fields. There should be 10");
    }

    public ExcessFieldsException(String message) {

        super("Syntax error: " + message);
    }

}

class MissingFieldsException extends Exception {

    public MissingFieldsException() {

        super("Syntax error: There are too little fields in this record. There should be exactly 10.");
    }

    public MissingFieldsException(String message) {

        super("Syntax error: " + message);
    }

}

class MissingQuotesException extends Exception {

    public MissingQuotesException() {

        super("Syntax error: The field is missing quotation marks.");

    }

    public MissingQuotesException(String message) {

        super("Syntax error: " + message);
    }

}
