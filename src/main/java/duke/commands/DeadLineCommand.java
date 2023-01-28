package duke.commands;

import duke.duke.Ui;
import duke.exceptions.DukeException;
import duke.storage.Storage;
import duke.storage.TaskList;
import duke.tasks.Deadlines;

import java.io.IOException;

public class DeadLineCommand extends Command {
    private final Deadlines deadline;

    public DeadLineCommand(Deadlines deadline) {
        this.deadline = deadline;
    }

    /**
     * Adds a deadline to the task list before saving the list.
     * @param tasks
     * @param ui
     * @param storage
     * @throws DukeException
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        tasks.add(this.deadline);
        ui.display("Got it. I've added this task:\n" + this.deadline +
                String.format("\nNow you have %s tasks in the list.", tasks.size()));

        try {
            storage.dumpFile(tasks);
        } catch (IOException err) {
            throw new DukeException("IO Exception occurred!");
        }

    }
}
