import java.lang.Exception;
public class DukeStoreInvalidAccessException extends Exception {
    public DukeStoreInvalidAccessException() {
        super("Invalid index entered. Please try again");
    }
}
