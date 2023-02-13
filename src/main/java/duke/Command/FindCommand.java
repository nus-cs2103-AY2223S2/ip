package duke.Command;

import duke.Utilities.NoteList;
import duke.Utilities.Storage;
import duke.Utilities.TaskList;
import duke.Utilities.UI;

public class FindCommand extends Command {

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList tasks, NoteList notes, UI ui, Storage storage) {
        return tasks.findKeyword(keyword);
    }

    @Override
    public boolean isByeCommand() {
        return false;
    }
}
