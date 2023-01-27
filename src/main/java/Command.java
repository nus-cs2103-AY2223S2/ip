import java.io.IOException;

public abstract class Command {
    private String commandType;
    public void execute(TaskList taskList, Ui ui, Storage storage) throws IOException {}
    public boolean isExitCommand() {
        return false;
    }
}
