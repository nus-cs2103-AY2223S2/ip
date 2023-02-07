package duke.commands;

import java.io.IOException;
import java.time.format.DateTimeParseException;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command that only adds Deadline tasks to the TaskList.
 * */
public class AddDeadlineCommand extends Command {
    private final String description;
    private final String deadline;

    /**
     * Constructs an AddDeadlineCommand
     * @param description of the deadline task
     * @param deadline date in String format
     */
    public AddDeadlineCommand(String description, String deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    /**
     * Creates and adds a new Deadline task to the TaskList.
     * Prompts Ui to notify the user, then prompts Storage class to append the new task to the task storage file.
     * @param tasks Existing TaskList used by the main Duke class.
     * @param ui Existing Ui used by the main Duke class.
     * @param storage Existing Storage used by the main Duke class.
     * @return output to be shown to user
     * @throws DukeException if something happened to task storage file during runtime or deadline field is not
     *     in the correct format.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            Task newTask = new Deadline(description, deadline);
            tasks.add(newTask);
            storage.appendToFile(newTask);
            return ui.showToUser("You have added: " + newTask, "You have " + tasks.getSize() + " tasks in the list.");
        } catch (IOException e) {
            throw new DukeException("Unable to write to file. Please run Duke again.");
        } catch (DateTimeParseException e) {
            throw new DukeException("Date must be in yyyy-mm-dd format.");
        }
    }
}
