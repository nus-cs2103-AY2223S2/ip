package duke.commands;

import java.util.ArrayList;

import duke.taskers.Task;
import duke.utils.Storage;
import duke.utils.TaskList;
import duke.utils.Ui;

/**
 * Find Command.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";
    private String keyword;

    /**
     * Constructor.
     *
     * @param keyword The keyword that is to be searched for.
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
    }

    /**
     * Executes the task.
     *
     * @param taskList Respective task list.
     * @param ui Respective Ui.
     * @param storage Respective storage.
     * @return String response.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        ArrayList<Task> matched = taskList.findMatching(this.keyword);
        return ui.showMatchingTasks(matched);
    }

    /**
     * Checks if command exits.
     * @return False.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
