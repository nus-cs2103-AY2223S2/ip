public class DukeInvalidDueOnCommandException extends DukeException {

    DukeInvalidDueOnCommandException() {
        super("Usage: dueon <date>\nExample: dueon 2012-12-31");
    }
}
