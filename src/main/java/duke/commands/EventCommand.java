package duke.commands;

import duke.DukeException;
import duke.Events;
import duke.Storage;
import duke.TaskList;

/**
 * A command type that the chatting bot can read.
 */
public class EventCommand extends Command {
    private String taskName;
    private String sTime;
    private String eTime;

    /**
     * Constructs this class.
     *
     * @param taskName
     * @param sTime
     * @param eTime
     */
    public EventCommand(String taskName, String sTime, String eTime) {
        this.taskName = taskName;
        this.sTime = sTime;
        this.eTime = eTime;
    }

    /**
     * Gets the task name.
     *
     * @return the task name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * Gets the starting time.
     *
     * @return the starting time
     */
    public String getStartTime() {
        return this.sTime;
    }

    /**
     * Gets the ending time.
     *
     * @return the ending time
     */
    public String getEndTime() {
        return this.eTime;
    }

    /**
     * Executes the command.
     *
     * @param list
     * @param store
     */
    @Override
    public String execute(TaskList list, Storage store) throws DukeException {
        Events newEvent = null;
        try {
            newEvent = new Events(taskName, sTime, eTime);
        } catch (DukeException e) {
            return e.getMessage();
        }
        if (list.isExist(newEvent)) {
            return "Ah, the task is already in the list.";
        }
        list.add(newEvent);
        store.save(list);
        String response = "Okay. I've added this task:\n";
        response += list.get(list.size() - 1).toString();
        response += "\nNow you have " + list.size() + " tasks in the list.";
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
