package duke.commands;

import duke.*;

/**
 * A command type that the chatting bot can read.
 */
public class DeadlineCommand extends Command {

    private String taskName;
    private String time;

    /**
     * The constructor of this class.
     *
     * @param taskName
     * @param time
     */
    public DeadlineCommand(String taskName, String time) {
        this.taskName = taskName;
        this.time = time;
    }

    /**
     * A method that gets the task name.
     *
     * @return the task name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * A method that gets the time of the deadline.
     *
     * @return the time of the deadline
     */
    public String getTime() {
        return this.time;
    }

    /**
     * The method that includes the execution of the command.
     *
     * @param list
     * @param store
     */
    @Override
    public String execute(TaskList list, Storage store) throws DukeException {
        Deadlines newDeadline = new Deadlines(taskName, time);
        if (list.isExist(newDeadline)) {
            return "OOPS!!! The task is already in the list.";
        }
        list.add(newDeadline);
        store.save(list);
        String response = "Got it. I've added this task:\n";
        response += list.get(list.size() - 1).toString();
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
