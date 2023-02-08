package duke.commands;

import duke.ui.Ui;

/**
 * ByeCommand represents a command to say bye.
 */
public class ByeCommand extends Command {

    private Ui ui;

    /**
     * Creates a ByeCommand to say bye.
     *
     * @param ui The ui used.
     */
    public ByeCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public String undo() {
        return "";
    }

    /**
     * Says bye and exit.
     */
    @Override
    public String action() {
        return ui.byeMessage();
    }
}
