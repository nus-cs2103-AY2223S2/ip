package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasktype.Deadline;
import duke.tasktype.TaskList;

/**
 * The class for deadline commands.
 */
public class CreateDeadline extends Command {
    private String cmdLine;

    public CreateDeadline(String cmdLine) {
        this.cmdLine = cmdLine;
    }

    /**
     * Creates a deadline task for the user.
     *
     * @param lst the task list to be operated
     * @param ui the UI object the program is using
     * @param storage the Storage object the program is using
     * @return the response from the bot
     */
    public String operate(TaskList lst, Ui ui, Storage storage) {
        try {
            if (cmdLine.length() <= 9) {
                throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you really have this Deadline task or not?");
            }

            String task = cmdLine.substring(9);
            int pos = task.indexOf("/by");
            if (pos == -1) {
                throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you have a deadline for this task or not?");
            }

            String time = task.substring(pos + 4);
            task = task.substring(0, pos - 1);
            if (task.isEmpty()) {
                throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you really have this Deadline task or not?");
            }
            if (time.isEmpty()) {
                throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you have a deadline for this task or not?");
            }

            lst.add(new Deadline(task, time));
            String response = "";
            response += "New Deadline task is added. Roarrrrrrrrrrrrrrrrrrrrrrrrr!\n";
            response += "  " + lst.get(lst.size() - 1).toString() + "\n";
            response += "You save " + lst.size() + " tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!";
            storage.save(lst);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (StringIndexOutOfBoundsException e) {
            return "Roarrrrrrrrrrrrrrrr! I cannot add this Deadline task! Check your input format!";
        }
    }
}
