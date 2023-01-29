package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private String indexString;

    public UnmarkCommand(String commandString, String indexString) {
        super(Commands.UNMARK, commandString);
        this.indexString = indexString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> t = tasks.getTasks();
        int index = this.isValidIndex(this.indexString, t);

        t.get(index).unmarkTask();

        String msgHeader = "I've unmarked this task as not done:";

        ui.showMsg(msgHeader);
        ui.showMsg(t.get(index).toString());
    }
}
