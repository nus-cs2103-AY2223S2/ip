package duke.command;

import java.util.ArrayList;

import duke.exception.DukeException;
import duke.task.Task;
import duke.task.TaskList;
import duke.ui.Ui;


/**
 * Executable command to find task based on given keyword.
 *
 * @author Guo-KeCheng
 */
public class FindCommand extends Command {

    private final ArrayList<String> keywords;
    private final TaskList taskList;
    private final Ui ui;

    /**
     * FindCommand constructor
     *
     * @param command  Entire line of user input
     * @param taskList Existing taskList
     * @param ui       Shared Ui object
     */
    public FindCommand(String command, TaskList taskList, Ui ui) throws DukeException {
        this.keywords = getKeyword(command.split(" "));
        this.taskList = taskList;
        this.ui = ui;
    }

    /*
     * adds deadline base on the string command
     * deadline requires taskName and EndDate
     */
    @Override
    public String execute() throws DukeException {
        TaskList foundList = new TaskList();

        for (Task task : taskList) {
            for (String keyword : keywords) {
                if (task.containsKeyword(keyword)) {
                    foundList.add(task);
                }
            }
        }

        if (foundList.isEmpty()) {
            return ui.printNoTaskWithKeywordFound(keywords);
        }

        return ui.printFoundList(foundList);

    }
}
