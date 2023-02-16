package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * Marks task when user input indicates mark.
 */
public class MarkCommand extends Command {
    private final String[] taskIndexes;

    public MarkCommand(String ... taskIndexes) {
        this.taskIndexes = taskIndexes;
    }

    /**
     * Marks task and updates the task list in the local storage.
     *
     * @param tasks List of tasks.
     * @param storage object that handles all Storage actions.
     * @throws DukeException from the methods called in this method.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws DukeException {
        String partialOutputString = markTasks(tasks);
        storage.saveTasks(tasks);
        return Ui.getMarkOutput(partialOutputString);
    }

    /**
     * Marks the tasks indicated in taskIndexes.
     *
     * @param tasks List of all tasks.
     * @return String of tasks that have been marked.
     * @throws DukeException if there is a problem with markTask.
     */
    private String markTasks(TaskList tasks) throws DukeException {
        String taskString = "";
        for (String taskIndex: taskIndexes) {
            try {
                taskString = String.format("%s%s%s\n", taskString, Ui.SEPARATOR,
                        tasks.markTask(Integer.parseInt(taskIndex) - 1));
            } catch (NumberFormatException e) {
                System.out.println("Invalid task index, skipping the marking of this task");
            }
        }
        return taskString;
    }

    @Override
    public String toString() {
        return String.format("Mark task(s) %s", taskIndexes.toString());
    }
}
