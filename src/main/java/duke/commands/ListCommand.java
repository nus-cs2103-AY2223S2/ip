package duke.commands;

import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Represents a Command to display tasks in an indexed list to user.
 * */
public class ListCommand extends Command {


    /**
     * Prompts Ui to display all tasks in an indexed list.
     * Also prompts Ui to notify user if no tasks exist in TaskList.
     * @param tasks Existing TaskList used by the main Duke class.
     * @param ui Existing Ui used by the main Duke class.
     * @param storage Existing Storage used by the main Duke class.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (tasks.getSize() == 0) {
            ui.showToUser("Task List is currently empty.");
        } else {
            ui.showToUser("Here are your tasks: ");
            ui.showIndexedList(tasks.getList());
        }

    }
}
