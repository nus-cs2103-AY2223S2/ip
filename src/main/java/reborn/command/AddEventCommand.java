package reborn.command;

import reborn.data.TaskList;
import reborn.data.exception.RebornException;
import reborn.data.task.Event;
import reborn.storage.Storage;
import reborn.ui.Ui;

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
     * @throws RebornException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws RebornException {
        Event addition = new Event(this.details, this.from, this.to);
        tasks.add(addition);
        String response = ui.showAddTask(addition) + "\n";
        response += ui.showTaskCount(tasks.size());
        return response;
    }
}
