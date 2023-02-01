package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.TaskList;
import duke.task.ToDo;
import duke.ui.Ui;

import java.time.DateTimeException;

/**
 * Command that add Tasks.
 */
public class AddCommand extends Command {
    private final String taskType;
    private final String details;

    /**
     * Constructor for class AddCommand
     * @param taskType type of task to be created.
     * @param details details of the task to be created.
     */
    public AddCommand(String taskType, String details) {
        if (details.isBlank()) {
            throw new DukeException("☹ OOPS!!! The description of a new task cannot be empty.");
        }
        this.taskType = taskType;
        this.details = details;
    }

    /**
     * Executes the command to create the task.
     * @param tasks TaskList containing all the currently stored Tasks.
     * @param ui Ui that deals with interactions with the user.
     * @param storage Storage that loads and saves tasks to the file containing currently stored Tasks.
     * @throws DukeException If user inputs dates and timings in the wrong format when creating a Deadline or Event.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            switch (taskType) {
                case "todo":
                    ToDo toDo = ToDo.addToDo(this.details);
                    tasks.add(toDo);
                    ui.show("Got it, I've added this task:");
                    ui.show(String.valueOf(toDo));
                    break;
                case "deadline":
                    Deadline deadline = Deadline.addDeadline(details);
                    tasks.add(deadline);
                    ui.show("Got it, I've added this task:");
                    ui.show(String.valueOf(deadline));
                    break;
                case "event":
                    Event event = Event.addEvent(details);
                    tasks.add(event);
                    ui.show("Got it, I've added this task:");
                    ui.show(String.valueOf(event));
                    break;
                default:
                    break;
            }
            storage.update(tasks);
        } catch (DateTimeException e) {
            throw new DukeException("Please key dates in this format: dd-MM-yyyy HHmm");
        }
    }

    /**
     * Checks if the Command terminates the Duke Program.
     * @return false as AddCommand does not terminate the Duke program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
