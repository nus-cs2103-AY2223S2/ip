import java.io.IOException;

public abstract class Command {

    public void execute(TaskList taskList, Ui ui, Storage storage) throws DukeException {};

    public boolean isExit() {
        return false;
    }

}
