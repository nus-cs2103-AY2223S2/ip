package lulu.command;

import lulu.TaskList;
import lulu.UI;
import lulu.Storage;
public class ListCommand extends Command {
    public ListCommand() {}

    /**
     * This method lists the tasks in tasks upon execution.
     *
     * @param tasks the TaskList to be listed
     * @param ui the UI that displays messages
     * @param storage the Storage is not relevant in this command
     */
    @Override
    public void execute(TaskList tasks, UI ui, Storage storage) {
        ui.showLine();
        ui.listText();
        int length = tasks.getSize();
        for (int i = 0; i < length; i++) {
            System.out.print(i+1);
            System.out.println(". " + tasks.get(i));
        }
        ui.showLine();
    }
}
