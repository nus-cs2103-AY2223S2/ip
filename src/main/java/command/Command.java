package command;

import duke.Storage;
import duke.Ui;
import task.Tasklist;

/**
 * This is an interface representing a command that can be executed.
 */
public interface Command {
    /**
     This method is called to execute the command.
     @param ui The user interface to interact with the user.
     @param list The task list containing the tasks.
     @param storage The storage where the data is stored.
     */
    public void execute(Ui ui, Tasklist list, Storage storage);

    /**
     This method returns a boolean indicating if this command should cause the program to exit.
     @return true if the program should exit, false otherwise.
     */
    public boolean isExit();

}
