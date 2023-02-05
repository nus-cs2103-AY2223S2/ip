package duke.command;

import duke.data.TaskList;
import duke.data.exception.DukeException;
import duke.data.task.Deadline;
import duke.storage.Storage;
import duke.ui.Ui;

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
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        Deadline addition = new Deadline(this.details, this.by);
        tasks.add(addition);
        String response = ui.showAddTask(addition) + "\n";
        response += ui.showTaskCount(tasks.size());
        return response;
    }
}
