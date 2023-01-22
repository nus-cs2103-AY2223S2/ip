package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.TaskException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class DeleteCommand implements Command {

    private String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int taskNum = Integer.parseInt(fullCommand.substring(7));
            if (taskNum > tasks.size() || taskNum < 1) {
                throw new TaskException("Please enter a valid task number!");
            }
            tasks.deleteTask(taskNum, storage, ui);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new TaskException("Please enter a valid task number!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
