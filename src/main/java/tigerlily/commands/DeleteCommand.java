package tigerlily.commands;

import tigerlily.Storage;
import tigerlily.Ui;

import tigerlily.exceptions.DukeExceptions;

import tigerlily.tasks.TaskList;
import tigerlily.tasks.Task;

public class DeleteCommand implements Command {
    private String input;

    /**
     * Constructor for DeleteCommand
     * @param input the input given to delete Tasks
     */
    public DeleteCommand(String input) {
        this.input = input;
    }

    /**
     * Deletes a given Task and displays confirmation message of deletion. If there are any errors from trying to delete
     * the Task, the error is displayed.
     *
     * @param taskList the TaskList the Task is removed from
     * @param ui the Ui needed to display according messages
     * @param storage the Storage used during this session
     * @return the message after deleting a Task
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task thisTask = taskList.deleteTask(input);
            return ui.showMessage("okay, this task has been removed: " + thisTask.toString() + "\nthe list now has "
                    + taskList.getSize() + " task(s) left\n");
        } catch (DukeExceptions e) {
            return ui.showError(e);
        }
    }
}