package crystal.command;

import crystal.TaskList;
import crystal.Ui;
import crystal.Storage;
public class MarkCommand extends Command {
    public int num;

    public MarkCommand(int num) {
        this.num = num;
    }
    public void execute(TaskList tasks, Ui ui,Storage storage) {
        ui.printMark(tasks, this.num);
    }

    public boolean isExit() {
        return false;
    }

}
