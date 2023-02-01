package duke.commands;

import duke.exception.InvalidArgumentDukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.uitext.UiText;

/**
 * Represents a command to mark a task as done.
 */
public class MarkTaskAsDoneCommand extends Command {
    private int number;

    public MarkTaskAsDoneCommand(int number) {
        this.number = number;
    }

    @Override
    public String execute(TaskList taskList, UiText uiText, Storage storage) throws InvalidArgumentDukeException {
        try {
            taskList.markTaskAsDone(number);
            return "Good job. You have finished this task:\n"
                    + taskList.getTaskString(number);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidArgumentDukeException();
        }
    }
}
