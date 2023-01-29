package commands;

import static ui.Ui.LS;

import storage.Storage;
import tasks.TaskList;
import tasks.ToDo;
import ui.Ui;

/**
 * Todo command to handle ToDo task logic.
 */
public class TodoCommand extends Command {
    private String desc;
    public TodoCommand(String message) {
        this.desc = message;
    }
    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        ToDo t = new ToDo(desc);
        tl.addTask(t);
        s.addTask(t.toText());
        ui.display("Got it. I've added this task:" + LS + t + LS + tl.numTasksMsg());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
