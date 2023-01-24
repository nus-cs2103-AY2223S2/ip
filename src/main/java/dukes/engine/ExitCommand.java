package dukes.engine;

public class ExitCommand extends Command {

    public ExitCommand(boolean isExit, boolean isValid,
                       String header, String body) {
        super(isExit, isValid, header, body);
    }

    public void execute(TaskList tasks, UI ui, Storage storage) {
        // do nothing
    }

}
