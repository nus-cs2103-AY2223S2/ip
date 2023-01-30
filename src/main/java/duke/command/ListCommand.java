package duke.command;
import duke.Storage;
import duke.Ui;
import duke.TaskList;

public class ListCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showTasks(tasks);
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public String toString() {
        return "Command: List tasks";
    }
}
