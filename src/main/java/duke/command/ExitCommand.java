package duke.command;
import duke.Storage;
import duke.Ui;
import duke.TaskList;

public class ExitCommand extends Command{
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showGoodbye();
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return "Command: Exit";
    }
}
