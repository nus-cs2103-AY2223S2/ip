package duke.commands;

import duke.Deadlines;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;

/**
 * A command type that the chatting bot can read.
 */
public class DeadlineCommand extends Command {

    private String taskName;
    private String time;

    /**
     * Constructs this class.
     *
     * @param taskName
     * @param time
     */
    public DeadlineCommand(String taskName, String time) {
        this.taskName = taskName;
        this.time = time;
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
     * Gets the time of the deadline.
     *
     * @return the time of the deadline
     */
    public String getTime() {
        return this.time;
    }

    /**
     * Executes the command.
     *
     * @param list
     * @param store
     */
    @Override
    public String execute(TaskList list, Storage store) throws DukeException {
        Deadlines newDeadline = null;
        try {
            newDeadline = new Deadlines(taskName, time);
        } catch (DukeException e) {
            return e.getMessage();
        }
        if (list.isExist(newDeadline)) {
            return "Ah, the task is already in the list.";
        }
        list.add(newDeadline);
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
