package is.hi.hbv202g.assignment8;

/**
 * Thrown to indicate that a book series does not contain enough books
 * to be considered a valid series.
 */
public class BookSeriesNotASeriesException extends RuntimeException {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message
     */
    public BookSeriesNotASeriesException(String message) {
        super(message);
    }
}
