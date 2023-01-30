package duke.command;
import duke.data.TaskList;
import duke.exception.DukeException;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.ui.Ui;

public abstract class Command {
    // use protected modifier when subclasses need this info
    // private modifier is to encapsulate data within the class itself
    protected String[] contents;
    protected Parser parser;
    protected boolean exitStatus;

    public Command(String[] contents,boolean exitStatus) {
        this.contents = contents;
        this.parser = new Parser();
        this.exitStatus = exitStatus;
    }

    public boolean isExit() {
        return this.exitStatus;
    }
    
    public abstract void execute(TaskList task,Ui ui,Storage storage) throws DukeException;
}
