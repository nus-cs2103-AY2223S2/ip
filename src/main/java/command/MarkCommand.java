package command;

import gui.Gui;
import parser.Parser;
import storage.Storage;
import task.TaskList;

/**
 * Command for marking a task in a task list.
 */
public class MarkCommand extends Command {
    private final boolean isToMark;
    private final int index;

    /**
     * Constructor for MarkCommand.
     * @param commandType Whether to mark or unmark the task.
     * @param commandContent Potentially contains the id of the task to be marked.
     */
    public MarkCommand(CommandType commandType, String commandContent) {
        this.isToMark = commandType == CommandType.MARK;
        this.index = Parser.parseInt(commandContent, "Item Marking") - 1;
    }

    @Override
    public void execute(TaskList taskList, Gui gui, Storage storage) {
        gui.say(taskList.markTask(this.index, this.isToMark));
    }
}
