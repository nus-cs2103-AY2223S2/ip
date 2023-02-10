package panav.command;

import panav.storage.Storage;
import panav.task.TaskList;
import panav.ui.Ui;

/**
 * Class to represent the 'find' command.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Method represents the functionality when 'find' command is types.
     * @param tasks the tasks in the list.
     * @param ui ui to interact with user.
     * @param storage storage to read/write from files.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        String text = "Here are the matching tasks in your list:\n";
        text += tasks.printTasksContainingKeyword(this.keyword);
        return text;
    }

}
