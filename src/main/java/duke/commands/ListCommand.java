package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command type that the chatting bot can read.
 */
public class ListCommand extends Command {

    /**
     * The method that includes the execution of the command.
     *
     * @param list
     * @param store
     * @param ui
     */
    @Override
    public void execute(TaskList list, Storage store, Ui ui) {
        ui.showMessage("Here are the tasks in your list:");
        if (list.size() == 0) {
            ui.showMessage("OOPS!!! Your list is empty.");
        }
        for (int i = 0; i < list.size(); i++) {
            System.out.print(i + 1 + ".");
            System.out.println(list.get(i).toString());
        }
    }

    /**
     * The method to see if the programme should exit.
     *
     * @return a boolean value stating the bot should not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
