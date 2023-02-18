package reborn.command;

import reborn.data.TaskList;
import reborn.data.exception.RebornException;
import reborn.data.task.Deadline;
import reborn.storage.Storage;
import reborn.ui.Ui;

/**
 * Adds deadline task to TaskList
 */
public class AddDeadlineCommand extends Command {
    private String details;
    private String by;

    /**
     * Constructor for add deadline command.
     *
     * @param details Description of deadline task.
     * @param by Deadline of deadline task.
     */
    public AddDeadlineCommand(String details, String by) {
        this.details = details;
        this.by = by;
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
        Deadline addition = new Deadline(this.details, this.by);
        tasks.add(addition);
        String response = ui.showAddTask(addition) + "\n";
        response += ui.showTaskCount(tasks.size());
        return response;
    }
}
