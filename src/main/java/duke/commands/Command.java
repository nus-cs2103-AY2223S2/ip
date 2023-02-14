package duke.commands;
import duke.ui.Ui;
import duke.storage.Storage;
import duke.tasklist.TaskList;

/**
 * Processes Commands to execute tasks.
 */
public abstract class Command {
    private boolean isEnd;
    private String tag;

    /**
     * Constuctor for a commmand.
     *
     * @param tag to know what command is used.
     */
    public Command(String tag) {
        this.isEnd = tag.equals("END");
        this.tag = tag;
    }

    /**
     * Processes the command and return a reply.
     *
     * @param tasks the task list
     */
    public abstract String execute(TaskList tasks);

    /**
     * Checks if the command is an exit command which terminates the program.
     *
     * @return boolean to tell if command is an exit command.
     */
    public boolean isExit() {
        return isEnd;
    }
}
