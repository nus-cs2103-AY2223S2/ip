package duke.exception;

public class DukeInvalidCommandException extends DukeException {

    @Override
    public String toString() {
        return String.format("%s I'm sorry, but I don't know what that means...", super.toString());
    }
}