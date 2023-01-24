package crystal.command;

import crystal.TaskList;
import crystal.Ui;
import crystal.Storage;
import crystal.task.Deadline;


public class DeadlineCommand extends Command{
    public String s;
    public String description;
    public DeadlineCommand(String description, String s) {
        this.description = description;
        this.s = s;
    }
    public void execute(TaskList tasks, Ui ui,Storage storage) {
        Deadline d = new Deadline(description, s);
        tasks.add(d);
        ui.printDeadline(tasks, d);

    }

    public boolean isExit() {
        return false;
    }
}
