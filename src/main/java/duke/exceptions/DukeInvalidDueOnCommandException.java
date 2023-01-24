package duke.exceptions;

public class DukeInvalidDueOnCommandException extends DukeException {

    public DukeInvalidDueOnCommandException() {
        super("Usage: dueon <date>\nExample: dueon 2012-12-31");
    }
}
