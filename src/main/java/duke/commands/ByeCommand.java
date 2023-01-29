package duke.commands;

import duke.ui.Ui;

/**
 * ByeCommand represents a command to say bye.
 */
public class ByeCommand extends Command {

    private Ui ui;

    /**
     * Creates a ByeCommand to say bye.
     * @param ui The ui used.
     */
    public ByeCommand(Ui ui) {
        this.ui = ui;
    }


    /**
     * Says bye and exit.
     */
    @Override
    public void action() {
        ui.byeMessage();
        System.exit(0);
    }
}
