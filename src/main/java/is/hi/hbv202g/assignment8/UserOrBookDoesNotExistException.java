package is.hi.hbv202g.assignment8;

public class UserOrBookDoesNotExistException extends RuntimeException {
    public UserOrBookDoesNotExistException(String message) {
        super(message);
    }
}
