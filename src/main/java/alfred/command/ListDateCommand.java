package alfred.command;

import alfred.task.TaskList;
import alfred.ui.Ui;
import alfred.storage.Storage;
import alfred.exceptions.AlfredException;
import java.time.LocalDate;

public class ListDateCommand extends Command {

    private LocalDate date;
    public ListDateCommand(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws AlfredException {
        int itemIndex = 1;
        String initial = String.format("Here are your pending tasks on %s: \n", date);
        StringBuilder output = new StringBuilder(initial);
        if (tasks.isEmpty()) {
            ui.displayCommand("Woohoo! You have no pending tasks\n");
            return;
        }
        String itemDateList = tasks.getList(date);
        ui.displayCommand(output.append(itemDateList).toString());
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
