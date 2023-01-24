package command;

import sys.Ui;
import sys.Storage;

import task.TaskList;

public class ListCommand extends Command {
    public ListCommand() {
        super("list");
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        System.out.println(tl.toString());
    }
}
