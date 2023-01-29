package duke.command;

import duke.TaskList;
import duke.Ui;

/**
 * A Command subclass for the list command.
 */
public class ListCommand extends Command {

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj instanceof ListCommand) {
            return true;
        }
        return false;
    }

    @Override
    public void execute(Ui ui, TaskList list, String command) {
        StringBuilder output = new StringBuilder("Your current task list:\n");
        for (int i = 0; i < list.getSize(); i++) {
            output.append("\t").append(i + 1).append(". ").append(list.getTask(i).formatTask()).append("\n");
        }
        ui.pixlPrint(output.toString());
    }
}
