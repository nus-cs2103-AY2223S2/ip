package duke.commands;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * This is a command to only list out task that contains the keyword.
 */
public class FindCommand extends Command {
    private String keyword;

    /**
     * Constructor for FindCommand class.
     *
     * @param keyword the keyword checked when filtering through tasks.
     */
    public FindCommand(String keyword) {
        super();
        this.keyword = keyword;
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
     * list out the tasks that contains keyword.
     *
     * @param taskList the TaskList containing all the task.
     * @param ui the Ui responsible for interacting with the user.
     * @param storage the Storage responsible for reading and writing data.
     * @return the message shown to the user after execution.
     */
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return taskList.findTask(this.keyword);
    }

    /**
     * Checks if the given Object equals to this.
     *
     * @param o the Object being checked.
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
