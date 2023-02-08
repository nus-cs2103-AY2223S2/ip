package duke.commands;

import java.util.List;

import duke.tasks.Task;
import duke.tasks.TaskList;
import duke.ui.Ui;


/**
 * FindCommand represents a command to find a list of tasks based
 * on similarity of a given string.
 */
public class FindCommand extends Command {

    private Ui ui;
    private TaskList taskList;
    private String string;

    /**
     * Creates a FindCommand to find all tasks on the Task list
     * based on similarity of a given string.
     *
     * @param ui The ui used.
     * @param taskList The TaskList to find the task.
     * @param string The specified string to find.
     */
    public FindCommand(Ui ui, TaskList taskList, String string) {
        this.ui = ui;
        this.taskList = taskList;
        this.string = string;
    }

    /**
     * Finds all the tasks on the TaskList with string.
     */
    @Override
    public String action() {
        List<Task> matchedTasks = taskList.findTask(string);
        assert matchedTasks != null;
        return ui.findResponse(matchedTasks);
    }
}
