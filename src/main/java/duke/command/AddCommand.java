package duke.command;
import duke.storage.Storage;
import duke.task.Task;
import duke.storage.TaskList;
import duke.ui.Ui;
public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList list, Ui ui) {
        list.add(task);
        return ui.printAddMessage(task, list);
    }
}
