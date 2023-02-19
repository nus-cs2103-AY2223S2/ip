package duke.command;
import duke.storage.Storage;
import duke.task.Task;
import duke.storage.TaskList;
import duke.ui.Ui;
public class AddCommand extends Command {

    private String task;

    public AddCommand(String task) {
        this.task = task;
    }

    @Override
    public String execute(TaskList list, Ui ui) {
        assert task != null : "task must not null";
        if (list.isDuplicate(task)) {
            return ui.printDuplicateTaskMessage();
        }
        list.add(task);
        return ui.printAddMessage(list.getLast(), list);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
