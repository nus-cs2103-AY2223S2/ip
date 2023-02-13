package duke.Command;

import duke.Storage;
import duke.TaskList;
import duke.UI;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) {
        return tasks.findKeyword(keyword);
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
