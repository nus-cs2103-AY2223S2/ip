import com.sun.source.util.TaskListener;

public abstract class Command {

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;
    public abstract boolean isExit();
}
