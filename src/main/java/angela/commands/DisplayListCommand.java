package angela.commands;

import angela.storage.Storage;
import angela.task.TaskList;
import angela.uitext.UiText;

/**
 * Represents a command to display a task list.
 */
public class DisplayListCommand extends Command {
    @Override
    public String execute(TaskList taskList, UiText uiText, Storage storage) {
        if (taskList.getSize() == 0) {
            return "Hmph. You have no tasks at all. Don't slack off.";
        }
        return "Your tasks are:\n" + taskList;
    }
}
