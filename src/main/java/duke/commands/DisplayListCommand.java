package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.uitext.UiText;

/**
 * Represents a command to display a task list.
 */
public class DisplayListCommand extends Command {
    @Override
    public String execute(TaskList taskList, UiText uiText, Storage storage) {
        return "Your tasks are:\n" + taskList.toString();
    }
}
