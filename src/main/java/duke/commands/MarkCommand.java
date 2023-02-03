package duke.commands;

import duke.Storage;
import duke.Ui;

import duke.exceptions.DukeExceptions;
import duke.exceptions.ForgottenArgumentException;
import duke.exceptions.InvalidIndexException;

import duke.tasks.Task;
import duke.tasks.TaskList;

public class MarkCommand implements Command {
    private String input;

    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * Marks a given Task as completed and displays confirmation message of update. If no index is given or the index
     * given is out of bounds, the error is displayed.
     *
     * @param taskList the TaskList containing the Task to be updated
     * @param ui the Ui needed to display according messages
     * @param storage the Storage used to update the save file
     * @throws ForgottenArgumentException If no index is given
     * @throws InvalidIndexException If the given index is out of bounds in TaskList
     */
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        if (input.length() < 6) {
            throw new ForgottenArgumentException();
        } else {
            int index = Integer.parseInt(input.substring(5)) - 1;
            if (index >= taskList.getSize()) {
                throw new InvalidIndexException();
            }
            Task thisTask = taskList.getTasks().get(index);
            thisTask.markDone();
            ui.showMessage("well done! you've completed this task: " + thisTask + "\n");
        }
    }
}