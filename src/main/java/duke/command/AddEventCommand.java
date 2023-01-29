package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Event;
import duke.storage.Storage;
import duke.ui.Ui;

/**
 * Adds event task to TaskList
 */
public class AddEventCommand extends Command {
    private String details;
    private String from;
    private String to;
    public AddEventCommand(String details, String from, String to) {
        this.details = details;
        this.from = from;
        this.to = to;
    }
    /**
     * Executes the command given by the user.
     *
     * @param tasks   to be modified
     * @param ui      to display changes
     * @param storage to interact with as necessary
     * @throws DukeException
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event addition = new Event(this.details, this.from, this.to);
        tasks.add(addition);
        ui.showAddTask(addition);
        ui.showTaskCount(tasks.size());
    }
}
