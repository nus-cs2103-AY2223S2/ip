package duke.commands;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

public abstract class Command {
    public abstract boolean execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit(){
        return false;
    }
}

