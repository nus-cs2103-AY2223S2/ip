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
     * @param tasks Stores all tasks.
     * @param ui       Handles all user interaction.
     * @param storage  Handles all storage of tasks in a file.
     * @return Returns string output.
     * @throws DukeException Checks if input is valid.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event event = Event.generate(this.getInputArr());
        tasks.addTask(event);
        storage.writeData(tasks);
        String output = ui.addTaskMsg(tasks, event);
        System.out.println(output);
        return output;
    }
}
