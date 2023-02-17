package DukeHelpfulCode.Commands;

import DukeHelpfulCode.Exceptions.NoSuchTaskException;
import DukeHelpfulCode.Exceptions.TaskAlrMarkException;
import DukeHelpfulCode.Utilities.*;

import java.io.IOException;

public abstract class Command {
    /** All commands will inherit from this abstract class
     * commands that inherit: AddCommand, DeleteCommand, ExitCommand etc
     * @return
     */

    public abstract String execute(TaskList taskList) throws TaskAlrMarkException, NoSuchTaskException;

    public abstract boolean isExit();
}
