package duke.command;
import duke.Storage;
import duke.Ui;
import duke.TaskList;

public class ExitCommand extends Command{

    /**
     * @inheritDoc
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage){
        ui.showGoodbye();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return "Command: Exit";
    }
}
