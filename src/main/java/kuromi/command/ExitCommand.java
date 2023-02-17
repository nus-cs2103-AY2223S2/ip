package kuromi.command;

import kuromi.storage.Storage;
import kuromi.task.TaskList;
import kuromi.view.Ui;

/**
 * Command to exit the current session.
 */
public class ExitCommand extends Command {
    private CommandType command;

    /**
     * Main constructor.
     */
    public ExitCommand() {
        this.command = CommandType.bye;
    }

    /**
     * Exits the current session.
     * Asks UI to print output.
     *
     * @param tasks Task list.
     * @param ui UI of the application to interact with users.
     * @param storage Storage to update when there is an update with the task list.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        super.setExit();
        return ui.showBye(getReply());
    }

    private String getReply() {
        String msg = "Bye Melody. Hope to see you again soon!\n";
        msg += "\u2014\u2014\u2014\u2014\u2014\nNote:\n";
        msg += "You're annoying but I'll definitely miss you :D";
        return msg;
    }
}
