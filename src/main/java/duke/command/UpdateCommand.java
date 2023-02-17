package duke.command;

import duke.Deadline;
import duke.Event;
import duke.Task;
import duke.TaskList;
import duke.Todo;
import duke.Type;
import duke.exception.DukeException;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Handles the appropriate tasks when performing a UpdateCommand by Duke.
 */
public class UpdateCommand extends Command {

    private String details;

    public UpdateCommand(String details) {
        this.details = details;
    }
    /**
     * @param tasks   a list of tasks.
     * @param ui      Ui class to handle display messages.
     * @param storage Storage to handle saving/loading of data to/from the list of task.
     * @return duke's response message.
     */
    @Override
    public String initCommand(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String[] splitDetails = this.details.split(" ", 2);
        int index = Integer.parseInt(splitDetails[0]);
        String description = splitDetails[1];
        Task toBeReplacedTask = tasks.getTask(index);
        Type taskType = toBeReplacedTask.getType();
        switch (taskType) {
        case TODO:
            Todo newToDo = new Todo(description);
            tasks.set(--index, newToDo);
            return ui.displayUpdateMessage(newToDo);
        case DEADLINE:
            String[] deadlineFormatter = description.split(" /by ");
            if (deadlineFormatter.length < 2) {
                throw new DukeException("Either the description or deadline of the task is missing");
            } else {
                Deadline newDeadline = new Deadline(deadlineFormatter[0], deadlineFormatter[1]);
                tasks.set(--index, newDeadline);
                return ui.displayUpdateMessage(newDeadline);
            }
        case EVENT:
            // update 2 project meeting /from 2020-12-20 2000 /to 2020-12-25 2200
            String[] details = description.split(" /", 3);
            if (details.length < 3) {
                throw new IllegalArgumentException("Either the description or dates (from/to) of the task is missing");
            } else {
                String[] eventFormatter = description.split(" /from ");
                String[] startEndDate = eventFormatter[1].split(" /to ");
                Event event = new Event(eventFormatter[0], startEndDate[0], startEndDate[1]);
                tasks.set(--index, event);
                return ui.displayUpdateMessage(event);
            }
        default:
            throw new DukeException("Please try again");
        }
    }
}
