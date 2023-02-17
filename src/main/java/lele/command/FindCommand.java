package lele.command;

import lele.storage.Storage;
import lele.task.TaskList;
import lele.ui.Ui;

/**
 * Given a regex,
 * executes the actions to take upon a find command.
 */
public class FindCommand extends Command {
    private final String regex;

    /**
     * Instantiates the regex queried by the user.
     *
     * @param regex Input taken from user.
     */
    public FindCommand(String regex) {
        this.regex = regex;
    }

    /**
     * Executes printing of tasks queried by user.
     *
     * @param taskList Current task list instance.
     * @param ui Current ui instance.
     * @param storage Current storage instance.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.printFind(taskList, this.regex);
    }

    /**
     * Find query will not terminate the program.
     *
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
