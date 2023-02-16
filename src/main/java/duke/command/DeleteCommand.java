package duke.command;

import java.util.Arrays;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Deletes task from list of tasks when user input states delete.
 */
public class DeleteCommand extends Command {
    private static final String ERROR_MESSAGE = "Invalid task index found. Unable to delete.";
    private Integer[] taskIndexes;

    /**
     * Constructor for DeleteCommand.
     *
     * @param taskIndexes to be deleted.
     */
    public DeleteCommand(String ... taskIndexes) {
        try {
            this.taskIndexes = Arrays.stream(taskIndexes)
                    .map(Integer::parseInt)
                    .toArray(Integer[]::new);
            Arrays.sort(this.taskIndexes);
        } catch (NumberFormatException e) {
            System.out.println(ERROR_MESSAGE);
        }
    }

    /**
     * Deletes task and updates the task list in the local storage.
     *
     * @param tasks List of tasks.
     * @param storage object that handles all Storage actions.
     * @throws DukeException from the methods called in this method.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (taskIndexes == null) {
            return ERROR_MESSAGE;
        }

        String partialOutputString = deleteTasks(tasks);
        storage.saveTasks(tasks);
        return Ui.getDeleteOutput(partialOutputString, tasks);
    }

    /**
     * Deletes the tasks indicated in taskIndexes.
     *
     * @param tasks to be deleted.
     * @return String of tasks that have been deleted.
     * @throws DukeException if there is a problem with deleteTask.
     */
    private String deleteTasks(TaskList tasks) throws DukeException {
        String taskString = "";
        int deleteOffset = 1;
        for (int taskIndex: taskIndexes) {
            taskString = String.format("%s%s\n", taskString,
                    tasks.deleteTask(taskIndex - deleteOffset));
            deleteOffset++;
        }
        return taskString;
    }

    @Override
    public String toString() {
        return String.format("Delete task(s) %s", taskIndexes.toString());
    }
}
