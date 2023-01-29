package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.task.Task;

import java.util.ArrayList;

public class DeleteCommand extends Command {
    private String indexString;

    public DeleteCommand(String commandString, String indexString) {
        super(Commands.DELETE, commandString);
        this.indexString = indexString;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> t = tasks.getTasks();
        int index = this.isValidIndex(this.indexString, t);

        Task task = t.get(index);
        t.remove(index);

        ui.showDeleteTask(task.toString(), t.size());
    }
}
