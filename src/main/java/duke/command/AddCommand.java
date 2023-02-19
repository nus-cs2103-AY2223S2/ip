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
            throw new DukeException(":( OOPS!!! The description of a new task cannot be empty.");
        }
        this.taskType = taskType;
        this.details = details;
    }

    /**
     * Executes the command to create the task.
     * @param tasks TaskList containing all the currently stored Tasks.
     * @param ui Ui that deals with interactions with the user.
     * @param storage Storage that loads and saves tasks to the file containing currently stored Tasks.
     * @return the response from Duke.
     * @throws DukeException If user inputs dates and timings in the wrong format when creating a Deadline or Event.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            String dukeResponse = "";
            switch (taskType) {
            case "todo":
                ToDo toDo = ToDo.addToDo(this.details);
                tasks.add(toDo);
                dukeResponse = "Got it, I've added this task:\n" + toDo;
                break;
            case "deadline":
                Deadline deadline = Deadline.addDeadline(details);
                tasks.add(deadline);
                dukeResponse = "Got it, I've added this task:\n" + deadline;
                break;
            case "event":
                Event event = Event.addEvent(details);
                tasks.add(event);
                dukeResponse = "Got it, I've added this task:\n" + event;
                break;
            default:
                break;
            }
            storage.update(tasks);
            return dukeResponse;
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
