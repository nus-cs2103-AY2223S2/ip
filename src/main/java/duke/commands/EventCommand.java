package duke.commands;

import duke.DukeException;
import duke.Events;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * A command type that the chatting bot can read.
 */
public class EventCommand extends Command {
    private String taskName;
    private String sTime;
    private String eTime;

    /**
     * The constructor of this class.
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
     * A method that gets the task name.
     *
     * @return the task name
     */
    public String getTaskName() {
        return this.taskName;
    }

    /**
     * A method that gets the starting time.
     *
     * @return the starting time
     */
    public String getStartTime() {
        return this.sTime;
    }

    /**
     * A method that gets the ending time.
     *
     * @return the ending time
     */
    public String getEndTime() {
        return this.eTime;
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
        list.add(new Events(taskName, sTime, eTime));
        store.save(list);
        ui.showMessage("Got it. I've added this task:");
        ui.showMessage(list.get(list.size() - 1).toString());
        ui.showMessage("Now you have " + list.size() + " tasks in the list.");
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
