package is.hi.hbv202g.assignment8;

/**
 * Exception thrown when a user or a book does not exist in the library system.
 */
public class UserOrBookDoesNotExistException extends Exception {

    /**
     * Constructs a new exception with the specified detail message.
     *
     * @param message the detail message explaining the reason for the exception
     */
    public UserOrBookDoesNotExistException(String message) {
        super(message);
    }
}
