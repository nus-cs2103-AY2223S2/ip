package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a command by user to Duke to add a Todo task.
 */
public class AddTodoCommand extends Command {

    private String name;

    /**
     * Initialises new instance of AddTodoCommand.
     *
     * @param name The name of the task.
     */
    public AddTodoCommand(String name) {
        this.name = name;
    }

    /**
     * Checks if command is an exit command.
     *
     * @return false Add Todo task is not an exit command.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Adds the Todo task to user's list of Tasks. Prints a message indicating to user that Todo task was
     * successfully added.
     *
     * @param tasks A TaskList containing the set of task the user has.
     * @param storage A Storage enabling Duke to store memory.
     * @return String The String message indicating status of action.
     * @throws DukeException
     */
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        return Ui.addTaskResponse(tasks.addTodo(name), tasks);
    }

}
