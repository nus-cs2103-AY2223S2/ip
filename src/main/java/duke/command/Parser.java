package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeInvalidCommandException;

/**
 * A class that deals with making sense of user input.
 * It creates the appropriate Command object accordingly.
 */
public class Parser {

    /**
     * Parses the command.
     * @param line User input.
     * @return An appropriate command.
     * @throws DukeException If user input is invalid.
     */
    public static Command parse(String line) throws DukeException {
        assert line != null;
        String[] chunked = line.split(" ");
        assert chunked.length > 0;
        String command = chunked[0];
        if (command.equals("bye")) {
            return handleBye();
        } else if (command.equals("mark")) {
            return handleMark(chunked);
        } else if (command.equals("unmark")) {
            return handleUnmark(chunked);
        } else if (command.equals("delete")) {
            return handleDelete(chunked);
        } else if (command.equals("list")) {
            return handleList();
        } else if (command.equals("find")) {
            return handleFind(chunked);
        } else if (command.equals("todo")) {
            return handleToDo(line);
        } else if (command.equals("deadline")) {
            return handleDeadline(line);
        } else if (command.equals("event")) {
            return handleEvent(line);
        } else if (command.equals("edit")) {
            return handleEdit(chunked);
        } else {
            throw new DukeInvalidCommandException("Huh? Sorry I don't know what this means :(");
        }
    }

    static Command handleBye() {
        return new ByeCommand();
    }
    static Command handleMark(String[] chunked) throws DukeException {
        if (chunked.length == 1) {
            throw new DukeInvalidCommandException();
        } else {
            return new MarkCommand(Integer.parseInt(chunked[1]));
        }
    }

    static Command handleUnmark(String[] chunked) throws DukeException {
        if (chunked.length == 1) {
            throw new DukeInvalidCommandException();
        } else {
            return new UnmarkCommand(Integer.parseInt(chunked[1]));
        }
    }

    static Command handleDelete(String[] chunked) throws DukeException {
        if (chunked.length == 1) {
            throw new DukeInvalidCommandException();
        } else {
            return new DeleteCommand(Integer.parseInt(chunked[1]));
        }
    }

    static Command handleList() {
        return new ListCommand();
    }

    static Command handleFind(String[] chunked) throws DukeException {
        if (chunked.length != 2) {
            throw new DukeInvalidCommandException();
        } else {
            return new FindCommand(chunked[1]);
        }
    }

    static Command handleToDo(String line) throws DukeException {
        String rest = line.substring(4).trim();
        if (rest.isBlank()) {
            throw new DukeInvalidCommandException();
        } else {
            return new AddToDoCommand(rest);
        }
    }

    static Command handleDeadline(String line) throws DukeException {
        String rest = line.substring(8).trim();
        String pattern = ".+ /by [0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9]";

        if (!rest.matches(pattern)) {
            throw new DukeInvalidCommandException();
        }

        String desc = rest.substring(0, rest.indexOf(" /by"));
        String[] by = rest.substring(rest.indexOf("/by")).split(" ");
        String date = by[1];
        String time = by[2];

        return new AddDeadlineCommand(desc, date, time);
    }

    static Command handleEvent(String line) throws DukeException {
        String rest = line.substring(5).trim();
        String pattern = ".+ /from [0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9]"
                + " /to [0-9][0-9]/[0-9][0-9]/[0-9][0-9][0-9][0-9] [0-9][0-9][0-9][0-9]";

        if (!rest.matches(pattern)) {
            throw new DukeInvalidCommandException();
        }

        String desc = rest.substring(0, rest.indexOf(" /from"));
        String[] fromTo = rest.substring(rest.indexOf("/from")).split(" ");
        String dateFrom = fromTo[1];
        String timeFrom = fromTo[2];
        String dateTo = fromTo[4];
        String timeTo = fromTo[5];

        return new AddEventCommand(desc, dateFrom, timeFrom, dateTo, timeTo);
    }

    static Command handleEdit(String[] chunked) throws DukeException {
        if (chunked.length < 3) {
            throw new DukeInvalidCommandException();
        } else {
            String newDesc = "";
            for (int i = 2; i < chunked.length; i++) {
                newDesc += chunked[i];
                if (i != chunked.length - 1) {
                    newDesc += " ";
                }
            }
            return new EditCommand(Integer.parseInt(chunked[1]), newDesc);
        }
    }
}
