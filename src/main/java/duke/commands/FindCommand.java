package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command type that the chatting bot can read.
 */
public class FindCommand extends Command {

    private String targetName;

    /**
     * The constructor of this class.
     *
     * @param targetName
     */
    public FindCommand(String targetName) {
        this.targetName = targetName;
    }

    /**
     * The method that includes the execution of the command.
     *
     * @param list
     * @param store
     * @param ui
     */
    @Override
    public void execute(TaskList list, Storage store, Ui ui) throws DukeException {
        ui.showMessage("Here are the matching tasks in your list:");
        if (list.size() == 0) {
            ui.showMessage("OOPS!!! Your list is empty.");
        }
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            int result = list.get(i).getName().indexOf(targetName);
            if (result >= 0) {
                System.out.print(count + 1 + ".");
                count++;
                System.out.println(list.get(i).toString());
            }
        }
        if (count == 0) {
            ui.showMessage("OOPS!!! There are no matching tasks in your list.");
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
