package crystal.command;

import crystal.TaskList;
import crystal.Ui;
import crystal.Storage;
public class DeleteCommand extends Command{

    public int num;

    public DeleteCommand(int num) {
        this.num = num;
    }
    public void execute(TaskList tasks, Ui ui,Storage storage) {
        ui.printDelete(tasks, this.num);
    }

    public boolean isExit() {
        return false;
    }
}
