package james.command;
import james.jamesbot.Storage;
import james.jamesbot.Ui;
import james.task.TaskList;

/**
 * Exits the program.
 */
public class ExitCommand extends Command {
    public static final String COMMAND = "bye";

    public static final String MESSAGE = COMMAND + ": Exits the program.";
    /**
     * Executes the ExitCommand which is to exit the program.
     *
     * @param tasks The list where tasks are added to.
     * @param ui The ui to print out response from JamesBot.
     * @param storage The task list that is stored in the storage file.
     */
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        return ui.displayLeaveChat();
    }

    /**
     * Returns whether ExitCommand exits the program.
     *
     * @return true as ExitCommand exits the program.
     */
    public boolean isExit() {
        return true;
    }
}
