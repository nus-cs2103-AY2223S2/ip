package duke.tasks;

import duke.DateHandler;
import duke.exceptions.DukeInvalidInputException;
import duke.exceptions.DukeEmptyInputException;

/**
 * Represents a type of task called a deadline that has a due date.
 * @author lukkesreysandeur
 */
public class Deadline extends Task {
    /** The time to do the task by. */
    protected String by;

    /**
     * Initialises the deadline object.
     *
     * @param description The name of the deadline.
     * @param by The date/time to finish it by.
     */
    private Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    /**
     * Creates a new deadline object, throws error if input is invalid.
     *
     * @param input A string that contains the parameters entered after the user command.
     * @return The created deadline object.
     * @throws DukeInvalidInputException
     * @throws DukeEmptyInputException
     */
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

    /**
     * Returns the string representation of the deadline.
     *
     * @return A string representing the deadline.
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }

    /**
     * Converts the deadline into a text format that can be saved to a text file.
     *
     * @return A string representing the deadline in a different format.
     */
    @Override
    public String toText() {
        return String.format("D %d %s /by %s\n", super.isDone ? 1 : 0, super.name, by);
    }
}
