import java.io.IOException;

public class EndCommand extends Command {
    public EndCommand(){}

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        try {
            storage.writeToFile(tasks.toTxtString());
        } catch(IOException e) {
            System.out.println("Error during saving");
        }
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
