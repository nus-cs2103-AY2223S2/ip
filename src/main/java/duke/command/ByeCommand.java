package duke.command;

/**
 * Terminates the UI.
 */
public class ByeCommand extends Command {
    @Override
    public String toString() {
        return String.format("ByeCommand");
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ByeCommand;
    }

    @Override
    public String execute() {
        assert ui != null : "Ui cannot be null";
        return ui.printByeMessage();
    }
}
