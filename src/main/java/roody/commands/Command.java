package roody.commands;

import java.util.ArrayList;

import roody.Storage;
import roody.Ui;
import roody.exceptions.RoodyException;
import roody.tasks.Task;

/**
 * Represents an executable command.
 */
public abstract class Command {
    public abstract String execute(ArrayList<Task> taskList, Ui ui, Storage storage) throws RoodyException;
}
