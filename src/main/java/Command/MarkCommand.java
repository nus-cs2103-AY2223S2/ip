package Command;

import Parser.Parser;
import Storage.Storage;
import Task.TaskList;
import Ui.Ui;

public class MarkCommand extends Command {
    private final boolean isToMark;
    private final int index;

    public MarkCommand(String commandWord, String commandContent) {
        this.isToMark = commandWord.equals("mark");
        this.index = Parser.parseInt(commandContent, "Item Marking");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.markTask(this.index, this.isToMark);
    }
}
