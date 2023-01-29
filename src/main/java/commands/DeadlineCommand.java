package commands;

import static ui.Ui.LS;

import storage.Storage;
import tasks.Deadline;
import tasks.TaskList;
import ui.Ui;

/**
 * Deadline command to handle Deadline task logic.
 */
public class DeadlineCommand extends Command {
    private String desc;
    private String by;

    /**
     * Constructor to create DeadlineCommand object.
     */
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
