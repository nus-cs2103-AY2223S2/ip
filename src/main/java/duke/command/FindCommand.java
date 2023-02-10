package duke.command;

import duke.DukeException;
import duke.TaskList;
import duke.Ui;

/**
 * The FindCommand class encapsulates the variables and methods related to Find commands.
 */
public class FindCommand extends Command {
    public static final String FIND_COMMAND = "find";
    private final String keyword;

    public FindCommand(String keyword) {
        super(FIND_COMMAND);
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList lst, Ui ui) throws DukeException {
        ui.showLine();
        lst.printMatchingList(this.keyword);
        ui.showLine();
    }
}
