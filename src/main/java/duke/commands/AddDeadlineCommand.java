package duke.commands;

import java.io.IOException;
import java.time.LocalDate;

import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.tasks.Deadline;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * A class that handles adding deadline.
 */
public class AddDeadlineCommand extends Command {

    private final String userInput;

    /**
     * Constructor for the AddDeadlineCommand class.
     *
     * @param userInput Given user input parsed by parser.
     */

    public AddDeadlineCommand(String userInput) {
        this.userInput = userInput;
    }

    /**
     * Adds a Deadline task to the given TaskList.
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
            throw new DukeException("OOPS!!! The description of a deadline cannot be empty.");
        }
        String[] taskNameAndDeadline = userInput.split(" ", 2)[1].split(" /by ");
        if (taskNameAndDeadline.length < 2) {
            throw new DukeException("OOPS!! The date of a deadline cannot be empty.");
        }
        String taskName = taskNameAndDeadline[0];
        String deadline = taskNameAndDeadline[1];
        Deadline userTask = new Deadline(taskName, LocalDate.parse(deadline));
        tasks.addTask(userTask);
        try {
            storage.appendToFile(storage.getFilePath(), "D | 0 | " + taskName + " | " + deadline + "\n");
        } catch (IOException e) {
            throw new DukeException("Error writing to file");
        }
        ui.showToUser("Got it. I've added this task: \n    " + userTask + "\nNow you have "
                        + tasks.getSize() + " duke.tasks in the list.");
    }
}
