package commands;

import exceptions.DukeException;
import storage.Storage;
import storage.TaskList;
import tasks.Task;
import ui.Ui;

public class AddCommand extends Command {
    private String[]  args;
    public AddCommand(String[] args) {
        this.args = args;
    }

    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            Task t = Task.createTask(args);
            taskList.add(t);
            ui.printResponse("The following task has been added to your list: \n    " + t + "\n\n"
                            + taskList.getSizeAsString());
        } catch (DukeException e) {
            ui.printException(e);
        }

    }
}