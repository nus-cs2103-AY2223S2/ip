package duke.command;

import duke.main.DukeException;
import duke.main.Storage;
import duke.main.Tasklist;
import duke.main.Ui;

public class FindCommand extends Command{

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) {
        try {
            ui.printFoundTasks(tasklist.findTask(keyword));
        } catch (DukeException de) {
            ui.printDukeException(de);
        }
    }
}
