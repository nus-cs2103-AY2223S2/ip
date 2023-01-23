package duke.command;

import duke.TaskList;
import duke.Ui;
import duke.exception.DukeException;

/** A representation of the "find" command in Duke. */
public class FindCommand extends Command {

    private final String NAME = "find";
    private String keyword;

    /**
     * Initializes a find command with a given keyword.
     * 
     * @param keyword The keyword to find tasks with
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui) throws DukeException {
        taskList.findTasks(keyword);
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        } else if (obj.getClass() != this.getClass()) {
            return false;
        }
        FindCommand cmd = (FindCommand) obj;
        return this.keyword == cmd.keyword;
    }
}
