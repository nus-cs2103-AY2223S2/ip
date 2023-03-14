package nemo.command;

import nemo.exception.NemoException;
import nemo.storage.Storage;
import nemo.task.TaskList;
import nemo.ui.Ui;

/**
 * Command to find Tasks in TaskList with name containing given String.
 *
 * @author Lian Kok Hai
 */
public class FindCommand extends Command {
    private String[] strings;

    /**
     * Constructs new FindCommand.
     *
     * @param str String to search for task name.
     */
    public FindCommand(String str) {
        this.strings = str.split(" ");
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws NemoException {
        return taskList.findTasks(this.strings);
    }
}
