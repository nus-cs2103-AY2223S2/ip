package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class MarkCommand extends Command {
    private String indexString;

    public MarkCommand(String commandString, String indexString) {
        super(Commands.MARK, commandString);
        this.indexString = indexString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> t = tasks.getTasks();
        int index = this.isValidIndex(this.indexString, t);

        t.get(index).markTask();

        String msgHeader = "I've marked this task as done:";

        ui.showMsg(msgHeader);
        ui.showMsg(t.get(index).toString());
    }
}
