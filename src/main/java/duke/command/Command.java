package duke.command;

import duke.DukeException;
import duke.Storage;
import duke.TaskList;
import duke.TextUi;

/**
 * An abstract class that execute specific action
 */
public abstract class Command {

    /**
     * An abstract method to execute the command given
     * @param tasksList A TaskList class that represents task list
     * @param ui A TextUi class that represents the ui
     * @param storage A Storage class which represents the storage of file
     */
    public abstract String execute(TaskList tasksList, TextUi ui, Storage storage) throws DukeException;

    /**
     * An abstract method to indicate whether to exit the program
     */
    public abstract boolean isExit();
}
