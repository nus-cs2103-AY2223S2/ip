package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

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
     */
    @Override
    public String execute(TaskList list, Storage store) throws DukeException {
        if (unmarkNumber < 1 || unmarkNumber > list.size()) {
            throw new DukeException("Sorry, this task number is invalid.");
        }
        list.stream().filter(x -> list.indexOf(x) == unmarkNumber - 1).forEach(y -> y.setStatus(false));
        store.save(list);
        String response = "Nice! I've marked this task as not done yet:\n";
        response += list.get(unmarkNumber - 1).toString();
        return response;
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
