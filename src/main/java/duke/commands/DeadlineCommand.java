package duke.commands;

import duke.Deadlines;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class DeadlineCommand extends Command {

    private String taskName;
    private String time;

    public DeadlineCommand(String taskName, String time) {
        this.taskName = taskName;
        this.time = time;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getTime() {
        return this.time;
    }

    @Override
    public void execute(TaskList list, Storage store, Ui ui) throws DukeException {
        list.add(new Deadlines(taskName, time));
        store.save(list);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(list.get(list.size() - 1).toString());
        ui.showMessage("Now you have " + list.size() + " tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
