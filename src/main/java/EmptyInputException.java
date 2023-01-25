public class EmptyInputException extends DukeException {
    public EmptyInputException(String s) {
        super("☹ OOPS!!! The description of " + s + " cannot be empty.");
    }
}
