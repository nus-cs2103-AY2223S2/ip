package roody.commands;

import java.util.ArrayList;

import roody.Storage;
import roody.Ui;
import roody.exceptions.RoodyException;
import roody.tasks.Task;

/**
 * Represents a command to list out tasks.
 */
public class ListCommand extends Command {
    /**
     * Creates a list command.
     */
    public ListCommand() {}
    @Override
    public String execute(ArrayList<Task> taskList, Ui ui, Storage storage) throws RoodyException {
        return ui.printList(taskList);
    }
}
