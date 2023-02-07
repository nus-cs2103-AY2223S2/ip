package duke.command;

import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

import java.util.List;

public class FindCommand extends Command {
    String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        List<Task> filtered = tasks.findAllTasksWithKeyword(this.keyword);
        StringBuilder toPrint = new StringBuilder();
        for (int i = 0; i < filtered.size(); i++) {
            if (i != 0) {
                toPrint.append("\n");
            }
            toPrint.append(i + 1).append(": ").append(filtered.get(i));
        }
        ui.printInBanner(toPrint.toString());
    }
}
