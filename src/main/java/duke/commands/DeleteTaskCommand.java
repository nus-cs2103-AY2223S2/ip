package duke.commands;

import duke.exception.InvalidArgumentDukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

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
    public void execute(TaskList taskList, Ui ui, Storage storage) throws InvalidArgumentDukeException {
        try {
            String taskString = taskList.getTaskString(number);
            taskList.remove(number);
            ui.printMessage("Ok. I have deleted this task:\n"
                    + taskString
            );
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new InvalidArgumentDukeException();
        }
    }
}
