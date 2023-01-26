package duke;

public class DukeException extends Exception{
    private static final String CUSTOM_PREFIX = "Something's not right.\n";
    public DukeException(String message) {
        super(CUSTOM_PREFIX + message);
    }
}
