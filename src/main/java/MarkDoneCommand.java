import java.io.FileNotFoundException;
import java.io.IOException;

public class MarkDoneCommand extends Command{

    protected int index;

    public MarkDoneCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage)throws FileNotFoundException,
            IllegalArgumentException, DukeException, DirectoryNotFoundException, IOException {
        Task marked = tasks.mark(index);
        ui.mark(marked);
        storage.write(tasks);
    }

    public boolean isExit() {
        return false;
    }

}
