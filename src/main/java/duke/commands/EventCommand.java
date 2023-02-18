package duke.commands;

import static duke.ui.Ui.LS;

import duke.storage.Storage;
import duke.tasks.Event;
import duke.tasks.TaskList;
import duke.ui.Ui;

/**
 * Event command to handle Event task logic.
 */
public class EventCommand extends Command {
    private String desc;
    private String from;
    private String to;

    /**
     * Constructor to create EventCommand object.
     */
    public EventCommand(String desc, String from, String to) {
        this.desc = desc;
        this.from = from;
        this.to = to;
    }
    @Override
    public void execute(TaskList tl, Ui ui, Storage s) {
        Event t = new Event(this.desc, this.from, this.to);
        if (tl.isDuplicate(t)) {
            handleDuplicate(ui);
        } else {
            tl.addTask(t);
            s.addTask(t.toText());
            ui.display("Got it. I've added this task:" + LS + t + LS + tl.numTasksMsg());
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}
