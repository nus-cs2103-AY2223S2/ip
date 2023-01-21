package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Exceptions.TaskException;
import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.Ui.Ui;

public class DeleteCommand implements Command {

    private String fullCommand;

    public DeleteCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int taskNum = Integer.parseInt(fullCommand.substring(7));
            if (taskNum > tasks.size() || taskNum < 1)
                throw new TaskException("Please enter a valid task number!");
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
