package kuromi.command;

import kuromi.Storage;
import kuromi.Ui;
import kuromi.task.TaskList;

/**
 * Command to exit the current session.
 */
public class ExitCommand extends Command {
    private CommandType command;

    /**
     * kuromi.MainWindow.kuromi.KuromiException.Main constructor.
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
        String msg = "Bye. Hope to see you again soon!\n";
        return msg;
    }
}
