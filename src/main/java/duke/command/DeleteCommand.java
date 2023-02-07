package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.util.Arrays;

/**
 * Deletes task from list of tasks when user input indicates delete.
 */
public class DeleteCommand extends Command {
    private Integer[] taskIndexes;
    private static final String ERROR_MESSAGE = "Invalid task index found. Unable to delete.";

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
     * @param storage Storage object that handles all Storage actions.
     * @throws DukeException Throws exception from the methods called in this method.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        if (taskIndexes == null) {
            return ERROR_MESSAGE;
        }
        String taskString = "";
        int deleteOffset = 1;
        for (int taskIndex: taskIndexes) {
            taskString = String.format("%s%s\n", taskString,
                    tasks.deleteTask(taskIndex - deleteOffset));
            deleteOffset++;
        }
        storage.saveTasks(tasks);
        return Ui.getDeleteOutput(taskString, tasks);
    }
}
