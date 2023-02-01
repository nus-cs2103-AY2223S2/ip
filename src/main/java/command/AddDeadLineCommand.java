package command;

import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import task.DeadLine;
import userinteraction.Ui;

/**
 * Command class for adding deadlines.
 */
public class AddDeadLineCommand extends AddTaskCommand {
    /**
     * Public constructor.
     *
     * @param inputArr String array from user input.
     */
    public AddDeadLineCommand(String[] inputArr) {
        super(inputArr);
    }

    /**
     * Generates deadline task, adds to tasklist and saves data to file.
     *
     * @param taskList Stores all tasks.
     * @param ui       Handles all user interaction.
     * @param storage  Handles all storage of tasks in a file.
     * @throws DukeException Checks if input is valid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        DeadLine deadLine = DeadLine.generate(this.getInputArr());
        taskList.addTask(deadLine);
        storage.writeData(taskList);
        ui.printAddTaskMsg(taskList, deadLine);
    }
}
