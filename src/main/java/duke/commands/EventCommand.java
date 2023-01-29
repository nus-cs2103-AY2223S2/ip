package duke.commands;

import duke.DukeException;
import duke.Events;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class EventCommand extends Command {
    private String taskName;
    private String sTime;
    private String eTime;

    public EventCommand(String taskName, String sTime, String eTime) {
        this.taskName = taskName;
        this.sTime = sTime;
        this.eTime = eTime;
    }

    public String getTaskName() {
        return this.taskName;
    }

    public String getStartTime() {
        return this.sTime;
    }

    public String getEndTime() {
        return this.eTime;
    }

    @Override
    public void execute(TaskList list, Storage store, Ui ui) throws DukeException {
        list.add(new Events(taskName, sTime, eTime));
        store.save(list);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(list.get(list.size() - 1).toString());
        ui.showMessage("Now you have " + list.size() +" tasks in the list.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
