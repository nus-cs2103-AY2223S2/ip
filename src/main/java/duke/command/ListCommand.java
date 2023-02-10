package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * The ListCommand class encapsulates the variables and methods related to List commands.
 */
public class ListCommand extends Command {
    public static final String LIST_COMMAND = "list";

    public ListCommand() {
        super(LIST_COMMAND);
    }

    @Override
    public void execute(TaskList lst, Ui ui) {
        ui.showList(lst);
    }
}
