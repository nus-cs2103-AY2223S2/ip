package duke.commands;

import duke.Storage;
import duke.Ui;
import duke.exceptions.DukeExceptions;
import duke.exceptions.ForgottenArgumentException;
import duke.exceptions.InvalidIndexException;
import duke.tasks.Task;
import duke.tasks.TaskList;

public class UnmarkCommand implements Command {
    private String input;

    public UnmarkCommand(String input) {
        this.input = input;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeExceptions {
        if(input.length() < 8) {
            throw new ForgottenArgumentException();
        } else {
            int index = Integer.parseInt(input.substring(7)) - 1;
            if (index >= taskList.getSize()) {
                throw new InvalidIndexException();
            }
            Task thisTask = taskList.getTasks().get(index);
            thisTask.unmarkDone();
            ui.showMessage("oops...this task is now marked as not done yet: " + thisTask.toString() + "\n");
        }
    }
}