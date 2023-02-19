package wtd.command;

import wtd.Storage;
import wtd.TaskList;
import wtd.Ui;
import wtd.exceptions.WtdException;

public abstract class Command {
    public abstract String getCommand();

    public abstract String execute(TaskList tasks, Ui ui, Storage storage) throws WtdException;

    public static boolean checkAlias(String alias) {
        throw new UnsupportedOperationException("This method is to be implemented by child classes.");
    };

    public boolean isExit() {
        return false;
    }
}
