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
     * @param input String from a user input.
     */
    public AddEventCommand(String input) {
        super(input);
    }

    /**
     * Creates a deadline task, adds it to the task list and saves it to the file.
     *
     * @param taskList Stores all tasks.
     * @param ui       The Ui to be used for printing messages.
     * @param storage  Saves all tasks in a file.
     * @return Returns the String message about adding an event task.
     * @throws DukeException Checks the validation of input.
     */
    @Override
    public String process(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        Event event = Event.generate(this.getInput());
        taskList.addTask(event);
        storage.saveData(taskList);
        return ui.printAddTaskMsg(taskList, event);
    }
}
