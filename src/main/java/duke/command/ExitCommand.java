package duke.command;

import java.util.function.Consumer;

import duke.constant.Message;
import duke.database.DukeRepo;
import duke.ui.Ui;

/**
 * ExitCommand
 */
public class ExitCommand extends Command {

    /**
     * Closes the database and say farewell.
     *
     * {@inheritDoc}
     */
    @Override
    public void execute(DukeRepo db, Ui ui) {
        db.close();
        ui.printConsole(Message.BYE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(DukeRepo db, Consumer<String> con) {
        db.close();
        con.accept(Message.BYE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
