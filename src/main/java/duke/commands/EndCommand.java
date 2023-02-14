package duke.commands;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Command to terminate program.
 */
public class EndCommand extends Command {
    public EndCommand() {
        super("END");
    }

    @Override
    public String execute(TaskList tasks) {
        Ui.outro();
        return "Nice have the BEST DAY AHEAD!!!!!";
    }
}
