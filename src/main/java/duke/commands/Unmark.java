package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasktype.TaskList;

/**
 * The class for the unmark commands.
 */
public class Unmark extends Command {
    private String cmdLine;

    public Unmark(String cmdLine) {
        this.cmdLine = cmdLine;
    }

    /**
     * Unmarks the task with the index given by the user.
     *
     * @param lst the task list to be operated
     * @param ui the UI object the program is using
     * @param storage the Storage object the program is using
     * @return the response from the bot
     */
    public String operate(TaskList lst, Ui ui, Storage storage) {
        try {
            if (cmdLine.length() <= 7) {
                throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you want to unmark any task or not?");
            }

            int i = Integer.parseInt(cmdLine.substring(7));
            lst.get(i - 1).unmark();
            String response = "";
            response += "Roarrrrrrrrrrrrrr! You said you did not finish that? Fine! Unmarked!\n";
            response += "  " + lst.get(i - 1).toString() + "\n";
            storage.save(lst);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (NumberFormatException e) {
            return "Roarrrrrrrrrrrrrrrrrrrr! I cannot identify that task as it is not an integer!";
        } catch (IndexOutOfBoundsException e) {
            return "Roarrrrrrrrrrrrrrrrrrrr! You did not add that many tasks in the list!";
        }
    }
}
