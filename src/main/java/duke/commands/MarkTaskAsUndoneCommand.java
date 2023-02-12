package duke.commands;

import duke.exception.InvalidArgumentDukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.uitext.UiText;

/**
 * Represents a command to mark a task as not done.
 */
public class MarkTaskAsUndoneCommand extends Command {
    private int number;

    public MarkTaskAsUndoneCommand(int number) {
        this.number = number;
    }

    @Override
    public String execute(TaskList taskList, UiText uiText, Storage storage) throws InvalidArgumentDukeException {
        try {
            taskList.markTaskAsNotDone(number);
            return "Ok. I have marked this task as not done:\n"
                    + taskList.getTaskString(number);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentDukeException();
        }
    }
}
