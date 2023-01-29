package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class MarkCommand extends Command {
    private final String INDEX_STRING;

    public MarkCommand(String commandString, String indexString) {
        super(AvailableCommands.MARK, commandString);
        INDEX_STRING = indexString;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {
        ArrayList<Task> tasks = taskList.getTasks();
        int index = isValidIndex(INDEX_STRING, tasks);

        tasks.get(index).markTask();

        String msgHeader = "I've marked this task as done:";

        ui.showMsg(msgHeader);
        ui.showMsg(tasks.get(index).toString());
    }
}
