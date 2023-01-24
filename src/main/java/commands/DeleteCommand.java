package commands;

import storage.Storage;
import tasks.TaskList;
import ui.Ui;

import static ui.Ui.LS;

public class DeleteCommand extends Command {
    private int index;
    public DeleteCommand(String message) {
        index = Integer.parseInt(message) - 1;
    }
    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        String taskText = tl.toText(this.index);
        tl.removeTask(this.index);
        s.deleteTask(taskText);
        ui.display("Noted. I've removed this task:" + LS + tl.toString(this.index) + LS + tl.numTasksMsg());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
