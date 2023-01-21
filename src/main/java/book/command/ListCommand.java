package book.command;

import book.Storage;
import book.TaskList;
import book.Ui;

public class ListCommand extends Command {
    @Override
    public void execute(Storage storage, TaskList list, Ui ui) {
        ui.showList(list);
        ui.showTaskListSize(list);
    }
}
