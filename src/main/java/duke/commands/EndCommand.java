package duke.commands;
import duke.tasklist.TaskList;
import duke.ui.Ui;

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
