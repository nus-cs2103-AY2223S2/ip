package DukeHelpfulCode.Commands;

import DukeHelpfulCode.Exceptions.NoSuchTaskException;
import DukeHelpfulCode.Exceptions.TaskAlrMarkException;
import DukeHelpfulCode.Utilities.*;
import DukeHelpfulCode.Tasks.*;

import java.io.IOException;

public abstract class Command {
    /** All commands will inherit from this abstract class
     * commands that inherit: AddCommand, DeleteCommand, ExitCommand etc
     */

    public abstract void execute(TaskList taskList, UI ui, Storage storage) throws TaskAlrMarkException, NoSuchTaskException, IOException;

    public abstract boolean isExit();
}
