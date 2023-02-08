package duke.exception;

public class CommandException extends DukeException {
    private static final String STRING = "☹ OOPS!!! I'm sorry, but I don't know what that means :-(";
    public CommandException() {
    }

    @Override
    public String toString() {
        return STRING;
    }

    @Override
    public void printStackTrace() {
        System.out.println(STRING);
    }
}
