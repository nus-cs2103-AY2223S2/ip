package duke.tasks;

import duke.DateHandler;
import duke.exceptions.DukeInvalidInputException;
import duke.exceptions.DukeEmptyInputException;

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
        System.out.println(arg2.length());
        arg2 = DateHandler.parse(arg2);
        return new Deadline(arg1, arg2);
    }
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }

    @Override
    public String toText() {
        return String.format("D %d %s /by %s\n", super.isDone ? 1 : 0, super.name, by);
    }
}
