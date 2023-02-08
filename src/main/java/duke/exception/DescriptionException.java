package duke.exception;

public class DescriptionException extends CommandException {

    private static final String STRING = "☹ OOPS!!! The description of a task cannot be empty.";
    public DescriptionException() {
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
