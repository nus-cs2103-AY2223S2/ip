package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasktype.TaskList;

/**
 * The class for the list commands.
 */
public class List extends Command {
    public List() {
    }

    /**
     * Shows the list
     *
     * @param lst the task list to be operated
     * @param ui the UI object the program is using
     * @param storage the Storage object the program is using
     * @return the response from the bot
     */
    public String operate(TaskList lst, Ui ui, Storage storage) {
        try {
            if (lst.size() == 0) {
                throw new DukeException("Roarrrrrrrrrrrrrrrrr! You did not add anything in the list!");
            }

            String response = "";
            response += "Roarrrrrrrrrrrrrrr! Task list shown below!\n";
            for (int i = 1; i <= lst.size(); ++i) {
                response += i + "." + lst.get(i - 1).toString() + "\n";
            }
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        }
    }
}
