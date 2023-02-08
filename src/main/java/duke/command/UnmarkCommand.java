package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

import java.io.IOException;

/**
 * Executable command to mark an existing task as uncompleted.
 *
 * @author Guo-KeCheng
 */
public class UnmarkCommand extends Command {
    private final String input;
    private final TaskList taskList;
    private final Ui ui;

    private final Storage storage;

    /**
     * UnmarkCommand constructor.
     *
     * @param input    String representation of task number
     * @param taskList Existing taskList
     * @param ui       Shared Ui object
     * @param storage  Shared storage object
     */
    public UnmarkCommand(String input, TaskList taskList, Ui ui, Storage storage) {
        this.input = input;
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Mark corresponding task as uncompleted.
     * Check for out of bounds as well as invalid syntax.
     *
     * @throws DukeException if input is incorrect
     */
    @Override
    public String execute() throws DukeException {
        int ind = Integer.parseInt(input) - 1;

        if (ind >= taskList.size() || ind < 0) {
            throw new DukeException("OOPS!!! Invalid task number");
        }

        taskList.get(ind).markUncompleted();

        try {
            storage.save(taskList);
        } catch (IOException e) {
            return ui.showError(e.getMessage());
        }

        return ui.printUnmarkedTask(taskList.get(ind));

    }
}
