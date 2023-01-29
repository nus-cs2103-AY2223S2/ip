package duke.commands;

import duke.duke.Ui;
import duke.exceptions.DukeException;
import duke.exceptions.StorerEmptyException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.tasks.Task;
import java.io.IOException;




/**
 * A command for deleting a task.
 * Stores a number, the index of a task in the task list.
 */
public class DeleteCommand extends Command {
    private final int num;
    public DeleteCommand(int num) {
        this.num = num;
    }

    /**
     * Deletes a task from the task list, specified by the number.
     * Saves the task list afterwards.
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {

        if (tasks.size() == 0) {
            throw new StorerEmptyException();
        } else {

            Task e = tasks.remove(this.num);
            String speech = "Noted. I've removed this task:\n"
                    + e + "\n Now you have " + tasks.size() + " tasks in the list.";
            ui.display(speech);
            try {
                storage.dumpFile(tasks);
            } catch (IOException err) {
                throw new DukeException("IO Exception occurred!");
            }

        }
    }
}
