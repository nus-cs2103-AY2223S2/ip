package duke;

/**
 * Command to terminate bot.
 */
public class CommandBye extends Command {
    @Override
    public String execute() {
        //TODO: find a way to terminate duke
        return Ui.goodbyeMessage;
    }
}
