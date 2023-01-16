package duke.command;

import duke.task.Deadline;
import duke.task.Task;

/**
 * Represents an add deadline task command.
 */
public class DeadlineCommand extends AddCommand {
    @Override
    protected Task createTask(String input) {
        input = input.replace("deadline ", "");
        String[] args = input.split(" /by ");

        return new Deadline(false, args[0], args[1]);
    }
}
