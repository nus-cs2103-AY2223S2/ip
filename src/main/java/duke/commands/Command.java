package duke.commands;
import duke.TaskList;
import duke.Storage;
import duke.Ui;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage);

    public boolean isExit(){
        return false;
    }
}

