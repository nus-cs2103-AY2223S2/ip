import java.io.FileNotFoundException;
import java.io.IOException;

public abstract class Command {
    public abstract void execute(TaskList tasks, Ui ui, Storage storage)throws FileNotFoundException,IllegalArgumentException, DukeException, DirectoryNotFoundException, IOException;
    public abstract boolean isExit();
}
