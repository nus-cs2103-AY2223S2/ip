package exceptions;

public class InvalidTaskAccessException extends DukeException {
    public InvalidTaskAccessException() {
        super(" Trying to access an invalid task!");
    }
}
