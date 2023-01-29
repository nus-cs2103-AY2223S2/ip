package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class UnmarkCommand extends Command {
    private final String INDEX_STRING;

    public UnmarkCommand(String commandString, String indexString) {
        super(AvailableCommands.UNMARK, commandString);
        INDEX_STRING = indexString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> t = tasks.getTasks();
        int index = isValidIndex(INDEX_STRING, t);

        t.get(index).unmarkTask();

        String msgHeader = "I've unmarked this task as not done:";

        ui.showMsg(msgHeader);
        ui.showMsg(t.get(index).toString());
    }
}
