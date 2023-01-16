package duke.command;

import duke.task.Task;
import duke.task.ToDo;

/**
 * Represents an add To-Do task command.
 */
public class ToDoCommand extends AddCommand {
    @Override
    protected Task createTask(String input) {
        String description = input.replaceFirst("todo ", "");
        return new ToDo(false, description);
    }
}
