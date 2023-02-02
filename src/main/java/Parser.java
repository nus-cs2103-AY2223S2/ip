public class Parser {

    public Parser() {
    }

    /**
     * @param input the input string from the user.
     * @return the ToDo object created from the input string.
     */
    public static ToDo parseTodo(String input)  {
        return new ToDo(input);
    }

    /**
     * @param input the input string from the user.
     * @return the Deadline object created from the input string.
     * @throws DukeException if the input string is invalid.
     */
    public static Deadline parseDeadline(String input) throws DukeException {
        String[] splitDescriptionAndBy = input.split(" /by ", 2);
        if (splitDescriptionAndBy.length == 1) {
            throw new DukeException("The deadline of a deadline task cannot be empty.");
        }
        return new Deadline(splitDescriptionAndBy[0], splitDescriptionAndBy[1]);
    }

    /**
     * @param input the input string from the user.
     * @return the Event object created from the input string.
     * @throws DukeException if the input string is invalid.
     */
    public static Event parseEvent(String input) throws DukeException {

        String[] splitDescriptionAndDuration = input.split(" /from ", 2);
        if (splitDescriptionAndDuration.length == 1) {
            throw new DukeException("The start time of an event cannot be empty.");
        }
        String[] splitFromAndTo = splitDescriptionAndDuration[1].split(" /to ", 2);
        if (splitFromAndTo.length == 1) {
            throw new DukeException("The end time of an event cannot be empty.");
        }
        return new Event(splitDescriptionAndDuration[0], splitFromAndTo[0], splitFromAndTo[1]);
    }

    public static DukeCommand parseInput(String input) {
        return DukeCommand.getCommand(input);
    }
}
