public class EmptyInputException extends DukeException {
    public EmptyInputException() {
        super("☹ OOPS!!! The description of a todo cannot be empty.");
    }
}
