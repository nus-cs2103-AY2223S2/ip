package duke.command;

import duke.exception.DukeException;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

/**
 * AddCommand class that encompasses the todo, deadline, event commands
 */
public class AddCommand extends Command {
    protected String fullCommand;
    protected String[] s;

    /**
     * Creates a new AddCommand object that has takes note of the full command and the command separated by a space
     * @param fullCommand input by user
     * @param s parsed command with spaces removed
     */
    public AddCommand(String fullCommand, String[] s) {
        this.fullCommand = fullCommand;
        this.s = s;
    }

    /**
     * A method that performs the desired action of the command
     * @param tasks the TaskList class that keeps track of the tasks
     * @param ui User Interface controlling what gets shown on the screen
     * @param storage Storage class that handles the file input and output (saving)
     * @throws DukeException
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws DukeException {
        assert (s.length > 0);
        if (s[0].equals("todo")) {
            return tasks.todo(s);
        } else if (s[0].equals("deadline")) {
            return tasks.deadline(s);
        } else if (s[0].equals("event")) {
            return tasks.event(s);
        }
        return null;
    }

    /**
     * Tests if at end of command stack
     *
     * @return false if not at end, true if no more commands left
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
