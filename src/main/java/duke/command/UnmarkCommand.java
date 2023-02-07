package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Unmarks task when user input indicates unmark.
 */
public class UnmarkCommand extends Command {
    private final String[] taskIndexes;

    public UnmarkCommand(String ... taskIndexes) {
        this.taskIndexes = taskIndexes;
    }

    /**
     * Unmarks task and updates the task list in the local storage.
     *
     * @param tasks List of tasks.
     * @param storage Storage object that handles all Storage actions.
     * @throws DukeException Throws exception from the methods called in this method.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String taskString = "";
        for (String taskIndex: taskIndexes) {
            try {
                taskString = String.format("%s%s\n", taskString,
                        tasks.unmarkTask(Integer.parseInt(taskIndex) - 1));
            } catch (NumberFormatException e) {
                System.out.println("Invalid task index, skipping the unmarking of this task");
            }
        }
        storage.saveTasks(tasks);
        return Ui.getUnmarkOutput(taskString);
    }
}
