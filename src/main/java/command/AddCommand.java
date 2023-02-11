package command;

import duke.Storage;
import duke.Ui;
import task.Task;
import task.Tasklist;

public class AddCommand implements Command {
    private Task newTask;
    public AddCommand(Task newTask) {
        this.newTask = newTask;
    }

    @Override
    public void execute(Ui ui, Tasklist list, Storage storage) {
        list.add(this.newTask);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
