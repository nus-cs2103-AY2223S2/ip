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
     * @param ui Ui object that handles all Ui actions.
     * @param storage Storage object that handles all Storage actions.
     * @throws DukeException Throws exception from the methods called in this method.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        String taskString = "";
        for (String taskIndex: taskIndexes) {
            try {
                taskString = String.format("%s%s\n", taskString,
                        tasks.markTask(Integer.parseInt(taskIndex) - 1));
            } catch (NumberFormatException e) {
                System.out.println("Invalid task index, skipping the marking of this task");
            }
        }
        storage.saveTasks(tasks);
        return ui.getMarkOutput(taskString);
    }
}
