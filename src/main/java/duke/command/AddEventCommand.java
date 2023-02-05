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

    /**
     * Constructor for add event command.
     *
     * @param details Description of event task.
     * @param from Start of event task.
     * @param to End of event task.
     */
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
     * @return Response string.
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Event addition = new Event(this.details, this.from, this.to);
        tasks.add(addition);
        String response = ui.showAddTask(addition) + "\n";
        response += ui.showTaskCount(tasks.size());
        return response;
    }
}
