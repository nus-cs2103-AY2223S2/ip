package duke.command;
import duke.task.TaskList;
import duke.UI.TextOutput;

/**
 * Implements the find task functionality.
 */
public class Find extends Command {

    public Find(String str) {
        this.setCommandStorage(str);
    }

    /**
     * Implements the find task functionality.
     * <p>
     * If found, then prints out the list of tasks found; alert user otherwise.
     * @param tasks The list of tasks to search.
     */
    public String execute(TaskList tasks) {
        String content = this.getCommandStorage();
        assert content != "": "Must provide task content to find tasks";
        String[] substrings = content.split("find ");
        assert substrings.length > 0: "Must specify the task contents to find";
        String item = substrings[1];
        TaskList foundTasks = tasks.findTask(item);
        if (foundTasks.getTaskCount() == 0) {
            return TextOutput.makeTaskNotFoundString();
        } else {
            return TextOutput.makeTaskFoundString(foundTasks);
        }
    }
}
