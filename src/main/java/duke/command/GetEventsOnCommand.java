package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidDateException;
import duke.task.TaskList;

/**
 * A command to list all events on a given date.
 */
public class GetEventsOnCommand {
    /**
     * Retrieves all events occurring on a given date from a list of tasks.
     * @param tasks the list of tasks.
     * @param input user's input containing the date to look out for.
     * @return a string of events.
     * @throws DukeException if the date were found to be invalid.
     */
    public static String retrieveEvents(TaskList tasks, String input) throws DukeException {
        if (input.length() < 12) {
            throw new DukeException("Date input in the format of YYYY-MM-DD required!");
        }
        LocalDate date = null;
        try {
            date = LocalDate.parse(input.substring(11).trim());
        } catch (DateTimeParseException e) {
            throw new InvalidDateException();
        }
        return "Here are the deadlines/events on "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ":\n" + tasks.listAllOnDate(date);
    }
}
