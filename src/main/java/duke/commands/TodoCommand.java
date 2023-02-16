package duke.commands;

import duke.tasks.Todos;
import duke.duke.Ui;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;

import java.io.IOException;

/**
 * A command for adding a to-do to the task list.
 */
public class TodoCommand extends Command {
    private final Todos todo;
    public TodoCommand(Todos todo) {
        this.todo = todo;
    }

    /**
     * Adds a to-do to the task list before saving the list.
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        int previousLength = tasks.size();
        tasks.add(this.todo);
        assert tasks.size() - previousLength == 1;
        String message = "Beep. I've added this task:\n" + this.todo
                + String.format("\nNow you have %s tasks in the list.", tasks.size());
        ui.display(message);
        try {
            storage.dumpFile(tasks);
        } catch (Exception err) {
            throw new DukeException("Error while saving file!");
        }

        return message;
    }
}
