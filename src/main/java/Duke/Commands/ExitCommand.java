package Duke.Commands;

import Duke.Ui.Ui;

/**
 * Represents the Duke.Commands.ExitCommand of the Chat bot.
 */
public class ExitCommand extends Command {
    @Override
    public void processCommand() {
        System.out.println("abstract method invoked");
    }

    /**
     * Overloaded processCommand method from the abstract class Command.
     * Processes the command for an ExitCommand.
     *
     * @param ui User interface of the Chat bot.
     */
    public void processCommand(Ui ui) {
        ui.printBye();
    }
}
