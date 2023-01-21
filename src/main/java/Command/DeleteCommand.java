package Command;

import Parser.Parser;
import Storage.Storage;
import Task.TaskList;
import Ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String commandContent) {
        this.index = Parser.parseInt(commandContent, "Delete Item");
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(this.index);
    }
}
