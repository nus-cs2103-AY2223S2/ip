package duke.commands;

import duke.DukeException;
import duke.Storage;
import duke.Ui;
import duke.tasktype.Event;
import duke.tasktype.TaskList;

/**
 * The class for event commands.
 */
public class CreateEvent extends Command {
    private String cmdLine;

    public CreateEvent(String cmdLine) {
        this.cmdLine = cmdLine;
    }

    /**
     * Creates an event task for the user.
     *
     * @param lst the task list to be operated
     * @param ui the UI object the program is using
     * @param storage the Storage object the program is using
     * @return the response from the bot
     */
    public String operate(TaskList lst, Ui ui, Storage storage) {
        try {
            if (cmdLine.length() <= 6) {
                throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you really have this Event task or not?");
            }

            String task = cmdLine.substring(6);
            int pos1 = task.indexOf("/from");
            if (pos1 == -1) {
                throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you have a start time for this task or not?");
            }

            String time1 = task.substring(pos1 + 6);
            int pos2 = time1.indexOf("/to");
            if (pos2 == -1) {
                throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you have a end time for this task or not?");
            }

            String time2 = time1.substring(pos2 + 4);
            time1 = time1.substring(0, pos2 - 1);
            task = task.substring(0, pos1 - 1);
            if (task.isEmpty()) {
                throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you really have this Event task or not?");
            }
            if (time1.isEmpty()) {
                throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you have a start time for this task or not?");
            }
            if (time2.isEmpty()) {
                throw new DukeException("Roarrrrrrrrrrrrrrrrr! Do you have a end time for this task or not?");
            }

            lst.add(new Event(task, time1, time2));
            String response = "";
            response += "New Event task is added. Roarrrrrrrrrrrrrrrrrrrrrrrrr!\n";
            response += "  " + lst.get(lst.size() - 1).toString() + "\n";
            response += "You save " + lst.size() + " tasks in the list. Roarrrrrrrrrrrrrrrrrrrr!";
            storage.save(lst);
            return response;
        } catch (DukeException e) {
            return e.getMessage();
        } catch (StringIndexOutOfBoundsException e) {
            return "Roarrrrrrrrrrrrrrrr! I cannot add this Event task! Check your input format!";
        }
    }
}
