package duke.Commands;

import duke.Exceptions.DukeException;
import duke.Exceptions.TaskException;
import duke.Storage.Storage;
import duke.TaskList.TaskList;
import duke.Ui.Ui;

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
            if (taskNum > tasks.size() || taskNum < 1)
                throw new TaskException("Please enter a valid task number!");
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
