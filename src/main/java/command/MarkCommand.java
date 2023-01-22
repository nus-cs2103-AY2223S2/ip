package command;

import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

/**
 * Command for marking or unmarking a task.
 */
public class MarkCommand extends Command {
    private final boolean isToMark;
    private final int index;

    /**
     * Constructor for MarkCommand.
     * @param commandWord Whether to mark or unmark the task.
     * @param commandContent Potentially contains the id of the task to be marked.
     */
    public MarkCommand(String commandWord, String commandContent) {
        this.isToMark = commandWord.equals("mark");
        this.index = Parser.parseInt(commandContent, "Item Marking") - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(this.index, this.isToMark);
    }
}
