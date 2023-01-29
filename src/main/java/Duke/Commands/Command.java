package Duke.Commands;

/**
 * Represents the Duke.Commands.Command abstract class.
 * Several commands inherit from Command, and instantiating this
 * will create the command required for processing.
 */
public abstract class Command {

    /**
     * Duke.Commands abstract method that processes the command.
     */
    public abstract void processCommand();

}
