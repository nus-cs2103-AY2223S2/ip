package duke.command;

import java.io.IOException;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;
import duke.util.Storage;

/**
 * Executable command to create todo.
 *
 * @author Guo-KeCheng
 */
public class TodoCommand extends Command {
    private final String input;
    private final TaskList taskList;
    private final Ui ui;

    private final Storage storage;

    /**
     * TodoCommand constructor.
     *
     * @param input    ToDo Name
     * @param taskList Existing taskList
     * @param ui       Shared Ui object
     * @param storage  Shared storage object
     */
    public TodoCommand(String input, TaskList taskList, Ui ui, Storage storage) {
        this.input = input;
        this.taskList = taskList;
        this.ui = ui;
        this.storage = storage;
    }

    /**
     * Instantiate ToDo base on the string command
     * Checks for valid TaskName
     *
     * @throws DukeException if input is incorrect
     */
    @Override
    public String execute() throws DukeException {
        ToDo toDo = new ToDo(input);
        taskList.add(toDo);

        try {
            storage.save(taskList);
        } catch (IOException e) {
            return ui.showError(e.getMessage());
        }

        return ui.printAddedTask(toDo, taskList);

    }
}
