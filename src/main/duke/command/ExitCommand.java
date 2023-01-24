package command;

import sys.Ui;
import sys.Storage;

import task.TaskList;

public class ExitCommand extends Command {

    public ExitCommand() {
        super("bye");
    }

    @Override
    public void execute(TaskList tl, Ui ui, Storage storage) {
        System.out.println("Bye. Hope to see you again soon!");
        System.exit(0);
    }
}
