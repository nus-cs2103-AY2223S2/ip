import DukeExceptions.DukeInvalidInputException;
import DukeExceptions.DukeEmptyInputException;

public class Deadline extends Task {

    protected String by;

    private Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public static Deadline createDeadline(String input) throws DukeInvalidInputException, DukeEmptyInputException {
        String sanitisedInput = input.trim();
        if (input.isEmpty() || sanitisedInput.isEmpty()) {
            throw new DukeInvalidInputException("Deadlines need a description and a deadline.");
        }
        if (!input.contains("/by")) {
            throw new DukeInvalidInputException("You need to specify the deadline using /by. Why don't you try again?");
        }
        String[] arguments = input.split("/by", 2);
        String arg1 = arguments[0].trim();
        String arg2 = arguments[1].trim();
        if (arg1.isEmpty() || arg2.isEmpty()) {
            throw new DukeEmptyInputException();
        }
        return new Deadline(arg1, arg2);
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
