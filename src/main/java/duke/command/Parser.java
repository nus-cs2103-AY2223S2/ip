package duke.command;

import duke.exception.DukeException;
import duke.exception.DukeInvalidCommandException;

/**
 * A class that deals with making sense of user input.
 * It creates the appropriate Command object accordingly.
 */
public class Parser {

    public static Command parse(String line) throws DukeException {
        assert line != null;
        String[] chunked = line.split(" ");
        assert chunked.length > 0;
        String command = chunked[0];
        if (command.equals("bye")) {
            return new ByeCommand();
        } else if (command.equals("mark")) {
            if (chunked.length == 1) {
                throw new DukeInvalidCommandException();
            } else {
                return new MarkCommand(Integer.parseInt(chunked[1]));
            }
        } else if (command.equals("unmark")) {
            if (chunked.length == 1) {
                throw new DukeInvalidCommandException();
            } else {
                return new UnmarkCommand(Integer.parseInt(chunked[1]));
            }
        } else if (command.equals("delete")) {
            if (chunked.length == 1) {
                throw new DukeInvalidCommandException();
            } else {
                return new DeleteCommand(Integer.parseInt(chunked[1]));
            }
        } else if (command.equals("list")) {
            return new ListCommand();
        } else if (command.equals("find")) {
            if (chunked.length != 2) {
                throw new DukeInvalidCommandException();
            } else {
                return new FindCommand(chunked[1]);
            }
        } else if (command.equals("todo")) {
            String rest = line.substring(4).trim();
            if (rest.isBlank()) {
                throw new DukeInvalidCommandException();
            } else {
                return new AddToDoCommand(rest);
            }
        } else if (command.equals("deadline")) {
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

        } else if (command.equals("event")) {
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

        } else {
            throw new DukeInvalidCommandException("Huh? Sorry I don't know what this means :(");
        }
    }
}
