package duke;

/**
 * Command Interface
 */
public interface Command {
    public void execute (TaskList taskList) throws DukeException;
    public boolean isExit();
}
