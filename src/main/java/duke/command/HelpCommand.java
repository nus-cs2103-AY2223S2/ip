package duke.command;

import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Command that shows help on other commands when executed.
 */
public class HelpCommand extends Command {
    private Command command;
    private boolean isGeneralHelp;

    /**
     * Constructor for a general help command without any specific command.
     */
    public HelpCommand() {
        super();
        this.isGeneralHelp = true;
    }

    /**
     * Constructor for a help command for a specific command.
     * @param command Command for help requested.
     */
    public HelpCommand(Command command) {
        this.command = command;
        this.isGeneralHelp = false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) {
        if (this.isGeneralHelp) {
            return ui.showHelp();
        }
        return command.commandHelp();
    }

    /**
     * @inheritDoc
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * @inheritDoc
     */
    @Override
    public String commandHelp() {
        return "Showing help for command: help\n"
                + Ui.showSepLine()
                + "Shows help for commands\n"
                + Ui.showSepLine()
                + "Usage:\n"
                + "help [COMMAND]\n\n"
                + "Example:\n"
                + "help todo\n"
                + Ui.showSepLine();
    }

    @Override
    public String toString() {
        return "Command: Help";
    }
}
