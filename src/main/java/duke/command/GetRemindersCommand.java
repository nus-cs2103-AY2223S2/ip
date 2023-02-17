package duke.command;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import duke.exceptions.DukeException;
import duke.task.TaskList;

/**
 * A command to list all events on a given date.
 */
public class GetRemindersCommand {
    private static final int defaultDays = 3;
    /**
     * Retrieves incomplete tasks that are over or within specified (default 3) days from the deadline.
     * @param tasks the list of tasks.
     * @param input user's input containing the date to look out for.
     * @return a string of incomplete tasks.
     * @throws DukeException if an invalid number of days were provided.
     */
    public static String getReminders(TaskList tasks, String input) throws DukeException {
        int days = defaultDays;
        LocalDate currDate = LocalDate.now();
        if (input.length() < 13) {
            LocalDate defaultDeadline = currDate.plusDays(days);
            return "Here are the incomplete deadlines/events before "
                    + defaultDeadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                    +":\n" + tasks.getTasksBy(defaultDeadline);
        }

        if (!input.matches("getreminders\\s+\\d+")) {
            throw new DukeException(
                    "<getreminders> command takes in number of days from the current date."
                    + "If no argument is provided, default is 3 days from current date.");
        }
        days = Integer.parseInt(input.substring(13).trim());
        LocalDate deadline = currDate.plusDays(days);
        return "Here are the incomplete deadlines/events before "
                + deadline.format(DateTimeFormatter.ofPattern("MMM d yyyy"))
                +":\n" + tasks.getTasksBy(deadline);
    }
}
