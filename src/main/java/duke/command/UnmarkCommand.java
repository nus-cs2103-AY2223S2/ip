package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidIndexException;
import duke.task.TaskList;

public class UnmarkCommand {
    public static String executeUnmark(TaskList tasks, String input) throws DukeException {
        if (input.length() < 8 || !input.matches("unmark\\s+\\d+")) {
            throw new DukeException("<unmark> command should receive a task number!");
        }
        int taskNum = Integer.parseInt(input.substring(7).trim());
        if (1 <= taskNum && taskNum <= tasks.getSize()) {
            String unmarked = tasks.toggleUnmark(taskNum);
            return "OK, I've marked this task as not done yet:\n\t" + unmarked;
        } else {
            throw new InvalidIndexException(taskNum);
        }
    }
}
