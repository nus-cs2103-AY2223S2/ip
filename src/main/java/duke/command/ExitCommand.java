package duke.command;

import java.util.function.BiConsumer;

import duke.constant.DialogType;
import duke.constant.Message;
import duke.database.DukeRepo;

/**
 * ExitCommand.
 *
 * @see Command
 */
public class ExitCommand extends Command {

    /**
     * {@inheritDoc}
     */
    @Override
    public void execute(DukeRepo db, BiConsumer<DialogType, String> con) {
        db.close();
        con.accept(DialogType.NORMAL, Message.BYE);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean isExit() {
        return true;
    }

}
