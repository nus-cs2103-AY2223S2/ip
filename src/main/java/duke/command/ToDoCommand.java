package duke.command;

import duke.DukeException;
import duke.Ui;
import duke.Storage;
import duke.TaskList;
import duke.Task;
import duke.ToDo;

public class ToDoCommand extends Command {
    String command;

    /**
     * Class constructor.
     *
     * @param command task to be added in list.
     */
    public ToDoCommand(String command) {
        this.command = command;
    }

    /**
     * Adds a new todo task to the list and returns a "taskAdded" message.
     *
     * @param taskList the list of tasks.
     * @param storage the items read from the file.
     * @param ui methods to be used to interact with the user.
     * @return "taskAdded" message.
     * @throws DukeException if input is wrong.
     */
    public String execute(TaskList taskList, Storage storage, Ui ui) throws DukeException {
        try {
            Task newTask;
            String description = command.substring(command.indexOf(" ") + 1);

            if (description.equals("")) {
                throw new DukeException("OOPS!!! The description of a todo task cannot be empty.");
            } else {
                newTask = new ToDo(command.substring(command.indexOf(" ") + 1));
            }
            taskList.addTask(newTask);
            return ui.showTaskAdded(newTask);
        } catch (DukeException e) {
            throw new DukeException(e.getMessage());
        }

    }
}

