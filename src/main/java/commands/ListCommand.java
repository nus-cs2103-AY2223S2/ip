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
        for (int i = 0; i < list.getSize(); i++) {
            int count = i + 1;
            String res = count + "." + list.getTask(i).toString();
            if (i != list.getSize() - 1) {
                res += "\n\t ";
            }
            sb.append(res);
        }
        ui.printOutput(sb.toString());
    }
}
