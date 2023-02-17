package lele.command;

import java.io.IOException;

import lele.exception.LeleException;
import lele.storage.Storage;
import lele.task.TaskList;
import lele.ui.Ui;


/**
 * An abstract class to enforce a structure
 * for command classes.
 */
public abstract class Command {

    public abstract String execute(TaskList taskList, Ui ui, Storage storage)
            throws LeleException, IOException;

    public abstract boolean isExit();

}
