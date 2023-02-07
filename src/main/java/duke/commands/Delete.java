package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasktype.TaskList;

/**
 * The class for the delete commands.
 */
public class Delete extends Command {
    private String cmdLine;

    public Delete(String cmdLine) {
        this.cmdLine = cmdLine;
    }

    /**
     * Deletes the task with the index given by the user.
     *
     * @param lst the task list to be operated
     * @param ui the UI object the program is using
     * @param storage the Storage object the program is using
     * @return the response from the bot
     */
    public String operate(TaskList lst, Ui ui, Storage storage) {
        try {
            if (cmdLine.length() <= 7) {
                throw new DukeException("Roarrrrrrrrrrrrrrrrrrr! Do you want to delete any task or not?");
            }

            int i = Integer.parseInt(cmdLine.substring(7));
            String response = "";
            response += "Fine! This task is deleted. Roarrrrrrrrrrrrrr!\n";
            response += "  " + lst.get(i - 1).toString() + "\n";
            lst.remove(i - 1);
            response += "You save " + lst.size() + " tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!\n";
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
