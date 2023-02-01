package duke.command;

import duke.exception.DukeException;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.util.Ui;

/**
 * Executable command to create todo.
 *
 * @author Guo-KeCheng
 */
public class TodoCommand extends Command {
    private String command;
    private TaskList taskList;
    private Ui ui;

    /**
     * TodoCommand constructor
     *
     * @param command Entire line of user input
     * @param taskList Existing taskList
     * @param ui Shared Ui object
     */
    public TodoCommand(String command, TaskList taskList, Ui ui) {
        this.command = command;
        this.taskList = taskList;
        this.ui = ui;
    }

    /**
     * Instantiate ToDo base on the string command
     * Checks for valid TaskName
     *
     * @throws DukeException if input is incorrect
     */
    @Override
    public boolean execute() throws DukeException {
        ToDo toDo = new ToDo(getTaskName("todo", command));
        taskList.add(toDo);
        ui.printAddedTask(toDo, taskList);

        return false;
    }
}
