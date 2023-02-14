package duke.command;

import duke.task.Task;
import duke.util.DukeException;
import duke.util.Storage;
import duke.util.TaskList;
import duke.util.Ui;

/**
 * Represents a DeleteCommand that implements the interface Command.
 */
public class DeleteCommand implements Command {
    private int taskNumber;

    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public String execute(Storage storage, TaskList tasks, Ui ui) throws DukeException {
        try {
            Task taskToDelete = tasks.getTask(taskNumber - 1);
            assert taskToDelete != null;
            tasks.deleteTask(taskNumber - 1);
            String toDisplay = String.format("Alright, I have removed this task:\n%s\n"
                    + "Now you have %d tasks in the list.", taskToDelete.toString(), tasks.getSize());
            ui.displayMessage(toDisplay);
            return toDisplay;
        } catch (Exception e) {
            throw new DukeException("Enter a valid task number!");
        }
    }
}
