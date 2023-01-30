package command;

import gui.Gui;
import parser.Parser;
import storage.Storage;
import task.TaskList;

/**
 * Command for deleting a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructor for DeleteCommand.
     * @param commandContent Potentially contains the id of the task being deleted.
     */
    public DeleteCommand(String commandContent) {
        this.index = Parser.parseInt(commandContent, "Delete Item") - 1;
    }

    @Override
    public void execute(TaskList taskList, Gui gui, Storage storage) {
        gui.say(taskList.deleteTask(this.index));
    }
}
