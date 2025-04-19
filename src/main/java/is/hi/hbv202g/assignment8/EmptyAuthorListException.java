package is.hi.hbv202g.assignment8;

/**
 * Exception thrown when attempting to create or modify a book
 * with an empty or null list of authors.
 */
public class EmptyAuthorListException extends Exception {

    /**
     * Constructs a new EmptyAuthorListException with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public EmptyAuthorListException(String message) {
        super(message);
    }
}
