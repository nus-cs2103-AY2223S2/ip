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
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showConfirmation(tasks.findKeyword(keyword));
    }
}
