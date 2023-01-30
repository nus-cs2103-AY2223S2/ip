package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command type that the chatting bot can read.
 */
public class MarkCommand extends Command {

    private int markNumber;

    /**
     * The constructor of this class.
     *
     * @param markNumber
     */
    public MarkCommand(int markNumber) {
        this.markNumber = markNumber;
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
        if (markNumber < 1 || markNumber > list.size()) {
            throw new DukeException("Sorry, this task number is invalid.");
        }
        list.get(this.markNumber - 1).setStatus(true);
        store.save(list);
        ui.showMessage("Nice! I've marked this task as done:");
        ui.showMessage(list.get(markNumber - 1).toString());
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
