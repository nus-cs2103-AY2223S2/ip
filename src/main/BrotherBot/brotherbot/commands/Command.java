package brotherbot.commands;
import brotherbot.storage.TaskList;
import brotherbot.ui.Ui;


public abstract class Command {
    protected String input;
    public boolean isExit = false;

    public Command(String input) {
        this.input = input;
    }
    public abstract void execute(TaskList storage, Ui ui);
}
