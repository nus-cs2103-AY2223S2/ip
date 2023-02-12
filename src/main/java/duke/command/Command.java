package duke.command;
import duke.*;
public abstract class Command { 
    abstract public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
