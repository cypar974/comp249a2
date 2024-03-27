class BadDurationException extends Exception {

    public BadDurationException() {

        super("Invalid Duration. Duration must be from 30 minutes to 300 minutes long");

    }

    public BadDurationException(String message) {

        super(message);
    }

}

class BadGenreException extends Exception {

    public BadGenreException() {

        super("Invalid Genre. Genre does not exist in catalog");

    }

    public BadGenreException(String message) {

        super(message);

    }

}

class BadNameException extends Exception {

    public BadNameException() {

        super("Invalid Actor Name.");
    }

    public BadNameException(String message) {

        super(message);
    }

}

class BadTitleException extends Exception {

    public BadTitleException() {

        super("Invalid Title.");

    }

    public BadTitleException(String message) {

        super(message);
    }
}

class BadScoreException extends IllegalArgumentException {

    public BadScoreException() {

        super("Invalid Score. Score must be less than or equal to 10");
    }

    public BadScoreException(String message) {

        super(message);

    }
}

class BadRatingException extends Exception {

    public BadRatingException() {

        super("Invalid Rating. Ratings must be either PG, Unrated, G,R,PG-13 or NC-17");
    }

    public BadRatingException(String message) {

        super(message);

    }

}

class BadYearException extends Exception {

    public BadYearException() {

        super("Invalid Year. Year is not in bound (1990-1999)");

    }

    public BadYearException(String message) {

        super(message);

    }
}

class ExcessFieldsException extends Exception {

    public ExcessFieldsException() {

        super("This movie record has too many fields. There should be 10");
    }

    public ExcessFieldsException(String message) {

        super(message);
    }

}

class MissingFieldsException extends Exception {

    public MissingFieldsException() {

        super("There are too little fields in this record. There should be exactly 10");
    }

    public MissingFieldsException(String message) {

        super(message);
    }

}

class MissingQuotesException extends Exception {

    public MissingQuotesException() {

        super("The field is missing quotation marks.");

    }

    public MissingQuotesException(String message) {

        super(message);
    }

}
