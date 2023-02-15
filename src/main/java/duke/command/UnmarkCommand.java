package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Unmarks task when user input states unmark.
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
     * @param storage object that handles all Storage actions.
     * @throws DukeException from the methods called in this method.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String partialOutputString = unmarkTasks(tasks);
        storage.saveTasks(tasks);
        return Ui.getUnmarkOutput(partialOutputString);
    }

    /**
     * Unmarks the tasks indicated in taskIndexes.
     *
     * @param tasks List of all tasks.
     * @return String of tasks that have been unmarked.
     * @throws DukeException if there is a problem with unmarkTask.
     */
    private String unmarkTasks(TaskList tasks) throws DukeException {
        String taskString = "";
        for (String taskIndex: taskIndexes) {
            try {
                taskString = String.format("%s%s\n", taskString,
                        tasks.unmarkTask(Integer.parseInt(taskIndex) - 1));
            } catch (NumberFormatException e) {
                System.out.println("Invalid task index, skipping the unmarking of this task");
            }
        }
        return taskString;
    }
}
