package tigerlily.commands;

import tigerlily.Storage;
import tigerlily.Ui;

import tigerlily.exceptions.DukeExceptions;
import tigerlily.exceptions.ForgottenArgumentException;
import tigerlily.exceptions.InvalidIndexException;

import tigerlily.tasks.Task;
import tigerlily.tasks.TaskList;

public class UnmarkCommand implements Command {
    private String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    /**
     * Marks a given Task as uncompleted and displays confirmation message of update. If no index is given or the index
     * given is out of bounds, the error is displayed.
     *
     * @param taskList the TaskList containing the Task to be updated
     * @param ui the Ui needed to display according messages
     * @param storage the Storage used to update the save file
     * @return the message after marking a Task as uncompleted
     * @throws ForgottenArgumentException If no index is given
     * @throws InvalidIndexException If the given index is out of bounds in TaskList
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        if (input.length() < 8) {
            throw new ForgottenArgumentException();
        } else {
            int index = Integer.parseInt(input.substring(7)) - 1;
            assert index > 0 : "Invalid index input";
            if (index >= taskList.getSize()) {
                throw new InvalidIndexException();
            }
            Task thisTask = taskList.getTasks().get(index);
            thisTask.unmarkDone();
            return ui.showMessage("oops...this task is now marked as not done yet: " + thisTask.toString() + "\n");
        }
    }
}