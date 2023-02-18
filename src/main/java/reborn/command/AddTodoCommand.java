package reborn.command;

import reborn.data.TaskList;
import reborn.data.exception.RebornException;
import reborn.data.task.Todo;
import reborn.storage.Storage;
import reborn.ui.Ui;

/**
 * Adds to do task to TaskList.
 */
public class AddTodoCommand extends Command {
    private String details;
    public AddTodoCommand(String details) {
        this.details = details;
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
        Todo addition = new Todo(details);
        tasks.add(addition);
        String response = ui.showAddTask(addition) + "\n";
        response += ui.showTaskCount(tasks.size());
        return response;
    }
}
