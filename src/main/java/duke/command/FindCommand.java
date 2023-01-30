package duke.command;

import duke.DukeException;
import duke.task.Task;
import duke.task.TaskList;

/**
 * A command that searches for any tasks that contains a keyword.
 * @author Junyi
 */
public class FindCommand extends Command {

    private final TaskList taskList;
    private final String keyword;

    /**
     * Constructor for FindCommand.
     * @param taskList TaskList of Duke's tasks.
     * @param keyword The keyword to identify tasks to filter by.
     */
    public FindCommand(TaskList taskList, String keyword) {
        this.taskList = taskList;
        this.keyword = keyword;
    }

    @Override
    public String execute() throws DukeException {
        StringBuilder stringBuilder = new StringBuilder();

        stringBuilder.append(String.format("Found tasks with keywords: %s\n", keyword));
        for (Task task : taskList.allTasks()) {
            if (task.containsKeyword(keyword)) {
                stringBuilder.append(task);
                stringBuilder.append("\n");
            }
        }

        return stringBuilder.toString();
    }
}
