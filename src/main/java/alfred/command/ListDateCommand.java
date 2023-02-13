package alfred.command;

import java.time.LocalDate;

import alfred.exceptions.AlfredException;
import alfred.storage.Storage;
import alfred.task.TaskList;
import alfred.ui.Ui;

/**
 * Represents a list date command when a user wishes to list all the tasks associated to a date.
 */
public class ListDateCommand extends Command {

    private LocalDate date;

    /**
     * Constructs a list date command given a date.
     * @param date The date of tasks that we are interested in.
     */
    public ListDateCommand(LocalDate date) {
        this.date = date;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException {
        int itemIndex = 1;
        String initial = String.format("Here are your pending tasks on %s: \n", date);
        StringBuilder output = new StringBuilder(initial);
        if (tasks.isEmpty()) {
            return ui.getCommandMessage("Woohoo! You have no pending tasks\n");
        }
        assert tasks.getSize() > 0 : "tasks should not be empty";
        String itemDateList = tasks.getList(date);
        return ui.getCommandMessage(output.append(itemDateList).toString());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
