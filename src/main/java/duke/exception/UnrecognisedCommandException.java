package duke.exception;

public class UnrecognisedCommandException extends DukeException{

    public UnrecognisedCommandException() {
        this.message ="Command not recognised. Please try again.";
    }
}
