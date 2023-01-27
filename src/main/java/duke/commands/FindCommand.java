package duke.commands;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;

public class FindCommand extends Command {
    public static final String COMMAND_WORD = "find";
    protected String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public boolean isExit() {
        return false;
    }

    @Override
    public void execute(TaskList taskList, Storage storage) {
        try {
            taskList.search(keyword);
        } catch (DukeException e) {
            System.out.println(e);
        }
    }
}
