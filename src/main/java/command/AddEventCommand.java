package command;

import dukeexception.DukeException;
import store.Storage;
import store.TaskList;
import task.Event;
import userinteraction.Ui;

/**
 * Command class for adding events.
 */
public class AddEventCommand extends AddTaskCommand {
    /**
     * Public constructor.
     *
     * @param inputArr String array from user input.
     */
    public AddEventCommand(String[] inputArr) {
        super(inputArr);
    }

    /**
     * Generates event task, adds to tasklist and saves data to file.
     *
     * @param taskList Stores all tasks.
     * @param ui       Handles all user interaction.
     * @param storage  Handles all storage of tasks in a file.
     * @throws DukeException Checks if input is valid.
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event = Event.generate(this.getInputArr());
        taskList.addTask(event);
        storage.writeData(taskList);
        ui.printAddTaskMsg(taskList, event);
    }
}
