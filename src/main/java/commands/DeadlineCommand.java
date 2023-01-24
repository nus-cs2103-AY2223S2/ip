package commands;

import storage.Storage;
import tasks.Deadline;
import tasks.TaskList;
import tasks.ToDo;
import ui.Ui;

import static ui.Ui.LS;

public class DeadlineCommand extends Command {
    private String desc, by;
    public DeadlineCommand(String desc, String by) {
        this.desc = desc;
        this.by = by;
    }
    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        Deadline t = new Deadline(this.desc, this.by);
        tl.addTask(t);
        s.addTask(t.toText());
        ui.display("Got it. I've added this task:" + LS + t + LS + tl.numTasksMsg());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
