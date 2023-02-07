package commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

public class ListCommand extends Command {
    public ListCommand() {
        super(CommandType.LIST);
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        StringBuilder sb = new StringBuilder();
        sb.append("Here are the tasks in your list:\n\t ");
        sb.append(list.toString());
        ui.printOutput(sb.toString());
    }
}
