package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private final String INDEX_STRING;

    public DeleteCommand(String commandString, String INDEX_STRING) {
        super(Commands.DELETE, commandString);
        this.INDEX_STRING = INDEX_STRING;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> t = tasks.getTasks();
        int index = this.isValidIndex(this.INDEX_STRING, t);

        Task task = t.get(index);
        t.remove(index);

        ui.showDeleteTask(task.toString(), t.size());
    }
}
