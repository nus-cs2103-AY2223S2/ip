package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * Executable command to delete specific task.
 *
 * @author Guo-KeCheng
 */
public class DeleteCommand extends Command {
    private final String input;
    private final TaskList taskList;
    private final Ui ui;

    private final Storage storage;

    /**
     * DeleteCommand constructor.
     *
     * @param input    String representation of task number
     * @param taskList Existing taskList
     * @param ui       Shared Ui object
     * @param storage  Shared storage object
     */
    public DeleteCommand(String input, TaskList taskList, Ui ui, Storage storage) {
        this.input = input;
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Delete task by removing the Task at the corresponding index.
     *
     * @throws DukeException if input is incorrect
     */
    @Override
    public String execute() throws DukeException {
        int ind = Integer.parseInt(input) - 1;
        if (ind >= taskList.size() || ind < 0) {
            throw new DukeException("OOPS!!! Invalid task number");
        }

        String result = ui.printDeletedTask(taskList.get(ind));
        taskList.remove(ind);

        try {
            storage.save(taskList);
        } catch (IOException e) {
            return ui.showError(e.getMessage());
        }

        return result;
    }
}
