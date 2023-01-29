package duke;

import duke.TaskList;
import duke.Ui;
import duke.Storage;
import duke.DukeException;

/**
 * A command class that is abstract.
 */
public abstract class Command {
    public String input;

    /**
     * Constructs classes that inherits from command class
     * with given user input.
     *
     * @param input User input.
     */
    public Command(String input) {
        this.input = input;
    }

    public abstract void execute(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether program should terminate, true or false.
     *
     * @return True or false, if bot should continue running.
     */
    public boolean isExit() {
        return true;
    }

}
