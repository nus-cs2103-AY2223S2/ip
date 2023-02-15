package duke.command;
import duke.*;
public abstract class Command { 
    abstract public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
}
