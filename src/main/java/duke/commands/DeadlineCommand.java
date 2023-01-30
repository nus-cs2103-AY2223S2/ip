package duke.commands;

import duke.Deadlines;
import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

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
     * @param ui
     */
    @Override
    public void execute(TaskList list, Storage store, Ui ui) throws DukeException {
        list.add(new Deadlines(taskName, time));
        store.save(list);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(list.get(list.size() - 1).toString());
        ui.showMessage("Now you have " + list.size() +" tasks in the list.");
    }

    /**
     * The method to see if the programme should exit.
     *
     * @return a boolean value stating the bot should exit
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
