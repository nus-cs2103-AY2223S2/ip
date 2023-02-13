package command;

import dukeexception.DukeException;
import storage.Storage;
import storage.TaskList;
import task.Event;
import userinteraction.Ui;

/**
 * Command class for adding event tasks.
 */
public class AddEventCommand extends AddTaskCommand {
    /**
     * Class constructor.
     *
     * @param inputArr String from a user input.
     */
    public AddEventCommand(String inputArr) {
        super(inputArr);
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
        Event event = Event.generate(this.getInputArr());
        taskList.addTask(event);
        storage.saveData(taskList);
        ui.printAddTaskMsg(taskList, event);
    }
}