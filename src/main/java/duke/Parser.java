package duke;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * Encapsulates a parser which parses user commands and makes sense of it.
 *
 * @author Sean Chin Jun Kai
 */

public class Parser {
    /**
     * Parses the userInput into a Command object.
     *
     * @param ui The user interface.
     * @param tasks The current list of tasks.
     * @param storage Object responsible for storing the tasks into txt file.
     * @param userInput String that user inputs.
     * @return The corresponding command object based on user input.
     * @throws DukeException if user enters invalid commands.
     */
    public static Command parseUserResponse(Ui ui, TaskList tasks, Storage storage, String userInput) throws DukeException {
        String[] tokens = userInput.split(" ", 2);
        String command = tokens[0];

        switch (command) {
        case "bye":
            return new ByeCommand(ui, tasks, storage, tokens);
        case "list":
            return new ListCommand(ui, tasks, tokens);
        case "find":
            return new FindCommand(tasks, tokens);
        case "unmark":
            return new UnmarkCommand(ui, tasks, tokens);
        case "mark":
            return new MarkCommand(ui, tasks, tokens);
        case "delete":
            return new DeleteCommand(ui, tasks, tokens);
        case "todo":
            return new TodoCommand(ui, tasks, tokens);
        case "deadline":
            return new DeadlineCommand(ui, tasks, tokens);
        case "event":
            return new EventCommand(ui, tasks, tokens);
        default:
            throw new DukeException("Invalid command entered!");
        }
    }

    /**
     * Parses the given arguments and returns a Todo object.
     *
     * @param args The arguments after going through the parser.
     * @return Todo object.
     * @throws DukeException if description of Todo is not present.
     */
    public static Todo parseTodo(String[] args) throws DukeException {
        if (args.length < 2) {
            throw new DukeException("The description of a todo cannot be empty!");
        }
        assert args.length == 2;
        String desc = args[1];
        return new Todo(desc);
    }

    /**
     * Parses the given arguments and returns a Deadline object.
     *
     * @param args The arguments after going through the parser.
     * @return Deadline object.
     * @throws DukeException if end date/time not present or wrong format of end date/time.
     */
    public static Deadline parseDeadline(String[] args) throws DukeException {
        String[] separated = args[1].split("/by ");

        if (separated.length < 2) {
            throw new DukeException("Deadline needs to include a specific end date!");
        }
        try {
            assert separated.length == 2;
            LocalDate date = LocalDate.parse(separated[1]);
            return new Deadline(separated[0], date);
        } catch (DateTimeParseException e) {
            throw new DukeException("Give deadline in YYYY-MM-DD format!");
        }
    }

    /**
     * Parses the given arguments and returns an Event object.
     *
     * @param args The arguments after going through the parser.
     * @return Event object.
     * @throws DukeException if start and end date/time not present.
     */
    public static Event parseEvent(String[] args) throws DukeException {
        String[] separated = args[1].split("/from |/to ");

        if (separated.length < 3) {
            throw new DukeException("Event needs to include a start date/time and a end date/time!");
        }
        assert separated.length == 3;
        return new Event(separated[0], separated[1], separated[2]);
    }

    /**
     * Parses the given arguments to get referenced Task ID.
     *
     * @param args The arguments after going through the parser.
     * @return Task ID.
     * @throws DukeException if task ID is of the wrong type.
     */
    public static int parseTask(String[] args) throws DukeException {
        try {
            return Integer.parseInt(args[1]);
        } catch (NumberFormatException e) {
            throw new DukeException("Given task number is invalid!");
        }
    }
}
