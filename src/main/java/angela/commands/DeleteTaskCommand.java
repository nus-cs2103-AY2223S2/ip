package angela.commands;

import angela.exception.InvalidArgumentAngelaException;
import angela.storage.Storage;
import angela.task.TaskList;
import angela.uitext.UiText;

/**
 * Represents a command to delete a task.
 */
public class DeleteTaskCommand extends Command {
    private int number;

    /**
     * Creates a command to delete a task.
     * @param number Task number.
     */
    public DeleteTaskCommand(int number) {
        this.number = number;
    }

    @Override
    public String execute(TaskList taskList, UiText uiText, Storage storage) throws InvalidArgumentAngelaException {
        try {
            String taskString = taskList.getTaskString(number);
            taskList.remove(number);
            return "Ok. I have deleted this task:\n"
                    + taskString;
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentAngelaException();
        }
    }
}
