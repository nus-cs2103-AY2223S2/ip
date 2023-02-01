package command;

import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import task.ToDo;
import userinteraction.Ui;

/**
 * Command class for adding todos.
 */
public class AddToDoCommand extends AddTaskCommand {
    /**
     * Public Constructor.
     *
     * @param inputArr String array from user input.
     */
    public AddToDoCommand(String[] inputArr) {
        super(inputArr);
    }

    /**
     * Generates todo task, adds to tasklist and saves data to file.
     *
     * @param taskList Stores all tasks.
     * @param ui       Handles all user interaction.
     * @param storage  Handles all storage of tasks in a file.
     * @throws DukeException Checks if input is valid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ToDo toDo = ToDo.generate(inputArr);
        taskList.addTask(toDo);
        storage.writeData(taskList);
        ui.printAddTaskMsg(taskList, toDo);
    }
}
