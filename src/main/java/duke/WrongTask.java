package duke;

/**
 * Represents an Exception due to user inputting a wrong task keyword.
 */
public class WrongTask extends Exception {
    public WrongTask(String message) {
        super(message);
    }

    @Override
    public String toString() {
        return "OOPS!!!" + getMessage();
    }
}

