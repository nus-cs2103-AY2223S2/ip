package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.utils.Formatter;

/**
 * Represents a Command to display tasks in an indexed list to user.
 * */
public class ListCommand extends Command {


    /**
     * Prompts Ui to display all tasks in an indexed list.
     * Also prompts Ui to notify user if no tasks exist in TaskList.
     * @param tasks Existing TaskList used by the main Duke class.
     * @param storage Existing Storage used by the main Duke class.
     * @return output to be shown to user
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        assert tasks != null;
        if (tasks.getSize() == 0) {
            return "LOADING... Task List is currently empty.\n";
        } else {
            return Formatter.formatMultipleMessages("LOADING... Here are your tasks: ",
                    Formatter.formatIndexedList(tasks.getList()));
        }

    }
}
