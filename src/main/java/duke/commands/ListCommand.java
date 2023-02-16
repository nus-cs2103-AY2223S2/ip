package duke.commands;

import duke.Storage;
import duke.TaskList;

/**
 * A command type that the chatting bot can read.
 */
public class ListCommand extends Command {

    /**
     * The method that includes the execution of the command.
     *
     * @param list
     * @param store
     */
    @Override
    public String execute(TaskList list, Storage store) {
        String response = "Here are the tasks in your list:\n";
        if (list.size() == 0) {
            return "Uh-oh, your list is empty.";
        }
        for (int i = 0; i < list.size(); i++) {
            response += Integer.toString(i + 1);
            response += ".";
            response += list.get(i).toString();
            if (i != list.size() - 1) {
                response += "\n";
            }
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
