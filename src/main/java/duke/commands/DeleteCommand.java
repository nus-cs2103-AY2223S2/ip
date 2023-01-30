package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;
import duke.Ui;

/**
 * A command type that the chatting bot can read.
 */
public class DeleteCommand extends Command {

    private int deleteNumber;

    /**
     * The constructor of this class.
     *
     * @param deleteNumber
     */
    public DeleteCommand(int deleteNumber) {
        this.deleteNumber = deleteNumber;
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
        if (deleteNumber < 1 || deleteNumber > list.size()) {
            throw new DukeException("Sorry, this task number is invalid.");
        }
        Task removed = list.get(this.deleteNumber - 1);
        list.remove(this.deleteNumber - 1);
        store.save(list);
        ui.showMessage("Noted. I've removed this task:");
        ui.showMessage(removed.toString());
        ui.showMessage("Now you have " + list.size() +" tasks in the list.");
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
