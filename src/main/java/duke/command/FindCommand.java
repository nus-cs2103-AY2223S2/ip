package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Command to find Tasks in TaskList with name containing given String.
 *
 * @author Lian Kok Hai
 */
public class FindCommand extends Command {
    private String str;

    /**
     * Constructs new FindCommand.
     *
     * @param str String to search for task name.
     */
    public FindCommand(String str) {
        this.str = str;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        return taskList.findTasks(this.str);
    }
}
