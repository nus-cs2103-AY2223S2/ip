package lele.command;

import lele.exception.LeleException;
import lele.storage.Storage;
import lele.task.TaskList;
import lele.ui.Ui;
import java.io.IOException;

/**
 * An abstract class to enforce a structure
 * for command classes.
 */
public abstract class Command {

    public abstract void execute(TaskList taskList, Ui ui, Storage storage)
            throws LeleException, IOException;


    public abstract boolean isExit();
}
