package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        StringBuilder toPrint = new StringBuilder();
        for (int i = 0; i < tasks.getSize(); i++) {
            if (i != 0) {
                toPrint.append("\n");
            }
            toPrint.append(i + 1).append(": ").append(tasks.get(i));
        }
        ui.printInBanner(toPrint.toString());
    }
}
