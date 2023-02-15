package duke.command;

import duke.task.TaskList;

public class Update extends Command {

    public Update(String str) {
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
        String taskId = substrings[1];
        String[] dates = new String[substrings.length - 2];
        System.arraycopy(substrings, 2, dates, 0, substrings.length - 2);
        return tasks.updateTaskTime(Integer.parseInt(taskId), dates);
    }
}
