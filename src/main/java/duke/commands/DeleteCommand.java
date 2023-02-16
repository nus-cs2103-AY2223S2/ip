package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Task;
import duke.TaskList;

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
     */
    @Override
    public String execute(TaskList list, Storage store) {
        if (deleteNumber < 1 || deleteNumber > list.size()) {
            return "This task number is invalid. Use 'list' to check how many tasks you have.";
        }
        Task removed = list.get(this.deleteNumber - 1);
        list.remove(this.deleteNumber - 1);
        store.save(list);
        String response = "Okay. I've removed this task:\n";
        response += removed.toString();
        response += "\nNow you have " + list.size() + " tasks in the list.";
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
