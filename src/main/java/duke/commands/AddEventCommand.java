package duke.commands;

import java.io.IOException;
import java.time.LocalDate;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 *  A class that handles adding event.
 */
public class AddEventCommand extends Command {

    private final String userInput;

    /**
     * Constructor for the AddEventCommand class.
     *
     * @param userInput Given user input parsed by parser.
     */
    public AddEventCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Adds an Event task to the given TaskList.
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
            throw new DukeException("OOPS!!! The description of an event cannot be empty.");
        }
        String[] taskNameAndDate = userInput.split(" ", 2)[1].split(" /from ");
        if (taskNameAndDate.length < 2) {
            throw new DukeException("OOPS!!! The from date of an event cannot be empty.");
        }
        String taskName = taskNameAndDate[0];
        String[] toAndFrom = taskNameAndDate[1].split(" /to ");
        if (toAndFrom.length < 2) {
            throw new DukeException("OOPS!!! The to date of an event cannot be empty.");
        }
        String from = toAndFrom[0];
        String to = toAndFrom[1];
        Event userTask = new Event(taskName, LocalDate.parse(from), LocalDate.parse(to));
        tasks.addTask(userTask);
        try {
            storage.appendToFile(storage.getFilePath(), "E | 0 | " + taskName + " | " + from + " | " + to + "\n");
        } catch (IOException e) {
            throw new DukeException("Error writing to file");
        }
        ui.showToUser("Got it. I've added this task: \n    " + userTask + "\nNow you have "
                        + tasks.getSize() + " duke.tasks in the list.");
    }
}
