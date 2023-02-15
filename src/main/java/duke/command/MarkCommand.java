package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * Executable command to mark an existing task as completed.
 *
 * @author Guo-KeCheng
 */
public class MarkCommand extends Command {
    private final String input;
    private final TaskList taskList;
    private final Ui ui;

    private final Storage storage;

    /**
     * MarkCommand constructor.
     *
     * @param input    String representation of task number
     * @param taskList Existing taskList
     * @param ui       Shared Ui object
     * @param storage  Shared storage object
     */
    public MarkCommand(String input, TaskList taskList, Ui ui, Storage storage) {
        this.input = input;
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Mark corresponding task as completed.
     * Check for out of bounds as well as invalid syntax.
     *
     * @throws DukeException if input is incorrect
     */
    @Override
    public String execute() throws DukeException {
        int ind = -1;

        try {
            ind = Integer.parseInt(input) - 1;
        } catch (NumberFormatException e) {
            throw new DukeException("It's a disgrace to not be able to tell numbers from alphabets...");
        }

        if (ind >= taskList.size() || ind < 0) {
            throw new DukeException("If you look at the task number, you would know what's happening. "
                    + "And I say this in tears.");
        }
        // If index falls out of bounds
        if (ind >= taskList.size() || ind < 0) {
            throw new DukeException("OOPS!!! Invalid task number :(");
        }

        taskList.get(ind).markCompleted();

        try {
            storage.save(taskList);
        } catch (IOException e) {
            return ui.showError(e.getMessage());
        }

        return ui.printMarkedTask(taskList.get(ind));
    }
}
