public class EmptyArgumentDukeException extends DukeException {

    public static final String ERROR_MESSAGE = "One or more arguments are empty!";

    public EmptyArgumentDukeException() {
        super(ERROR_MESSAGE);
    }
}
