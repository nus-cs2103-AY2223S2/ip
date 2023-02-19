package treebot.commands;


/**
 * Represents a <code>Command</code> that when executed exits the program.
 */
public class ExitCommand extends Command {

    /**
     * Constructs the ExitCommand
     */
    public ExitCommand() {
        super.isExitCommand = true;
    }
    @Override
    public String execute() {
        return null;
    }

    @Override
    String toResultString() {
        return null;
    }

}


