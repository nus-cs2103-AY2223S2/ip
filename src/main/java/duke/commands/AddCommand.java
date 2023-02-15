package duke.commands;

import java.time.LocalDateTime;

import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.TaskList;
import duke.task.Todo;
import duke.ui.Ui;

/**
 * The AddCommand class implements the action of adding tasks.
 *
 * @author Chia Jeremy
 */
public class AddCommand extends Command {

    private final Task task;

    /**
     * Class constructor of an add todo task command.
     *
     * @param description the description of the task
     */
    public AddCommand(String description) {
        this.task = new Todo(description);
    }

    /**
     * Class constructor of an add deadline task command.
     *
     * @param description the description of the task
     * @param dateTime    the deadline to finish the task
     */
    public AddCommand(String description, LocalDateTime dateTime) {
        this.task = new Deadline(description, dateTime);
    }

    /**
     * Class constructor of an add event task command.
     *
     * @param description   the description of the task
     * @param startDateTime the starting date and time
     * @param endDateTime   the ending date and time
     */
    public AddCommand(String description, LocalDateTime startDateTime, LocalDateTime endDateTime) {
        this.task = new Event(description, startDateTime, endDateTime);
    }

    /**
     * Executes the add command.
     *
     * @param storage the file to save the tasks
     * @param tasks   the task lists
     * @param ui      the interface that deals with interactions with the user
     */
    @Override
    public void execute(Storage storage, TaskList tasks, Ui ui) {
        addToTaskList(tasks);
        addToStorage(storage, tasks);
        setUiResponse(tasks, ui);
    }

    private void addToTaskList(TaskList tasks) {
        tasks.add(this.task);
    }

    private void addToStorage(Storage storage, TaskList tasks) {
        storage.add(tasks.get());
    }

    private void setUiResponse(TaskList tasks, Ui ui) {
        ui.setResponse("Got it. I've added this task:\n"
                + this.task + "\n"
                + "Now you have " + tasks.getSize() + " tasks in the list.");
    }
}
