package duke.commands;

import duke.storage.Storage;
import duke.tasks.Tasks;
import duke.ui.Ui;

/**
 * Represents the command for filtering tasks by a given date.
 */
public class DateCommand extends Command {

    private String date;

    public DateCommand(String date) {
        this.date = date;
    }

    @Override
    public void execute(Tasks tasks, Ui ui, Storage storage) {
        tasks.filterByDate(this.date);
    }
}
