package crystal.command;

import crystal.CrystalException;
import crystal.TaskList;
import crystal.Ui;
import crystal.Storage;
import crystal.task.Deadline;


public class DeadlineCommand extends Command{
    public String by;
    public String description;
    public DeadlineCommand(String description, String by) {
        this.description = description;
        this.by = by;
    }
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CrystalException {
        Deadline deadline = new Deadline(description, by);
        tasks.add(deadline);
        ui.printDeadline(tasks, deadline);
    }

    public boolean isExit() {
        return false;
    }
}
