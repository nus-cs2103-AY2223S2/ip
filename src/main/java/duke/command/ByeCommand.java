package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;
import javafx.application.Platform;


/**
 * Executable command to exit the program.
 *
 * @author Guo-KeCheng
 */
public class ByeCommand extends Command {
    private final TaskList taskList;
    private final Storage storage;

    private final Ui ui;

    /**
     * ByeCommand constructor
     *
     * @param taskList Existing taskList
     * @param ui       Shared Ui object
     * @param storage  Shared storage object
     */
    public ByeCommand(TaskList taskList, Ui ui, Storage storage) {
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    @Override
    public String execute() throws DukeException {
        try {
            storage.save(taskList);
        } catch (IOException e) {
            return ui.showError(e.getMessage());
        }
        
        return ui.printBye();

    }
}
