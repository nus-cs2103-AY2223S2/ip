package duke;

/**
 * A command class that is abstract.
 */
public abstract class Command {
    private String input;

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

    public abstract String executeReturnString(TaskList tasks, Ui ui, Storage storage) throws DukeException;

    /**
     * Returns whether program should terminate, true or false.
     *
     * @return True or false, if bot should continue running.
     */
    public boolean isExit() {
        return true;
    }

    public String getInput() {
        return this.input;
    }

}
