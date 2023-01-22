package command;

import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Command for deleting a task from the task list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Constructor for DeleteCommand.
     * @param commandContent Potentially contains the index of the task to be deleted.
     */
    public DeleteCommand(String commandContent) {
        this.index = Parser.parseInt(commandContent, "Delete Item") - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(this.index);
    }
}
