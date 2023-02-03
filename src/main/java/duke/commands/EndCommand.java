package duke.commands;
import duke.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Command to terminate program.
 */
public class EndCommand extends Command{
    public EndCommand(){
        super("END");
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.outro();
        return;
    }
}
