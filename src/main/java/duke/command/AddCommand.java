package duke.command;

import duke.Storage;
import duke.Task;
import duke.Tasklist;
import duke.Ui;

public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(Tasklist tasklist) throws Exception {
        tasklist.add(this.task);
        Storage.save(tasklist);
        return Ui.showAdd(this.task) + "\n" + Ui.showTasklistSize(tasklist);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
