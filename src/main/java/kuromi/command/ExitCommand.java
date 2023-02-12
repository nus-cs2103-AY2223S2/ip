package kuromi.command;

import kuromi.storage.Storage;
import kuromi.view.Ui;
import kuromi.task.TaskList;

/**
 * Command to exit the current session.
 */
public class ExitCommand extends Command {
    private CommandType command;

    /**
     * kuromi.gui.component.MainWindow.kuromi.exceptions.KuromiException.Main constructor.
     */
    public ExitCommand() {
        this.command = CommandType.bye;
    }

    /**
     * Exit the current session.
     * Ask UI to print output.
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
        msg += "You're annoying but I'll definitely miss u :D";
        return msg;
    }
}
