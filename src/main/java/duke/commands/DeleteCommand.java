package duke.commands;

import duke.Storage;
import duke.Ui;

import duke.exceptions.DukeExceptions;

import duke.tasks.TaskList;
import duke.tasks.Task;

public class DeleteCommand implements Command {
    private String input;
    public DeleteCommand(String input) {
        this.input = input;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task thisTask = taskList.deleteTask(input);
            ui.showMessage("okay, this task has been removed: " + thisTask.toString() + "\nthe list now has "
                    + taskList.getSize() + " task(s) left\n");
        } catch (DukeExceptions e) {
            ui.showError(e);
        }
    }
}