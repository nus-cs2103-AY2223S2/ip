package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

import java.io.IOException;


public class ByeCommand extends Command {
    private TaskList taskList;
    private Storage storage;

    private Ui ui;
    public ByeCommand(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    @Override
    public boolean execute() throws DukeException {
        try {
            storage.save(taskList);
        } catch (IOException e) {
            ui.showError(e.getMessage());
        }

        ui.printBye();
        return true;
    }
}
