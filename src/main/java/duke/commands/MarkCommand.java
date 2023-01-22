package duke.commands;

import duke.exceptions.DukeException;
import duke.exceptions.TaskException;
import duke.storage.Storage;
import duke.tasklist.TaskList;
import duke.ui.Ui;

public class MarkCommand implements Command {

    private boolean toMark;
    private String fullCommand;

    public MarkCommand(boolean toMark, String fullCommand) {
        this.toMark = toMark;
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            int taskNum = Integer.parseInt(this.fullCommand.substring(toMark ? 5 : 7));
            if (taskNum > tasks.size() || taskNum < 1) {
                throw new TaskException("Please enter a valid task number!");
            }
            tasks.markTask(taskNum, storage, ui, toMark);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new TaskException("Please enter a valid task number!");
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
