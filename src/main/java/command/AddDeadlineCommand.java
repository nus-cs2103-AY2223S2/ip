package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import task.Deadline;
import userinteraction.Ui;

/**
 * Command class for adding deadline tasks.
 */
public class AddDeadlineCommand extends AddTaskCommand{
    /**
     * Class constructor.
     *
     * @param input String from a user input.
     */

    public AddDeadlineCommand(String input) {
        super(input);

    }

    /**
     * Creates a deadline task, adds it to the task list and saves it to the file.
     *
     * @param taskList Stores all tasks.
     * @param ui       The Ui to be used for printing messages.
     * @param storage  Saves all tasks in a file
     * @throws DukeException Checks the validation of input.
     */
    @Override
    public void process(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Deadline deadline = Deadline.generate(this.getInput());
        taskList.addTask(deadline);
        storage.saveData(taskList);
        ui.printAddTaskMsg(taskList, deadline);
    }
}

