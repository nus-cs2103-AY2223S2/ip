package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;

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
     */
    @Override
    public String execute(TaskList list, Storage store) throws DukeException {
        String response = "Here are the matching tasks in your list:\n";
        if (list.size() == 0) {
            response += "OOPS!!! Your list is empty.";
            return response;
        }
        int count = 0;
        for (int i = 0; i < list.size(); i++) {
            int result = list.get(i).getName().indexOf(targetName);
            if (result >= 0) {
                response += Integer.toString(count + 1);
                response += ".";
                count++;
                response += list.get(i).toString();
                if (i != list.size() - 1) {
                    response += "\n";
                }
            }
        }
        if (count == 0) {
            response += "OOPS!!! There are no matching tasks in your list.";
        }
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
