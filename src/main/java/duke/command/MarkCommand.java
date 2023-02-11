package duke.command;

import duke.exceptions.DukeException;
import duke.exceptions.InvalidIndexException;
import duke.task.TaskList;

public class MarkCommand {
    public static String executeMark(TaskList tasks, String input) throws DukeException {
        if (input.length() < 6 || !input.matches("mark\\s+\\d+")) {
            throw new DukeException("<mark> command should receive a task number!");
        }
        int taskNum = Integer.parseInt(input.substring(5).trim());
        if (1 <= taskNum && taskNum <= tasks.getSize()) {
            String marked = tasks.toggleMark(taskNum);
             return "Nice! I've marked this task as done:\n\t" + marked;
        } else {
            throw new InvalidIndexException(taskNum);
        }
    }
}
