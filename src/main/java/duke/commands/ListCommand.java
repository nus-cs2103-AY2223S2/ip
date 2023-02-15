package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This is a command to list out all the task in Duke.
 */
public class ListCommand extends Command {

    /**
     * Constructor of ListCommand class.
     */
    public ListCommand() {
        super();
    }

    /**
     * Checks if Duke should terminate after this command.
     *
     * @return false.
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Prints out the list of task in Duke.
     *
     * @param taskList the TaskList storing all Task.
     * @param ui the Ui for handling inputs/outputs.
     * @param storage the Storage responsible for reading/writing data.
     * @return the message shown to the user after execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.showTaskList();
    }

    /**
     * Checks if the given Object is the same as this.
     *
     * @param o the Object being compared.
     * @return true if o is an instance of this.
     */
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        return o instanceof ListCommand;
    }
}
