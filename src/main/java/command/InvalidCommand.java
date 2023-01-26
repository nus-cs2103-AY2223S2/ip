package command;

public class InvalidCommand extends Command{

    /**
     * Represents an invalid Command
     */
    public InvalidCommand() {
    }

    /**
     * Prints out a statement of invalidity
     */
    @Override
    public void execute() {
        System.out.println("This is an invalid input lol.");
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
