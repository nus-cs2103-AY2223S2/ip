package command;

import duke.Storage;
import duke.Ui;
import task.Tasklist;

public class ExitCommand implements Command {
    public ExitCommand() {

    }
    @Override
    public void execute(Ui ui, Tasklist list, Storage storage) {

        System.out.println("Bye. Hope to see you again soon!");
        ui.closeDuke();
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
