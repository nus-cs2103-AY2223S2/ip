package duke.command;
import duke.Storage;
import duke.Ui;
import duke.TaskList;

public class ListCommand extends Command{

    /**
     * @inheritDoc
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showTasks(tasks);
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "Command: List tasks";
    }
}
