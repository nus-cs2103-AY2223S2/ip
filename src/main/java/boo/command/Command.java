package boo.command;

/**
 * Represents a command that is entered by the user
 */
public abstract class Command {
    /**
     * Constructs a {@code Command} instance.
     */
    public Command() {
        //Empty constructor
    }

    /**
     * Runs the given command.
     *
     * @return the string output of running a given command
     */
    public abstract String runCommand();


}
