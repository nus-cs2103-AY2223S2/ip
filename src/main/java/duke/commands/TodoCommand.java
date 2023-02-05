package duke.commands;

import static duke.ui.Ui.LS;

import duke.storage.Storage;
import duke.tasks.TaskList;
import duke.tasks.ToDo;
import duke.ui.Ui;

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
