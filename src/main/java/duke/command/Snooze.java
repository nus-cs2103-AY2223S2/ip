package duke.command;

import duke.UI.TextOutput;
import duke.task.TaskList;

public class Snooze extends Command {

    public Snooze(String str) {
        this.setCommandStorage(str);
    }

    /**
     * Implements the find task functionality. If found, then prints out the list of tasks found; alert user otherwise.
     * @param tasks the list of tasks to search.
     */
    public String execute(TaskList tasks) {
        String content = this.getCommandStorage();
        assert content != "": "Must provide arguments to snooze a task";
        String[] substrings = content.split(" ");
        String item = substrings[1];
        String days = substrings[2];
        TaskList foundTasks = tasks.findTask(item);
        if (foundTasks.getTaskCount() == 0) {
            return TextOutput.makeTaskFoundString();
        }
        foundTasks.loadTasks();
    }
}
