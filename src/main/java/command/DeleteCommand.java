package command;

import parser.Parser;
import storage.Storage;
import task.TaskList;
import ui.Ui;

public class DeleteCommand extends Command {
    private final int index;

    public DeleteCommand(String commandContent) {
        this.index = Parser.parseInt(commandContent, "Delete Item") - 1;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.deleteTask(this.index);
    }
}
