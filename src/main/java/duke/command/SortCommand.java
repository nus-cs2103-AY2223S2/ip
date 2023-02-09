package duke.command;

import duke.exception.StorageFileException;
import duke.storage.Storage;
import duke.task.DukeTask;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * The SortCommand class is a command class that sorts all the tasks in the TaskList into different categories.
 * The categories are: Deadline Task, Event Task, Fixed Duration Task, Todo Task.
 * The sorted tasks are then saved in storage, and the sorted tasks are displayed to the user.
 */
public class SortCommand extends Command {
    private static final String NO_TASKS_MESSAGE = "There are no tasks in your list.";

    /**
     * Executes the SortCommand.
     *
     * @param tasks The TaskList that contains all the tasks.
     * @param ui The Ui that handles the display of the sorted tasks.
     * @param storage The Storage that saves the sorted tasks.
     * @throws StorageFileException If there is an error in reading or writing to the storage file.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws StorageFileException {
        // check if there are no tasks
        if (tasks.getNoOfTasks() == 0) {
            ui.appendResponse(NO_TASKS_MESSAGE);
            return;
        }

        // categorize all the tasks in the TaskList into different categories
        TaskList[] listOfList = tasks.categorizeTask();
        tasks.clearTasks();

        // add the sorted tasks back to the TaskList
        for (TaskList list : listOfList) {
            for (DukeTask task : list.getTasks()) {
                tasks.addTask(task);
            }
        }

        // save the sorted tasks in storage
        storage.saveTaskList(tasks);

        // append the final task list to the UI
        ui.appendResponse("Here are all your Deadline Task:\n" + listOfList[0] + "\n");
        ui.appendResponse("Here are all your Event Task:\n" + listOfList[1] + "\n");
        ui.appendResponse("Here are all your Fixed Duration Task:\n" + listOfList[2] + "\n");
        ui.appendResponse("Here are all your Todo Task:\n" + listOfList[3]);
    }
}
