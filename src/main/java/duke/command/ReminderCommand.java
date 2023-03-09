package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
/**
 * Reminder Command is a command that reminds the user of tasks with deadlines.
 */
public class ReminderCommand extends Command {
    /**
     * A Constructor for ReminderCommand.
     */
    public ReminderCommand() {
        super(false);
    }

    /**
     * Outputs to user the list of tasks with deadlines.
     *
     * @param tasks Tasklist containing the list of tasks, displays the add message to user.
     * @param storage Saves tasks into the file locally.
     * @param ui Deals with interactions with user.
     * @return String response from Duke.
     * @throws DukeException if duke does not recognise the command.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) throws DukeException {
        return ui.printDeadlineList(tasks.getTasksWithDeadlines());
    }
}
