package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.util.Storage;
import duke.util.Ui;

/**
 * Executable command to exit the program.
 *
 * @author Guo-KeCheng
 */
public class ByeCommand extends Command {
    private TaskList taskList;
    private Storage storage;

    private Ui ui;

    /**
     * ByeCommand constructor
     *
     * @param taskList Existing taskList
     * @param ui Shared Ui object
     * @param storage Shared storage object
     */
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
