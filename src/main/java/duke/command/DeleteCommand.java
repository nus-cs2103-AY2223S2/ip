package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.IoHandler;

/**
 * Represents Duke's delete function.
 */
public class DeleteCommand extends Command {
    /** Constructs the delete command. */
    public DeleteCommand() {}

    /**
     * Deletes a task and stores the resulting tasklist.
     *
     * @throws DukeException If user input is invalid.
     */
    @Override
    public String execute(TaskList tasks, IoHandler ui, Storage store) throws DukeException {
        Integer taskNum = ui.getTaskNum();
        String s = tasks.delete(taskNum);
        store.saveToFile(tasks);
        return ui.produceDukeOutput("Noted. I've removed this task:\n\t" + s + "\n" + "Now you have "
                + Integer.toString(tasks.size()) + " tasks in the list.\n");
    };
}
