package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Todos;
import duke.Ui;

public class TodoCommand extends Command {

    private String taskName;

    public TodoCommand(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskName() {
        return this.taskName;
    }

    @Override
    public void execute(TaskList list, Storage store, Ui ui) throws DukeException {
        list.add(new Todos((taskName)));
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
