import DukeExceptions.DukeEmptyInputException;
import DukeExceptions.DukeInvalidInputException;

public class Event extends Task {
    protected String from;
    protected String to;

    private Event(String event, String from, String to) {
        super(event);
        this.from = from;
        this.to = to;
    }

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
        String arg3 = remainder[1].trim();
        if (arg1.isEmpty() || arg2.isEmpty() || arg3.isEmpty()) {
            throw new DukeEmptyInputException();
        }
        return new Event(arg1, arg2, arg3);
    }

    @Override
    public String toString() {
        return String.format("[E]%s (from: %s to: %s)", super.toString(), from, to);
    }
}
