package fea.commands;

import fea.exceptions.FeaException;
import fea.exceptions.TaskException;
import fea.storage.Storage;
import fea.tasklist.TaskList;
import fea.ui.Ui;
/**
 * MarkCommand class that implements the Command interface.
 */
public class MarkCommand implements Command {

    private static final String INVALID_TASK_NUMBER = "Please enter a valid task number!";
    private boolean toMark;
    private String fullCommand;

    /**
     * Constructor class to initialise MarkCommand.
     * @param toMark Whether the command is to mark or unmark the task.
     * @param fullCommand The string of the full command.
     */
    public MarkCommand(boolean toMark, String fullCommand) {
        this.toMark = toMark;
        this.fullCommand = fullCommand;
    }

    /**
     * Toggles a task to done or not done.
     *
     * @param tasks The current task list.
     * @param ui The ui object that handles printing to the user.
     * @param storage The storage object that handles saving to the data file.
     * @return String The string of the task to be marked.
     * @throws FeaException If there is an exception saving to the data file or invalid input.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws FeaException {
        try {
            int taskNum = Integer.parseInt(this.fullCommand.substring(toMark ? 5 : 7));
            if (taskNum > tasks.size() || taskNum < 1) {
                throw new TaskException(INVALID_TASK_NUMBER);
            }
            return tasks.markTask(taskNum, storage, ui, toMark);
        } catch (NumberFormatException | StringIndexOutOfBoundsException e) {
            throw new TaskException(INVALID_TASK_NUMBER);
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }

}
