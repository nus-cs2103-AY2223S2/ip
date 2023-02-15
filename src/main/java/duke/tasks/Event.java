package duke.tasks;

import duke.DateHandler;
import duke.exceptions.DukeEmptyInputException;
import duke.exceptions.DukeInvalidInputException;

/**
 * Represents a type of task called an event that has a starting and ending time.
 * @author lukkesreysandeur
 */
public class Event extends Task {
    /** String representing the starting time. */
    protected String from;
    /** String representing the ending time. */
    protected String to;

    /**
     * Initialises an event object.
     *
     * @param event The name of the event.
     * @param from The starting time.
     * @param to The ending time.
     */
    private Event(String event, String from, String to) {
        super(event);
        this.from = from;
        this.to = to;
    }

    /**
     * Creates a new event object, throws error if input is invalid.
     *
     * @param input A string that contains the parameters entered after the user command.
     * @return The created event object.
     * @throws DukeInvalidInputException
     * @throws DukeEmptyInputException
     */
    public static Event createEvent(String input) throws DukeInvalidInputException, DukeEmptyInputException {
        String sanitisedInput = input.trim();
        if (input.isEmpty() || sanitisedInput.isEmpty()) {
            throw new DukeInvalidInputException("Events need a description, a from date and a to date.");
        }
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new DukeInvalidInputException("You need to specify the event duration using /from and /to.");
        }
        String[] arguments = input.split("/from", 2);
        String arg1 = arguments[0].trim();
        if (!arguments[1].contains("/to")) {
            throw new DukeInvalidInputException("It would appear that your message is out of order. " +
                    "Try putting /from before /to.");
        }
        String[] remainder = arguments[1].split("/to", 2);
        String arg2 = remainder[0].trim();
        arg2 = DateHandler.parse(arg2);
        String arg3 = remainder[1].trim();
        arg3 = DateHandler.parse(arg3);
        if (arg1.isEmpty() || arg2.isEmpty() || arg3.isEmpty()) {
            throw new DukeEmptyInputException();
        }
        return new Event(arg1, arg2, arg3);
    }

    /**
     * Returns the string representation of the event.
     *
     * @return A string representing the event.
     */
    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }

    /**
     * Converts the event into a text format that can be saved to a text file.
     *
     * @return A string representing the event in a different format.
     */
    @Override
    public String toText() {
        return String.format("E %d %s /from %s /to %s\n", super.isDone ? 1 : 0, super.name, from, to);
    }
}
