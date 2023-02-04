package duke;

/**
 * Command Interface
 */
public interface Command {
    public String execute(TaskList taskList) throws DukeException;
    public boolean isExit();
}
