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
     * @param tasks Stores all tasks.
     * @param ui       Handles all user interaction.
     * @param storage  Handles all storage of tasks in a file.
     * @return Returns string output.
     * @throws DukeException Checks if input is valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        DeadLine deadLine = DeadLine.generate(this.getInputArr());
        tasks.addTask(deadLine);
        storage.writeData(tasks);
        return ui.getAddTaskMsg(tasks, deadLine);
    }
}
