package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that finds tasks with a keyword when executed.
 */
public class FindCommand extends Command {
    private String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    public FindCommand() {
        super();
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        return ui.showFoundTasks(tasks.findKeywordTasks(keyword));
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String commandHelp() {
        return "Showing help for command: find\n"
                + Ui.showSepLine()
                + "Finds all tasks containing the specified keyword\n"
                + Ui.showSepLine()
                + "Usage:\n"
                + "find [(String) KEYWORD]\n\n"
                + "Example:\n"
                + "find CS2103T\n"
                + Ui.showSepLine();
    }

    @Override
    public String toString() {
        return "Command: Find tasks containing \"" + keyword + "\".";
    }
}
