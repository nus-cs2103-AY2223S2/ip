import java.io.IOException;

public class ExitCommand extends Command {

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.exit();
        isExit();
        try {
            storage.store(tasks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean isExit(){
        return true;
    }
}
