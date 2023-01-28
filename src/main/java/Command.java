import java.io.IOException;

abstract class Command {
    boolean isBye() {
        return false;
    }
    abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException, IOException;
}