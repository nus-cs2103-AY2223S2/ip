package duke.command;

import duke.exceptions.DukeException;
import duke.task.TaskList;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class GetEventsOnCommand {
    public static String retrieveEvents(TaskList tasks, String input) throws DukeException {
        if (input.length() < 12) {
            throw new DukeException("Date input in the format of YYYY-MM-DD required!");
        }
        LocalDate date = LocalDate.parse(input.substring(11).trim());
        return "Here are the deadlines/events on "
                + date.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                + ":\n" + tasks.listAllOnDate(date);
    }
}
