package commands;

import static ui.Ui.LS;

import storage.Storage;
import tasks.Event;
import tasks.TaskList;
import ui.Ui;

public class EventCommand extends Command {
    private String desc;
    private String from;
    private String to;

    public EventCommand(String desc, String from, String to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }
    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        Event t = new Event(this.desc, this.from, this.to);
        tl.addTask(t);
        s.addTask(t.toText());
        ui.display("Got it. I've added this task:" + LS + t + LS + tl.numTasksMsg());
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
