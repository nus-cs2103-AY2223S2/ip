package duke.command;

import duke.UI.TextOutput;
import duke.exception.DukeException;
import duke.task.TaskList;

import java.lang.reflect.InvocationTargetException;

public class Update extends Command {

    public Update(String str) {
        this.setCommandStorage(str);
    }

    /**
     * Implements the find task functionality.
     * <p>
     * If found, then prints out the list of tasks found; alert user otherwise.
     * @param tasks the list of tasks to search.
     */
    public String execute(TaskList tasks){
        String content = this.getCommandStorage();
        assert content != "": "Must provide arguments to change task dates.";
        String[] substrings = content.split(" ");
        String taskId = substrings[1];
        String[] dates = new String[substrings.length - 2];
        System.arraycopy(substrings, 2, dates, 0, substrings.length - 2);
        try {
            return tasks.updateTaskTime(Integer.parseInt(taskId), dates);
        } catch (DukeException | NumberFormatException e) {
            return TextOutput.makeInvalidUpdateString();
        }
    }
}
