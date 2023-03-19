package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * A command type that the chatting bot can read.
 */
public class UnmarkCommand extends Command {

    private int unmarkNumber;

    /**
     * Constructs this class.
     *
     * @param unmarkNumber
     */
    public UnmarkCommand(int unmarkNumber) {
        this.unmarkNumber = unmarkNumber;
    }

    /**
     * Gets the int variable of the class.
     *
     * @return the unmarkNumber
     */
    public int getUnmarkNumber() {
        return this.unmarkNumber;
    }

    /**
     * Executes the command.
     *
     * @param list
     * @param store
     */
    @Override
    public String execute(TaskList list, Storage store) {
        if (unmarkNumber < 1 || unmarkNumber > list.size()) {
            return "This task number is invalid. Use 'list' to check how many tasks you have.";
        }
        list.stream().filter(x -> list.indexOf(x) == unmarkNumber - 1).forEach(y -> y.setStatus(false));
        store.save(list);
        String response = "Okay, I've marked this task as not done yet:\n";
        response += list.get(unmarkNumber - 1).toString();
        return response;
    }

    /**
     * Checks if the programme should exit.
     *
     * @return a boolean value stating the bot should not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
