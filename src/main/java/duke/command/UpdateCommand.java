package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;

public class UpdateCommand extends Command {
    private final String details;

    /**
     * Constructor for UpdateDescriptionCommand class.
     * @param details contains index and new description of task to update.
     */
    public UpdateCommand(String details) {
        if (details.isBlank()) {
            throw new DukeException(":( OOPS!!! The description of a new task cannot be empty.");
        }
        this.details = details;
    }

    /**
     * Executes the command to list the currently stored Tasks in chronological order.
     * Deadlines are sorted by Deadline.by, Events are sorted by Event.from, while ToDos take the lowest precedence.
     * In the event of tiebreakers, they are sorted according to the order in the original TaskList.
     * @param tasks TaskList containing all the currently stored Tasks.
     * @param ui Ui that deals with interactions with the user.
     * @param storage Storage that loads and saves tasks to the file containing currently stored Tasks.
     * @return the response from Duke.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            int parseInt = Integer.parseInt(details.substring(0, 1));
            String newDescription = details.substring(2);
            Task task = tasks.get(parseInt - 1);
            task.setDescription(newDescription);
            storage.update(tasks);
            return "OK, I've updated the task:\n" + task;
        } catch (NumberFormatException e) {
            throw new DukeException(":( OOPS!!! I'm not able to understand your input. " +
                    "Please input in this format: <task index> <new description of task>");
        } catch (IndexOutOfBoundsException e) {
            throw new DukeException(":( OOPS!!! There are less than " + this.details + " tasks.");
        }
    }

    /**
     * Checks if the Command terminates the Duke Program.
     * @return false as SortCommand does not terminate the Duke program.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
