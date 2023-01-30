package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command type that the chatting bot can read.
 */
public class UnmarkCommand extends Command {

    private int unmarkNumber;

    /**
     * The constructor of this class.
     *
     * @param unmarkNumber
     */
    public UnmarkCommand(int unmarkNumber) {
        this.unmarkNumber = unmarkNumber;
    }

    /**
     * A method that gets the int variable of the class.
     *
     * @return the unmarkNumber
     */
    public int getUnmarkNumber() {
        return this.unmarkNumber;
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
        if (unmarkNumber < 1 || unmarkNumber > list.size()) {
            throw new DukeException("Sorry, this task number is invalid.");
        }
        list.get(this.unmarkNumber - 1).setStatus(false);
        store.save(list);
        ui.showMessage("OK, I've marked this task as not done yet:");
        ui.showMessage(list.get(unmarkNumber - 1).toString());
    }

    /**
     * The method to see if the programme should exit.
     *
     * @return a boolean value stating the bot should exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
