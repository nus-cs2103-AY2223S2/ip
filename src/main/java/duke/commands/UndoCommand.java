package duke.commands;

import duke.ui.Ui;

/**
 * UndoCommand represents a command to undo a previous command.
 */
public class UndoCommand extends Command {
    private Ui ui;

    /**
     * Creates a UndoCommand to undo.
     *
     * @param ui The ui used.
     */
    public UndoCommand(Ui ui) {
        this.ui = ui;
    }

    @Override
    public String undo() {
        return "";
    }

    /**
     * Undo the previous command.
     */
    @Override
    public String action() {
        Command pastCommand = Command.getPastCommand();
        String undoString = pastCommand.undo();
        return ui.undoResponse(undoString);
    }
}
