package brotherbot.commands;

import brotherbot.storage.*;
import brotherbot.ui.Ui;

public class DisplayCommand extends Command {

    /**
     * Constructor to create an DisplayCommand object.
     *
     * @param input Input string required for command execution.
     */
    public DisplayCommand(String input) {
        super(input);
    }

    /**
     * Executes command.
     *
     * @param storage Existing TaskList object required for command execution.
     * @param ui Ui object required for command execution.
     */
    public void execute(TaskList storage, Ui ui) {
        ui.toUser("Here you go my brother!" );
        storage.display(ui);
        ui.toUser("Anything else I can do for you top G" );

    }
}
