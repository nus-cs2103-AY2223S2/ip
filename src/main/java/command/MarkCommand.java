package command;

import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class MarkCommand extends Command {
    private final boolean isToMark;
    private final int index;

    public MarkCommand(String commandWord, String commandContent) {
        this.isToMark = commandWord.equals("mark");
        this.index = Parser.parseInt(commandContent, "Item Marking") - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(this.index, this.isToMark);
    }
}
