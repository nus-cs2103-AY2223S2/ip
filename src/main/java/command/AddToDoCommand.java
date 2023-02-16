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
     * @param tasks Stores all tasks.
     * @param ui       Handles all user interaction.
     * @param storage  Handles all storage of tasks in a file.
     * @return Returns string output.
     * @throws DukeException Checks if input is valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ToDo toDo = ToDo.generate(this.getInputArr());
        tasks.addTask(toDo);
        storage.writeData(tasks);
        String output = ui.getAddTaskMsg(tasks, toDo);
        System.out.println(output);
        return output;
    }
}
