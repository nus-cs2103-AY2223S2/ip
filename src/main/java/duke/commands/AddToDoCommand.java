package duke.commands;

import java.io.IOException;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

/**
 * A class that handles adding To Do.
 */
public class AddToDoCommand extends Command {

    private final String userInput;

    /**
     * Constructor for the AddToDoCommand class.
     *
     * @param userInput Given user input parsed by parser.
     */
    public AddToDoCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Adds a ToDo task to the given TaskList.
     * Displays success message if task is added or throw exception if fails.
     *
     * @param tasks The TaskList to add the new task.
     * @param ui Ui given by Duke.
     * @param storage Storage for storing the newly created task.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] inputs = userInput.split(" ");
        if (inputs.length < 2) {
            throw new DukeException("OOPS!!! The description of a todo cannot be empty.");
        }
        String taskName = userInput.split(" ", 2)[1];
        ToDo userTask = new ToDo(taskName);
        tasks.addTask(userTask);
        try {
            storage.appendToFile(storage.getFilePath(), "T | 0 | " + taskName + "\n");
        } catch (IOException e) {
            throw new DukeException("Error writing to file");
        }
        ui.showToUser("Got it. I've added this task: \n    " + userTask + "\nNow you have "
                        + tasks.getSize() + " tasks in the list.");
    }
}
