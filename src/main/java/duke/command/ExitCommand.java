package duke.command;

import duke.Ui.Ui;
import duke.storage.Storage;
import duke.task.TaskList;

public class ExitCommand extends Command{

    public ExitCommand() {
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        storage.store(tasks.getDataBase());
        System.out.println("Bye. Hope to see you again soon!");
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
