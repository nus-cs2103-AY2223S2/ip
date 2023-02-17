package duke.command;

import duke.exceptions.DukeException;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A command to create activities with deadlines.
 */
public class DeadlineCommand {
    /**
     * Creates a deadline object that is added to list of existing tasks.
     * @param tasks a list of tasks.
     * @param input user's description of the activity.
     * @return acknowledgement of the deadline object being added.
     * @throws DukeException if an invalid date or description were found.
     */
    public static String addDeadline(TaskList tasks, String input) throws DukeException {
        if (input.length() < 10) {
            throw new DukeException("The description of a <deadline> cannot be empty!");
        }
        String info = input.substring(9);
        if (!info.matches(".+ /by .+")) {
            throw new DukeException("<deadline> is to be used as such: $ deadline <des> /by <YYYY-MM-DD>");
        }
        String[] sp = info.split(" /by ");
        if (sp[1].trim().equals("")) {
            throw new DukeException("<deadline> is missing <YYYY-MM-DD>");
        }
        Task task = new Deadline(sp[0].trim(), sp[1].trim());
        tasks.addTask(task);
        return "Got it. I've added this task:\n\t"
                + task.getStatusIcon()
                + "\n" + "Now you have "
                + tasks.getSize()
                + " task(s) in the list.";
    }
}
