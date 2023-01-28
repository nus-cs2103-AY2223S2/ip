package panav.command;

import panav.storage.Storage;
import panav.task.TaskList;
import panav.ui.Ui;

public class ExitCommand extends Command {

    public static final String COMMAND_WORD = "exit";

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println("Bye. Hope to see you again soon!");
        ui.showLine();
        System.exit(0);
    }

    @Override
    public boolean isExit() {
        return true;
    }

    @Override
    public String toString() {
        return ExitCommand.COMMAND_WORD;
    }

}
