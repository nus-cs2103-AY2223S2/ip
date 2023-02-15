package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that is used to add a todo task when executed.
 */
public class ToDoCommand extends Command {
    private String content;

    /**
     * Class constructor of TodoCommand.
     * @param content the content of the todo task that contains its title.
     */
    public ToDoCommand(String content) {
        this.content = content;
    }

    /**
     * Executes the ToDoCommand to add an event task into the given TaskList.
     * @param tasks the TaskList of the Duke
     * @param ui the Ui of the Duke
     * @param storage the storage of the Duke
     * @return the message to indicate addition of the todo task
     * @throws DukeException if error occurs during addition of the todo task
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        content = content.trim();
        String res = tasks.addToDo(content);
        return res;
    }

    /**
     * Returns true when the command indicates the closure of the software.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
